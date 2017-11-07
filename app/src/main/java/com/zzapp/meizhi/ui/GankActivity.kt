package com.zzapp.meizhi.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.WindowManager
import android.widget.AbsListView
import android.widget.ListView
import android.widget.Toast
import com.zzapp.meizhi.R
import com.zzapp.meizhi.presenter.MainPresenter
import com.zzapp.meizhi.utils.GankAdapter
import com.zzapp.meizhi.utils.GankBean
import com.zzapp.meizhi.utils.MyViewPagerAdapter
import com.zzapp.meizhi.view.BaseView
import kotlinx.android.synthetic.main.gank.*

/**
 * Project MeiZhi
 * Date 2017-11-05
 *
 * @author zzzz
 */

class GankActivity: AppCompatActivity(),BaseView {

    private val TEXT = "text"
    private val URL = "url"
    private val VIA = " (via. "

    private var views = emptyList<View>()
    private val tabs = listOf("Android","iOS","前端","瞎推荐")

    private lateinit var presenter: MainPresenter

    private var position = 0

    private var lista: ListView? = null
    private var listi: ListView? = null
    private var listq: ListView? = null
    private var listx: ListView? = null

    private lateinit var adaptera: GankAdapter
    private lateinit var adapteri: GankAdapter
    private lateinit var adapterq: GankAdapter
    private lateinit var adapterx: GankAdapter
    private var dataa: List<Map<String,String>> = emptyList()
    private var datai: List<Map<String,String>> = emptyList()
    private var dataq: List<Map<String,String>> = emptyList()
    private var datax: List<Map<String,String>> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.gank)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = ContextCompat.getColor(this,R.color.colorPrimaryDark)
        }

        initView()
    }

    private fun initView(){
        gankToolbar.setNavigationOnClickListener {
            finish()
        }

        views = listOf(layoutInflater.inflate(R.layout.gank_item,null),layoutInflater.inflate(R.layout.gank_item,null),
                layoutInflater.inflate(R.layout.gank_item,null),layoutInflater.inflate(R.layout.gank_item,null))

        tabLayout.addTab(tabLayout.newTab().setText(tabs[0]))
        tabLayout.addTab(tabLayout.newTab().setText(tabs[1]))
        tabLayout.addTab(tabLayout.newTab().setText(tabs[2]))
        tabLayout.addTab(tabLayout.newTab().setText(tabs[3]))

        val myAdapter = MyViewPagerAdapter(views,tabs)
        viewPager.adapter = myAdapter
        tabLayout.setupWithViewPager(viewPager)

        tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                position = tab!!.position

                when(position){
                    0 -> {
                        if(lista == null){
                            lista = views[0].findViewById(R.id.gankList)
                            adaptera = GankAdapter(this@GankActivity,dataa)
                            lista!!.adapter = adaptera
                            lista!!.setOnItemClickListener { _, _, i, _ ->
                                startActivity(Intent(this@GankActivity,WebActivity::class.java).putExtra(URL,dataa[i][URL]!!))
                            }
                            lista!!.setOnScrollListener(object: AbsListView.OnScrollListener{
                                override fun onScrollStateChanged(p0: AbsListView?, p1: Int) {
                                    if(p1 == AbsListView.OnScrollListener.SCROLL_STATE_IDLE){
                                        if(p0!!.lastVisiblePosition >= dataa.size - 1){
                                            presenter.freshGank(tabs[0],dataa.size / 10 + 1)
                                        }
                                    }
                                }

                                override fun onScroll(p0: AbsListView?, p1: Int, p2: Int, p3: Int) {

                                }
                            })
                        }
                        if(dataa.isEmpty()){
                            presenter.freshGank(tabs[0],1)
                        }
                    }
                    1 -> {
                        if(listi == null){
                            listi = views[1].findViewById(R.id.gankList)
                            adapteri = GankAdapter(this@GankActivity,datai)
                            listi!!.adapter = adapteri
                            listi!!.setOnItemClickListener { _, _, i, _ ->
                                startActivity(Intent(this@GankActivity,WebActivity::class.java).putExtra(URL,datai[i][URL]!!))
                            }
                            listi!!.setOnScrollListener(object: AbsListView.OnScrollListener{
                                override fun onScrollStateChanged(p0: AbsListView?, p1: Int) {
                                    if(p1 == AbsListView.OnScrollListener.SCROLL_STATE_IDLE){
                                        if(p0!!.lastVisiblePosition >= datai.size - 1){
                                            presenter.freshGank(tabs[1],datai.size / 10 + 1)
                                        }
                                    }
                                }

                                override fun onScroll(p0: AbsListView?, p1: Int, p2: Int, p3: Int) {

                                }
                            })
                        }
                        if(datai.isEmpty()){
                            presenter.freshGank(tabs[1],1)
                        }
                    }
                    2 -> {
                        if(listq == null){
                            listq = views[2].findViewById(R.id.gankList)
                            adapterq = GankAdapter(this@GankActivity,dataq)
                            listq!!.adapter = adapterq
                            listq!!.setOnItemClickListener { _, _, i, _ ->
                                startActivity(Intent(this@GankActivity,WebActivity::class.java).putExtra(URL,dataq[i][URL]!!))
                            }
                            listq!!.setOnScrollListener(object: AbsListView.OnScrollListener{
                                override fun onScrollStateChanged(p0: AbsListView?, p1: Int) {
                                    if(p1 == AbsListView.OnScrollListener.SCROLL_STATE_IDLE){
                                        if(p0!!.lastVisiblePosition >= dataq.size - 1){
                                            presenter.freshGank(tabs[2],dataq.size / 10 + 1)
                                        }
                                    }
                                }

                                override fun onScroll(p0: AbsListView?, p1: Int, p2: Int, p3: Int) {

                                }
                            })
                        }
                        if(dataq.isEmpty()){
                            presenter.freshGank(tabs[2],1)
                        }
                    }
                    else -> {
                        if(listx == null){
                            listx = views[3].findViewById(R.id.gankList)
                            adapterx = GankAdapter(this@GankActivity,datax)
                            listx!!.adapter = adapterx
                            listx!!.setOnItemClickListener { _, _, i, _ ->
                                startActivity(Intent(this@GankActivity,WebActivity::class.java).putExtra(URL,datax[i][URL]!!))
                            }
                            listx!!.setOnScrollListener(object: AbsListView.OnScrollListener{
                                override fun onScrollStateChanged(p0: AbsListView?, p1: Int) {
                                    if(p1 == AbsListView.OnScrollListener.SCROLL_STATE_IDLE){
                                        if(p0!!.lastVisiblePosition >= datax.size - 1){
                                            presenter.freshGank(tabs[3],datax.size / 10 + 1)
                                        }
                                    }
                                }

                                override fun onScroll(p0: AbsListView?, p1: Int, p2: Int, p3: Int) {

                                }
                            })
                        }
                        if(datax.isEmpty()){
                            presenter.freshGank(tabs[3],1)
                        }
                    }
                }
            }
        })

        lista = views[0].findViewById(R.id.gankList)
        adaptera = GankAdapter(this,dataa)
        lista!!.adapter = adaptera
        lista!!.setOnItemClickListener { _, _, i, _ ->
            startActivity(Intent(this@GankActivity,WebActivity::class.java).putExtra(URL,dataa[i][URL]!!))
        }
        lista!!.setOnScrollListener(object: AbsListView.OnScrollListener{
            override fun onScrollStateChanged(p0: AbsListView?, p1: Int) {
                if(p1 == AbsListView.OnScrollListener.SCROLL_STATE_IDLE){
                    if(p0!!.lastVisiblePosition >= dataa.size - 1){
                        presenter.freshGank(tabs[0],dataa.size / 10 + 1)
                    }
                }
            }

            override fun onScroll(p0: AbsListView?, p1: Int, p2: Int, p3: Int) {

            }
        })

        presenter = MainPresenter(this)
        presenter.freshGank(tabs[0],1)
    }

    override fun setDataView(data: Any) {
        data as List<GankBean.ResultsBean>
        when(position){
            0 -> {
                for(bean in data){
                    this.dataa += mapOf(TEXT to bean.desc + VIA + bean.who + ")",URL to bean.url!!)
                }
                adaptera.setData(this.dataa)
            }
            1 -> {
                for(bean in data){
                    this.datai += mapOf(TEXT to bean.desc + VIA + bean.who + ")",URL to bean.url!!)
                }
                adapteri.setData(this.datai)
            }
            2 -> {
                for(bean in data){
                    this.dataq += mapOf(TEXT to bean.desc + VIA + bean.who + ")",URL to bean.url!!)
                }
                adapterq.setData(this.dataq)
            }
            else -> {
                for(bean in data){
                    this.datax += mapOf(TEXT to bean.desc + VIA + bean.who + ")",URL to bean.url!!)
                }
                adapterx.setData(this.datax)
            }
        }
    }

    override fun toast(string: String) {
        Toast.makeText(applicationContext,string,Toast.LENGTH_SHORT).show()
    }
}