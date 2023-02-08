package com.example.girls4girls.presentation.videoblogsList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.girls4girls.R
import com.example.girls4girls.data.VideoBlog
import com.example.girls4girls.databinding.ItemVideoblogBinding

class VideoAdapter: ListAdapter<VideoBlog, VideoAdapter.VideoBlogViewHolder>(VideoBlogDiffCallback()) {

    var onVideoClickListener: ((VideoBlog) -> Unit)? = null

    class VideoBlogViewHolder(item: View): RecyclerView.ViewHolder(item){
        private val binding = ItemVideoblogBinding.bind(item)
        fun bind(videoBlog: VideoBlog) = with(binding){
            videoTitle.text = videoBlog.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoBlogViewHolder {
        val view = LayoutInflater.from((parent.context)).inflate(R.layout.item_videoblog, parent, false)
        return VideoBlogViewHolder(view)
    }

    override fun onBindViewHolder(holder: VideoBlogViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            onVideoClickListener?.invoke(getItem(position))
        }
    }

    interface OnVideoClickListener{
        fun onVideoClick(videoBlog: VideoBlog)
    }
}