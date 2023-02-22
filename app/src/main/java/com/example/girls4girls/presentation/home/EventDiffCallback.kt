package com.example.girls4girls.presentation.home

import androidx.recyclerview.widget.DiffUtil
import com.example.girls4girls.data.Event
import com.example.girls4girls.data.VideoBlog

class EventDiffCallback: DiffUtil.ItemCallback<Event>() {
    override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
        return oldItem == newItem
    }
}