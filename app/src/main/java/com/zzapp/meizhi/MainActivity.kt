package com.zzapp.meizhi

import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import com.zzapp.meizhi.presenter.MainPresenter
import com.zzapp.meizhi.ui.AboutActivity
import com.zzapp.meizhi.ui.DayActivity
import com.zzapp.meizhi.ui.GankActivity
import com.zzapp.meizhi.utils.MeiZhiAdapter
import com.zzapp.meizhi.utils.MeiZhiBean
import com.zzapp.meizhi.view.BaseView
import com.zzapp.meizhi.ui.MeiZhiActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Project MeiZhi
 * Date 2017-11-03
 *
 * @author zzzz
 */

class MainActivity : AppCompatActivity(),BaseView {

    private val URL = "url"
    private val YEAR = "year"
    private val MONTH = "month"
    private val DAY = "day"
    private val DIVIDER_T = "T"
    private val DIVIDER__ = "-"

    private lateinit var presenter: MainPresenter

    private var data: List<MeiZhiBean.ResultsBean> = emptyList()

    private lateinit var adapter: MeiZhiAdapter

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            R.id.about -> startActivity(Intent(this@MainActivity, AboutActivity::class.java))
            else -> return false
        }

        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = ContextCompat.getColor(this,R.color.colorPrimaryDark)
        }

        initView()
    }

    private fun initView(){
        setSupportActionBar(mainToolbar)

        val recyclerViewLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = recyclerViewLayoutManager
        adapter = MeiZhiAdapter(this.data, applicationContext)
        adapter.setOnItemClickListener(object: MeiZhiAdapter.MyOnItemClickListener {
            override fun onImageClick(view: View, position: Int) {
                val intent = Intent(this@MainActivity, MeiZhiActivity::class.java)
                intent.putExtra(URL,data[position].url)
                startActivity(intent)
            }

            override fun onTextClick(view: View, position: Int) {
                val date = data[position].publishedAt!!.split(DIVIDER_T)[0].split(DIVIDER__)
                val intent = Intent(this@MainActivity, DayActivity::class.java)
                intent.putExtra(YEAR,date[0])
                intent.putExtra(MONTH,date[1])
                intent.putExtra(DAY,date[2])
                intent.putExtra(URL,data[position].url)
                startActivity(intent)
            }
        })
        recyclerView.adapter = adapter

        recyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                if(newState == RecyclerView.SCROLL_STATE_IDLE){
                    val positions = recyclerViewLayoutManager.findLastVisibleItemPositions(null)
                    val flag = positions.max()
                    val isFinish = this@MainActivity.data.isNotEmpty() && flag!! >= this@MainActivity.data.size - 1
                    if(isFinish){
                        presenter.freshMeiZhi(this@MainActivity.data.size / 10 + 1)
                    }
                }
            }
        })

        fabutton.setOnClickListener {
            startActivity(Intent(this@MainActivity,GankActivity::class.java))
        }

        presenter = MainPresenter(this)
        presenter.freshMeiZhi(1)
    }

    override fun setDataView(data: Any) {
        this.data += data as List<MeiZhiBean.ResultsBean>
        adapter.addData(data,this.data.size)
    }

    override fun toast(string: String) {
        Toast.makeText(applicationContext,string,Toast.LENGTH_SHORT).show()
    }
}