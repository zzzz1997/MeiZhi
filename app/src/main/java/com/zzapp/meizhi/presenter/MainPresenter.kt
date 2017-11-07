package com.zzapp.meizhi.presenter

import com.zzapp.meizhi.model.MeiZhiApi
import com.zzapp.meizhi.utils.DayBean
import com.zzapp.meizhi.utils.GankBean
import com.zzapp.meizhi.utils.MeiZhiBean
import com.zzapp.meizhi.view.BaseView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Project MeiZhi
 * Date 2017-11-03
 *
 * @author zzzz
 */
class MainPresenter constructor(private var view: BaseView): BasePresenter {

    private val URL = "http://gank.io/"

    private val retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    private val api = retrofit.create(MeiZhiApi::class.java)

    override fun freshMeiZhi(page: Int) {
        val call = api.getMeiZhiInfo(page)
        call.enqueue(object: Callback<MeiZhiBean> {
            override fun onResponse(call: Call<MeiZhiBean>?, response: Response<MeiZhiBean>?){
                view.setDataView(response?.body()?.results!!)
            }

            override fun onFailure(call: Call<MeiZhiBean>?, t: Throwable?) {
                view.toast(t.toString())
            }
        })
    }

    override fun freshDay(year: String, month: String, day: String) {
        val call = api.getDayInfo(year,month,day)
        call.enqueue(object: Callback<DayBean> {
            override fun onResponse(call: Call<DayBean>?, response: Response<DayBean>?){
                view.setDataView(response?.body()?.results!!)
            }

            override fun onFailure(call: Call<DayBean>?, t: Throwable?) {
                view.toast(t.toString())
            }
        })
    }

    override fun freshGank(type: String, page: Int) {
        val call = api.getGank(type,page)
        call.enqueue(object: Callback<GankBean> {
            override fun onResponse(call: Call<GankBean>?, response: Response<GankBean>?){
                view.setDataView(response?.body()?.results!!)
            }

            override fun onFailure(call: Call<GankBean>?, t: Throwable?) {
                view.toast(t.toString())
            }
        })
    }
}