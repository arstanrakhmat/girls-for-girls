package com.example.girls4girls.presentation.training

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.girls4girls.data.model.Data
import com.example.girls4girls.databinding.ItemPastTrainingBinding
import com.example.girls4girls.utils.toFormattedDate

class TrainingAdapter : RecyclerView.Adapter<TrainingAdapter.ViewHolder>() {

    private lateinit var binding: ItemPastTrainingBinding

    /**
     * DiffUtil -> calculates differences between two lists and enables us to only update
     * to those items that are different.
     *
     * To start we need callback for our async list differ
     *
     *
     */

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
        binding = ItemPastTrainingBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return ViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val training = differ.currentList[position]
        holder.itemView.apply {
            if (training.images!!.isNotEmpty()) {
                Glide.with(this).load(training.images[0].url).centerCrop()
                    .into(binding.ivTrainingImagePt)
            }
            binding.tvTrainingTitlePt.text = training.title
            binding.tvDatePt.text = training.eventDate.toFormattedDate()
            binding.tvLocationPt.text = training.address
            setOnClickListener {
                onItemClickListener?.let { it(training) }
            }
        }
    }

    private var onItemClickListener: ((Data) -> Unit)? = null

    fun setOnItemClickListener(listener: (Data) -> Unit) {
        onItemClickListener = listener
    }
}