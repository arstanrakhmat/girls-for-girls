package com.example.girls4girls.presentation.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.girls4girls.R
import com.example.girls4girls.data.Event
import com.example.girls4girls.data.VideoBlog
import com.example.girls4girls.databinding.ItemEventBinding
import com.example.girls4girls.databinding.ItemVideoblogBinding
import com.example.girls4girls.presentation.videoblogsList.VideoAdapter
import com.example.girls4girls.presentation.videoblogsList.VideoBlogDiffCallback
import java.text.SimpleDateFormat

class EventAdapter:ListAdapter<Event, EventAdapter.EventViewHolder>(EventDiffUtil()) {

    inner class EventViewHolder(item: View): RecyclerView.ViewHolder(item){
        private val binding = ItemEventBinding.bind(item)
        fun bind(event: Event) = with(binding){
            Glide.with(binding.root)
                .load(event.images[0].url)
                .into(binding.eventImage)
            eventType.text = event.type
            eventName.text = event.title
            eventDate.text = format(event.date)

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EventAdapter.EventViewHolder {
        val view = LayoutInflater.from((parent.context)).inflate(R.layout.item_event, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventAdapter.EventViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun format(inputEventDate: String): String{
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        val outputFormat = SimpleDateFormat("dd/MM/yyyy")
        val inputDate = inputFormat.parse(inputEventDate)
        return outputFormat.format(inputDate)
    }

    class EventDiffUtil: DiffUtil.ItemCallback<Event>(){
        override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
            return oldItem == newItem
        }

    }
}