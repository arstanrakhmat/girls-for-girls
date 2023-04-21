package com.example.girls4girls.presentation.forum

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.girls4girls.data.model.Data
import com.example.girls4girls.databinding.ItemUpcomingTrainingBinding
import com.example.girls4girls.utils.toFormattedDate

class UpcomingForumAdapter : RecyclerView.Adapter<UpcomingForumAdapter.ViewHolder>() {

    private lateinit var binding: ItemUpcomingTrainingBinding

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<Data>() {
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemUpcomingTrainingBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val forum = differ.currentList[position]
        holder.itemView.apply {
            if (forum.images!!.isNotEmpty()) {
                Glide.with(this).load(forum.images[0].url).centerCrop()
                    .into(binding.ivTrainingImage)
            }
            binding.tvTrainingTitle.text = forum.title
            binding.tvDate.text = forum.eventDate.toFormattedDate()
            binding.tvLocation.text = forum.location
            binding.tvTime.text = forum.time
            binding.tvDeadline.text = forum.deadlineDate.toFormattedDate()
            setOnClickListener {
                onItemClickListener?.let { it(forum) }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((Data) -> Unit)? = null

    fun setOnItemClickListener(listener: (Data) -> Unit) {
        onItemClickListener = listener
    }
}