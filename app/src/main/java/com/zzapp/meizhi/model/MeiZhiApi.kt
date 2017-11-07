package com.zzapp.meizhi.model

import com.zzapp.meizhi.utils.DayBean
import com.zzapp.meizhi.utils.GankBean
import com.zzapp.meizhi.utils.MeiZhiBean
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Project MeiZhi
 * Date 2017-11-03
 *
 * @author zzzz
 */

interface MeiZhiApi {

    /**
     * 获取妹纸数据
     *
     * @param page 图片页码
     */
    @GET("api/data/福利/10/{page}")
    fun getMeiZhiInfo(@Path("page")page: Int): Call<MeiZhiBean>

    /**
     * 获取某一天的干货
     *
     * @param year 年份
     * @param month 月份
     * @param day 日期
     */
    @GET("api/day/{year}/{month}/{day}")
    fun getDayInfo(@Path("year")year: String, @Path("month")month: String, @Path("day")day: String): Call<DayBean>

    /**
     * 获取某类型干货
     *
     * @param type 干货类型
     * @param page 干货页码
     */
    @GET("api/data/{type}/25/{page}")
    fun getGank(@Path("type")type: String, @Path("page")page: Int): Call<GankBean>
}