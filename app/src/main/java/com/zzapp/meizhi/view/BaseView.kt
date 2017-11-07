package com.zzapp.meizhi.view

/**
 * Project MeiZhi
 * Date 2017-11-03
 *
 * @author zzzz
 */
interface BaseView {

    /**
     * 更新RecyclerView
     *
     * @param data 妹纸数据
     */
    fun setDataView(data: Any)

    /**
     * 新建吐司
     *
     * @param string 吐司文本
     */
    fun toast(string: String)
}