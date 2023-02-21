package com.example.girls4girls.presentation.videoblogsList

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.girls4girls.R
import com.example.girls4girls.data.VideoBlog
import com.example.girls4girls.databinding.ItemVideoblogBinding
import java.util.*

class VideoAdapter: ListAdapter<VideoBlog, VideoAdapter.VideoBlogViewHolder>(VideoBlogDiffCallback()) {

    var unfilteredList = listOf<VideoBlog>()

    var onVideoClickListener: ((VideoBlog) -> Unit)? = null

    class VideoBlogViewHolder(item: View, private val onVideoClickListener: ((VideoBlog) -> Unit)?): RecyclerView.ViewHolder(item){
        private val binding = ItemVideoblogBinding.bind(item)
        fun bind(videoBlog: VideoBlog) = with(binding){
            videoTitle.text = videoBlog.title
            itemView.setOnClickListener {
                onVideoClickListener?.invoke(videoBlog)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoBlogViewHolder {
        val view = LayoutInflater.from((parent.context)).inflate(R.layout.item_videoblog, parent, false)
        return VideoBlogViewHolder(view, onVideoClickListener)
    }

    override fun onBindViewHolder(holder: VideoBlogViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun modifyList(list: List<VideoBlog>){
        unfilteredList = list
        submitList(list)
    }

    fun filter(query: CharSequence){
        val list = mutableListOf<VideoBlog>()

        if (!query.isNullOrEmpty()){
            list.addAll(unfilteredList.filter {
                it.title.lowercase(Locale.getDefault()).contains(query.toString().lowercase(Locale.getDefault()))
            })
        } else {
            list.addAll(unfilteredList)
        }

        submitList(list)
    }
}