package com.zzapp.meizhi.presenter

/**
 * Project MeiZhi
 * Date 2017-11-03
 *
 * @author zzzz
 */
interface BasePresenter {

    /**
     * 刷新妹纸图片
     *
     * @param page 图片页码
     */
    fun freshMeiZhi(page: Int)

    /**
     * 刷新每日干货
     *
     * @param year 年份
     * @param month 月份
     * @param day 日期
     */
    fun freshDay(year: String,month: String,day: String)

    /**
     * 刷新单一类型干货
     *
     * @param type 干货类型
     * @param page 干货页码
     */
    fun freshGank(type: String,page: Int)
}