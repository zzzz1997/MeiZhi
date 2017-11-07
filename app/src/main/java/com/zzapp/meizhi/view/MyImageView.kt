package com.zzapp.meizhi.view

import android.content.Context
import android.graphics.Matrix
import android.graphics.RectF
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import android.view.ViewTreeObserver
import android.widget.ImageView

/**
 * Project MeiZhi
 * Date 2017-11-04
 *
 * @author zzzz
 */

class MyImageView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : android.support.v7.widget.AppCompatImageView(context, attrs), View.OnTouchListener, ViewTreeObserver.OnGlobalLayoutListener {
    private var SCALE_FULL: Float = 0.toFloat()
    private var SCALE_DOUBLE: Float = 0.toFloat()
    private var SCALE_MAX: Float = 0.toFloat()
    private var SCALE_INIT: Float = 0.toFloat()
    private val isLimitedScale = true
    private val mScaleMatrix = Matrix()
    private val matrixValues = FloatArray(9)
    private val mScaleGestureDetector: ScaleGestureDetector
    private val mGestureDetector: GestureDetector
    private var isAutoScale: Boolean = false
    private val mTouchSlop: Int = 0
    private var mLastX: Float = 0.toFloat()
    private var mLastY: Float = 0.toFloat()
    private var isCanDrag: Boolean = false
    private var lastPointerCount: Int = 0
    private var isCheckTopAndBottom = true
    private var isCheckLeftAndRight = true
    private val matrixRectF: RectF
        get() {
            val matrix = mScaleMatrix
            val rect = RectF()
            val d = drawable
            if (null != d) {
                rect.set(0f, 0f, d.intrinsicWidth.toFloat(), d.intrinsicHeight.toFloat())
                matrix.mapRect(rect)
            }
            return rect
        }
    val scale: Float
        get() {
            mScaleMatrix.getValues(matrixValues)
            return matrixValues[Matrix.MSCALE_X]
        }

    init {
        scaleType = ImageView.ScaleType.MATRIX
        mGestureDetector = GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
            override fun onDoubleTap(e: MotionEvent): Boolean {
                if (isAutoScale == true) {
                    return true
                }
                val x = e.x
                val y = e.y
                if (scale < SCALE_FULL) {
                    post(AutoScaleRunnable(SCALE_FULL, x, y))
                } else if (scale >= SCALE_FULL && scale < SCALE_DOUBLE) {
                    post(AutoScaleRunnable(SCALE_DOUBLE, x, y))
                } else {
                    post(AutoScaleRunnable(SCALE_INIT, x, y))
                }
                isAutoScale = true
                return true
            }
        })
        mScaleGestureDetector = ScaleGestureDetector(context, object : ScaleGestureDetector.SimpleOnScaleGestureListener() {
            override fun onScale(detector: ScaleGestureDetector): Boolean {
                if (drawable == null) {
                    return true
                }
                val scale = scale
                var scaleFactor = detector.scaleFactor
                if (!isLimitedScale) {
                    mScaleMatrix.postScale(scaleFactor, scaleFactor, detector.focusX, detector.focusY)
                } else if (scale < SCALE_MAX && scaleFactor > 1.0f || scale > SCALE_INIT && scaleFactor < 1.0f) {
                    if (scaleFactor * scale < SCALE_INIT) {
                        scaleFactor = SCALE_INIT / scale
                    }
                    if (scaleFactor * scale > SCALE_MAX) {
                        scaleFactor = SCALE_MAX / scale
                    }
                    mScaleMatrix.postScale(scaleFactor, scaleFactor, detector.focusX, detector.focusY)
                    checkBorderAndCenterWhenScale()
                    imageMatrix = mScaleMatrix
                }
                return true
            }
        })
        this.setOnTouchListener(this)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        viewTreeObserver.addOnGlobalLayoutListener(this)
    }

    override fun onGlobalLayout() {
        if (drawable == null) {
            return
        }
        val dw = drawable.intrinsicWidth
        val dh = drawable.intrinsicHeight
        if (dw >= width && dh >= height) {
            SCALE_INIT = Math.min(width * 1.0f / dw, height * 1.0f / dh)
            SCALE_FULL = Math.max(width * 1.0f / dw, height * 1.0f / dh)
        } else if (dw >= width) {
            SCALE_INIT = width * 1.0f / dw
            SCALE_FULL = height * 1.0f / dh
        } else if (dh >= height) {
            SCALE_INIT = height * 1.0f / dh
            SCALE_FULL = width * 1.0f / dw
        } else {
            SCALE_INIT = 1.0f
            SCALE_FULL = Math.min(width * 1.0f / dw, height * 1.0f / dh)
        }
        SCALE_DOUBLE = 1.5f * SCALE_FULL
        SCALE_MAX = 3.5f * SCALE_FULL
        mScaleMatrix.postTranslate(((width - dw) / 2).toFloat(), ((height - dh) / 2).toFloat())
        mScaleMatrix.postScale(SCALE_INIT, SCALE_INIT, (width / 2).toFloat(), (height / 2).toFloat())
        imageMatrix = mScaleMatrix
        //viewTreeObserver.removeOnGlobalLayoutListener(this)
    }

    override fun onTouch(v: View, event: MotionEvent): Boolean {
        if (mGestureDetector.onTouchEvent(event)) {
            return true
        }
        mScaleGestureDetector.onTouchEvent(event)
        var x = 0f
        var y = 0f
        val pointerCount = event.pointerCount
        for (i in 0 until pointerCount) {
            x += event.getX(i)
            y += event.getY(i)
        }
        x /= pointerCount
        y /= pointerCount
        if (pointerCount != lastPointerCount) {
            isCanDrag = false
            mLastX = x
            mLastY = y
        }
        lastPointerCount = pointerCount
        val rectF = matrixRectF
        when (event.action) {
            MotionEvent.ACTION_DOWN -> if (rectF.width() > width || rectF.height() > height) parent.requestDisallowInterceptTouchEvent(true)
            MotionEvent.ACTION_MOVE -> {
                if (rectF.width() > width || rectF.height() > height) parent.requestDisallowInterceptTouchEvent(true)
                var dx = x - mLastX
                var dy = y - mLastY
                if (!isCanDrag) isCanDrag = isCanDrag(dx, dy)
                if (isCanDrag) {
                    if (drawable != null) {
                        isCheckTopAndBottom = true
                        isCheckLeftAndRight = isCheckTopAndBottom
                        if (rectF.width() < width) {
                            dx = 0f
                            isCheckLeftAndRight = false
                        }
                        if (rectF.height() < height) {
                            dy = 0f
                            isCheckTopAndBottom = false
                        }
                        var translateScale = scale
                        if (translateScale > 2.0f) translateScale = 2.0f
                        if (translateScale < 0.5f) translateScale = 0.5f
                        mScaleMatrix.postTranslate(dx * translateScale, dy * translateScale)
                        checkMatrixBounds()
                        imageMatrix = mScaleMatrix
                    }
                }
                mLastX = x
                mLastY = y
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                lastPointerCount = 0
            }
        }
        return true
    }

    private fun checkBorderAndCenterWhenScale() {
        val rect = matrixRectF
        var deltaX = 0f
        var deltaY = 0f
        val width = width
        val height = height
        if (rect.width() >= width) {
            if (rect.left > 0) {
                deltaX = -rect.left
            }
            if (rect.right < width) {
                deltaX = width - rect.right
            }
        }
        if (rect.height() >= height) {
            if (rect.top > 0) {
                deltaY = -rect.top
            }
            if (rect.bottom < height) {
                deltaY = height - rect.bottom
            }
        }
        if (rect.width() < width) {
            deltaX = width * 0.5f - rect.right + 0.5f * rect.width()
        }
        if (rect.height() < height) {
            deltaY = height * 0.5f - rect.bottom + 0.5f * rect.height()
        }
        mScaleMatrix.postTranslate(deltaX, deltaY)
    }

    private fun checkMatrixBounds() {
        val rect = matrixRectF
        var deltaX = 0f
        var deltaY = 0f
        val viewWidth = width.toFloat()
        val viewHeight = height.toFloat()
        if (rect.top > 0 && isCheckTopAndBottom) {
            deltaY = -rect.top
        }
        if (rect.bottom < viewHeight && isCheckTopAndBottom) {
            deltaY = viewHeight - rect.bottom
        }
        if (rect.left > 0 && isCheckLeftAndRight) {
            deltaX = -rect.left
        }
        if (rect.right < viewWidth && isCheckLeftAndRight) {
            deltaX = viewWidth - rect.right
        }
        mScaleMatrix.postTranslate(deltaX, deltaY)
    }

    private fun isCanDrag(dx: Float, dy: Float): Boolean {
        return Math.sqrt((dx * dx + dy * dy).toDouble()) >= mTouchSlop
    }

    private inner class AutoScaleRunnable internal constructor(private val mTargetScale: Float, private val x: Float, private val y: Float) : Runnable {

        private val time = 100
        private var tmpScale: Float = 0.toFloat()

        init {
            if (scale < mTargetScale) {
                tmpScale = 1.0f + (mTargetScale - scale / time)
            } else {
                tmpScale = 1.0f - (scale - mTargetScale / time)
            }
        }

        override fun run() {
            mScaleMatrix.postScale(tmpScale, tmpScale, x, y)
            checkBorderAndCenterWhenScale()
            imageMatrix = mScaleMatrix
            val isShneMe = tmpScale > 1f && scale < mTargetScale || tmpScale < 1f && scale > mTargetScale
            if (isShneMe) {
                post(this)
            } else {
                val deltaScale = mTargetScale / scale
                mScaleMatrix.postScale(deltaScale, deltaScale, x, y)
                checkBorderAndCenterWhenScale()
                imageMatrix = mScaleMatrix
                isAutoScale = false
            }
        }
    }

    companion object {
        private val TAG = "bqt"
    }
}
