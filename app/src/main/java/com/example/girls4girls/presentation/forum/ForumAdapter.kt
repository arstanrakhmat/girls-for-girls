package com.example.girls4girls.presentation.forum

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.girls4girls.R
import com.example.girls4girls.data.model.Forum
import com.example.girls4girls.databinding.ItemPastTrainingBinding

class ForumAdapter : RecyclerView.Adapter<ForumAdapter.ViewHolder>() {

    private lateinit var binding: ItemPastTrainingBinding

    private val forumList = listOf(
        Forum(
            "Женский курултай",
            "19.03.2023",
            "Джал-29, 17",
            "11:30",
            "25.02.2023",
            R.drawable.event_1,
            R.string.training_description.toString()
        ),

        Forum(
            "Встреча с Гретой Турнберг",
            "11.02.2023",
            "Джал-29, 17",
            "15:40",
            "25.01.2023",
            R.drawable.event_2,
            R.string.training_description.toString()
        ),

        Forum(
            "Великие женские личности в истории",
            "11.01.2023",
            "Джал-29, 17",
            "17:55",
            "12.12.2022",
            R.drawable.event_3,
            R.string.training_description.toString()
        ),

        )

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemPastTrainingBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val forum = forumList[position]
        holder.itemView.apply {
            Glide.with(this).load(forum.image).centerCrop().into(binding.ivTrainingImagePt)
            binding.tvTrainingTitlePt.text = forum.title
            binding.tvDatePt.text = forum.date
            binding.tvLocationPt.text = forum.location
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