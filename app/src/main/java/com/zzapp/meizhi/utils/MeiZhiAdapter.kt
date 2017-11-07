package com.zzapp.meizhi.utils

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.zzapp.meizhi.R

/**
 * Project MeiZhi
 * Date 2017-11-03
 *
 * @author zzzz
 */

class MeiZhiAdapter constructor(private var data: List<MeiZhiBean.ResultsBean>, private var context: Context): RecyclerView.Adapter<MeiZhiAdapter.MeiZhiViewHolder>(),View.OnClickListener {

    private val IMAGE_SIZE = "?imageView2/0/w/200"

    lateinit var bean: MeiZhiBean.ResultsBean

    private lateinit var myonItemClickListener: MyOnItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MeiZhiViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.meizhi_item, parent, false)
        view.setOnClickListener(this)
        return MeiZhiViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MeiZhiViewHolder?, position: Int) {
        holder!!.itemView.tag = position
        holder.text.tag = position
        holder.text.setOnClickListener(this)
        bean = data[position]
        holder.text.text = bean.desc + "\n" + bean.who
        Glide.with(context)
                .load(bean.url + IMAGE_SIZE)
                .into(holder.image)
    }

    override fun onClick(p0: View?) {
        if(myonItemClickListener != null){
            when(p0!!.id){
                R.id.itemText -> myonItemClickListener.onTextClick(p0, p0.tag as Int)
                else -> myonItemClickListener.onImageClick(p0, p0.tag as Int)
            }

        }
    }

    fun addData(data: List<MeiZhiBean.ResultsBean>,position: Int){
        this.data += data
        notifyItemInserted(position)
    }

    fun setOnItemClickListener(onItemClickListener: MyOnItemClickListener){
        this.myonItemClickListener = onItemClickListener
    }

    class MeiZhiViewHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView) {
        var image: ImageView = itemView.findViewById(R.id.itemImage)
        var text: TextView = itemView.findViewById(R.id.itemText)
    }

    interface MyOnItemClickListener {
        fun onImageClick(view: View, position: Int)
        fun onTextClick(view: View, position: Int)
    }
}