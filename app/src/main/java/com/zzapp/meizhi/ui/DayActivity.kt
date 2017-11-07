package com.zzapp.meizhi.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.widget.*
import com.zzapp.meizhi.R
import com.zzapp.meizhi.presenter.MainPresenter
import com.zzapp.meizhi.utils.DayBean
import com.zzapp.meizhi.view.BaseView
import kotlinx.android.synthetic.main.day.*
import android.os.Build
import android.support.v4.content.ContextCompat
import android.view.WindowManager
import com.bumptech.glide.Glide


/**
 * Project MeiZhi
 * Date 2017-11-05
 *
 * @author zzzz
 */

class DayActivity: AppCompatActivity(),BaseView {

    private val YEAR = "year"
    private val MONTH = "month"
    private val DAY = "day"
    private val TEXT = "text"
    private val URL = "url"
    private val VIA = " (via. "

    private lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.day)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = ContextCompat.getColor(this,R.color.colorPrimaryDark)
        }

        initView()
    }

    private fun initView(){
        val year = intent.getStringExtra(YEAR)
        val month = intent.getStringExtra(MONTH)
        val day = intent.getStringExtra(DAY)
        dayToolbar.title = "$year/$month/$day"
        dayToolbar.setNavigationOnClickListener {
            finish()
        }

        Glide.with(this)
                .load(intent.getStringExtra(URL))
                .into(infoImage)

        presenter = MainPresenter(this)
        presenter.freshDay(year,month,day)
    }

    override fun setDataView(data: Any) {
        data as DayBean.ResultsBean

        val 休息视频 = data.休息视频
        if(休息视频 != null){
            val title = 休息视频[0].type!!
            var list = emptyList<Map<String,String>>()
            for(休息视频Bean in 休息视频){
                list += mapOf(TEXT to 休息视频Bean.desc + VIA + 休息视频Bean.who + ")",URL to 休息视频Bean.url!!)
            }
            addView(title,list)
        }

        val androids = data.Android
        if(androids != null){
            val title = androids[0].type!!
            var list = emptyList<Map<String,String>>()
            for(androidBean in androids){
                list += mapOf(TEXT to androidBean.desc + VIA + androidBean.who + ")",URL to androidBean.url!!)
            }
            addView(title,list)
        }

        val ios = data.iOS
        if(ios != null){
            val title = ios[0].type!!
            var list = emptyList<Map<String,String>>()
            for(iosBean in ios){
                list += mapOf(TEXT to iosBean.desc + VIA + iosBean.who + ")",URL to iosBean.url!!)
            }
            addView(title,list)
        }

        val 前端 = data.前端
        if(前端 != null){
            val title = 前端[0].type!!
            var list = emptyList<Map<String,String>>()
            for(前端Bean in 前端){
                list += mapOf(TEXT to 前端Bean.desc + VIA + 前端Bean.who + ")",URL to 前端Bean.url!!)
            }
            addView(title,list)
        }

        val 瞎推荐 = data.瞎推荐
        if(瞎推荐 != null){
            val title = 瞎推荐[0].type!!
            var list = emptyList<Map<String,String>>()
            for(瞎推荐Bean in 瞎推荐){
                list += mapOf(TEXT to 瞎推荐Bean.desc + VIA + 瞎推荐Bean.who + ")",URL to 瞎推荐Bean.url!!)
            }
            addView(title,list)
        }
    }

    override fun toast(string: String) {
        Toast.makeText(applicationContext,string, Toast.LENGTH_SHORT).show()
    }

    private fun addView(title: String,list: List<Map<String,String>>){
        val textView = TextView(this)
        textView.setPadding(resources.getDimensionPixelSize(R.dimen.dp10),resources.getDimensionPixelSize(R.dimen.dp10),
                resources.getDimensionPixelSize(R.dimen.dp10),resources.getDimensionPixelSize(R.dimen.dp10))
        textView.text = title
        textView.textSize = 20f
        textView.paint.isFakeBoldText = true
        dayLayout.addView(textView)

        for(data in list){
            val view = LayoutInflater.from(this).inflate(R.layout.text_item,null)
            val text = view.findViewById<TextView>(R.id.text)
            text.text = data[TEXT]
            text.setOnClickListener {
                startActivity(Intent(this@DayActivity,WebActivity::class.java).putExtra(URL,data[URL]!!))
            }
            view.isClickable = true
            view.isFocusable = true
            dayLayout.addView(view)
        }
    }
}