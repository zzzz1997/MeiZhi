package com.zzapp.meizhi.utils

import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup

/**
 * Project MeiZhi
 * Date 2017-11-05
 *
 * @author zzzz
 */

class MyViewPagerAdapter constructor(private val viewLIst: List<View>,private val tabLIst: List<String>): PagerAdapter() {

    override fun instantiateItem(container: ViewGroup?, position: Int): Any {
        container!!.addView(viewLIst[position])
        return viewLIst[position]
    }

    override fun isViewFromObject(view: View?, `object`: Any?): Boolean {
        return view == `object`
    }

    override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
        container!!.removeView(viewLIst[position])
    }

    override fun getCount(): Int {
        return viewLIst.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return tabLIst[position]
    }
}