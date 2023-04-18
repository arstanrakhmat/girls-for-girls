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
import com.example.girls4girls.presentation.videoblog.VideoblogFragment.Companion.TAG
import java.text.SimpleDateFormat
import java.util.*

class VideoAdapter: ListAdapter<VideoBlog, VideoAdapter.VideoBlogViewHolder>(VideoBlogDiffCallback()) {

    var unfilteredList = listOf<VideoBlog>()

    var onVideoClickListener: ((VideoBlog) -> Unit)? = null

    class VideoBlogViewHolder(item: View, private val onVideoClickListener: ((VideoBlog) -> Unit)?): RecyclerView.ViewHolder(item){
        private val binding = ItemVideoblogBinding.bind(item)
        fun bind(videoBlog: VideoBlog) = with(binding){

            videoTitle.text = videoBlog.title
            videoSpeaker.text = videoBlog.lecturerName
            videoViews.text = videoBlog.views.toString()

            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            val outputFormat = SimpleDateFormat("dd/MM/yyyy")
            val inputDate = inputFormat.parse(videoBlog.date)
            val outputDate = outputFormat.format(inputDate)
            videoDate.text = outputDate

            if (videoBlog.isLiked){
                likeButton.setImageResource(R.drawable.ic_heart_filled)
            } else {
                likeButton.setImageResource(R.drawable.ic_heart)
            }

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
        Log.d(TAG, "filter: ${list}")

        submitList(list)
    }
}