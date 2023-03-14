package com.example.girls4girls.presentation.training

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.girls4girls.R
import com.example.girls4girls.data.repository.Training
import com.example.girls4girls.databinding.ItemPastTrainingBinding

class TrainingAdapter : RecyclerView.Adapter<TrainingAdapter.ViewHolder>() {

    private lateinit var binding: ItemPastTrainingBinding

    private val trainingList = listOf(
        Training(
            "Title",
            "23.05.2023",
            "Bishkek",
            "17:00",
            "26.04.2023",
            R.drawable.main_team_4,
            R.string.training_description.toString()
        ),
        Training(
            "Title2", "23.05.2023", "Bishkek", "17:00", "26.04.2023", R.drawable.main_team_3,
            R.string.training_description.toString()
        ),
        Training(
            "Title3", "23.05.2023", "Bishkek", "17:00", "26.04.2023", R.drawable.main_team_2,
            R.string.training_description.toString()
        ),
    )

    /**
     * DiffUtil -> calculates differences between two lists and enables us to only update
     * to those items that are different.
     *
     * To start we need callback for our async list differ
     *
     *
     */

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<Training>() {
        override fun areItemsTheSame(oldItem: Training, newItem: Training): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Training, newItem: Training): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemPastTrainingBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return ViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
//        return differ.currentList.size
        return trainingList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val training = trainingList[position]
        holder.itemView.apply {
            Glide.with(this).load(training.image).centerCrop().into(binding.ivTrainingImagePt)
            binding.tvTrainingTitlePt.text = training.title
            binding.tvDatePt.text = training.date
            binding.tvLocationPt.text = training.location
            setOnClickListener {
                onItemClickListener?.let { it(training) }
            }
        }
    }

    private var onItemClickListener: ((Training) -> Unit)? = null

    fun setOnItemClickListener(listener: (Training) -> Unit) {
        onItemClickListener = listener
    }
}