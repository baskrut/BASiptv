package com.andrewbas.basiptv.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.andrewbas.basiptv.R
import com.andrewbas.basiptv.db.Channel


class RvTvAdapter (private val tvChannels: List<Channel>):RecyclerView.Adapter<RvTvAdapter.TvViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        return TvViewHolder(inflater, parent)

    }

    override fun onBindViewHolder(holder: TvViewHolder, position: Int) {

        val tvChanel: Channel = tvChannels[position]
        holder.bind(tvChanel)
    }

    override fun getItemCount(): Int = tvChannels.size

    class TvViewHolder(inflater: LayoutInflater, parent: ViewGroup):
        RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item, parent, false)){

        var chTitle: TextView? = null
//        var chLogo: ImageView? = null

        init {
        chTitle = itemView.findViewById(R.id.tvTitle)
        }

        fun bind(tvChanel: Channel){

            chTitle?.text = tvChanel.title
        }
    }


}