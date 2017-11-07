package com.zzapp.meizhi.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.zzapp.meizhi.R

/**
 * Project MeiZhi
 * Date 2017-11-05
 *
 * @author zzzz
 */
class GankAdapter constructor(private var context: Context,private var data: List<Map<String,String>>): BaseAdapter() {

    private val TEXT = "text"

    override fun getItem(p0: Int): Any {
        return p0
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return data.size
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val vh: ViewHolder
        val v: View
        if(p1 == null){
            vh = ViewHolder()
            v = LayoutInflater.from(context).inflate(R.layout.text_item, p2,false)
            vh.text = v.findViewById(R.id.text)
            v.tag = vh
        } else{
            v = p1
            vh = v.tag as GankAdapter.ViewHolder
        }

        vh.text.text = data[p0][TEXT]

        return v
    }

    fun setData(data: List<Map<String,String>>){
        this.data = data
        notifyDataSetChanged()
    }

    class ViewHolder{
        lateinit var text: TextView
    }
}