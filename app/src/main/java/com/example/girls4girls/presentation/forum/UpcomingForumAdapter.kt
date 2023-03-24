package com.example.girls4girls.presentation.forum

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.girls4girls.R
import com.example.girls4girls.data.model.Forum
import com.example.girls4girls.databinding.ItemUpcomingTrainingBinding

class UpcomingForumAdapter : RecyclerView.Adapter<UpcomingForumAdapter.ViewHolder>() {

    private lateinit var binding: ItemUpcomingTrainingBinding

    private val forumList = listOf(
        Forum(
            "Стрессоустойчивость",
            "23.05.2023",
            "Бишкек",
            "17:00",
            "26.04.2023",
            R.drawable.main_team_2,
            R.string.training_description.toString()
        )
    )

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemUpcomingTrainingBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val forum = forumList[position]
        holder.itemView.apply {
            Glide.with(this).load(forum.image).centerCrop().into(binding.ivTrainingImage)
            binding.tvTrainingTitle.text = forum.title
            binding.tvDate.text = forum.date
            binding.tvLocation.text = forum.location
            binding.tvTime.text = forum.time
            binding.tvDeadline.text = forum.deadline
            setOnClickListener {
                onItemClickListener?.let { it(forum) }
            }
        }
    }

    override fun getItemCount(): Int {
        return forumList.size
    }

    private var onItemClickListener: ((Forum) -> Unit)? = null

    fun setOnItemClickListener(listener: (Forum) -> Unit) {
        onItemClickListener = listener
    }
}