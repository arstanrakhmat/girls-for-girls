package com.example.girls4girls.presentation.mentorship.mentor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.girls4girls.R
import com.example.girls4girls.data.Mentor
import com.example.girls4girls.data.VideoBlog
import com.example.girls4girls.databinding.ItemMentorVideoBinding

class MentorVideoAdapter: ListAdapter<VideoBlog, MentorVideoAdapter.MentorVideoViewholder>(MentorVideoDiffUtil()) {

    var onVideoClickListener: ((VideoBlog) -> Unit)? = null

    class MentorVideoDiffUtil: DiffUtil.ItemCallback<VideoBlog>(){
        override fun areItemsTheSame(oldItem: VideoBlog, newItem: VideoBlog): Boolean {
            return oldItem.link == newItem.link
        }

        override fun areContentsTheSame(oldItem: VideoBlog, newItem: VideoBlog): Boolean {
            return oldItem == newItem
        }

    }

    inner class MentorVideoViewholder(item: View): RecyclerView.ViewHolder(item){
        private val binding = ItemMentorVideoBinding.bind(item)
        fun bind(video: VideoBlog) = with(binding){
            mentorVideoTitle.text = video.title
            mentorVideoDate.text = video.date
            mentorVideoViews.text = video.views.toString()

            mentorVideoItem.setOnClickListener {
                onVideoClickListener?.invoke(video)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MentorVideoViewholder {
        val view = LayoutInflater.from((parent.context)).inflate(R.layout.item_mentor_video, parent, false)
        return MentorVideoViewholder(view)
    }

    override fun onBindViewHolder(holder: MentorVideoViewholder, position: Int) {
        holder.bind(getItem(position))
    }


}