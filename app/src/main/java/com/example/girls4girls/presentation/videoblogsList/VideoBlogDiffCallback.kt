package com.example.girls4girls.presentation.videoblogsList

import androidx.recyclerview.widget.DiffUtil
import com.example.girls4girls.data.VideoBlog

class VideoBlogDiffCallback: DiffUtil.ItemCallback<VideoBlog>() {
    override fun areItemsTheSame(oldItem: VideoBlog, newItem: VideoBlog): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: VideoBlog, newItem: VideoBlog): Boolean {
        return oldItem == newItem
    }
}