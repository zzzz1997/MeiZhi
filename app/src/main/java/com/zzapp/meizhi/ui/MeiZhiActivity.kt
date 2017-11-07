package com.zzapp.meizhi.ui

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.zzapp.meizhi.R
import kotlinx.android.synthetic.main.meizhi.*

/**
 * Project MeiZhi
 * Date 2017-11-04
 *
 * @author zzzz
 */

class MeiZhiActivity: Activity() {

    private val URL = "url"
    private val SAVE = "保存图片"
    private val SHARE = "分享"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.meizhi)

        initView()
    }

    private fun initView(){
        Glide.with(this)
                .load(intent.getStringExtra(URL))
                .into(meizhiPicture)

        meizhiPicture.setOnClickListener {
            Log.e("click","short")
        }
        meizhiPicture.setOnLongClickListener {
            Log.e("click","long")
            AlertDialog.Builder(this@MeiZhiActivity)
                    .setItems(arrayOf(SAVE,SHARE), { _, i ->
                        when(i){
                            0 -> {
                                Toast.makeText(applicationContext,SAVE,Toast.LENGTH_SHORT).show()
                            }
                            else -> {
                                Toast.makeText(applicationContext,SHARE,Toast.LENGTH_SHORT).show()
                            }
                        }
                    })
                    .create().show()
            true
        }
    }
}
