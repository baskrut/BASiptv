package com.andrewbas.basiptv

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class RvTvAdapter (private val tvChanels: List<TvChanel>):RecyclerView.Adapter<RvTvAdapter.TvViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        return TvViewHolder(inflater, parent)

    }

    override fun onBindViewHolder(holder: TvViewHolder, position: Int) {

        val tvChanel: TvChanel = tvChanels[position]
        holder.bind(tvChanel)
    }

    override fun getItemCount(): Int = tvChanels.size

    class TvViewHolder(inflater: LayoutInflater, parent: ViewGroup):
        RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item, parent, false)){

        var chTitle: TextView? = null
//        var chLogo: ImageView? = null

        init {
        chTitle = itemView.findViewById(R.id.tvTitle)
        }

        fun bind(tvChanel: TvChanel){

            chTitle?.text = tvChanel.title
        }
    }


}