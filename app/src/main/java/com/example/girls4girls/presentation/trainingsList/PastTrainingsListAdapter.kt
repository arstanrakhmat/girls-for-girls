package com.example.girls4girls.presentation.trainingsList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.girls4girls.R
import com.example.girls4girls.data.model.Data
import com.example.girls4girls.data.repository.Training
import com.example.girls4girls.databinding.ItemPastTrainingsListVerticalBinding
import com.example.girls4girls.utils.toFormattedDate

class PastTrainingsListAdapter : RecyclerView.Adapter<PastTrainingsListAdapter.ViewHolder>() {

    private lateinit var binding: ItemPastTrainingsListVerticalBinding

    private val trainingList = listOf(
        Training(
            "Финансовая грамотность",
            "23.05.2023",
            "АУЦА, 4 этаж",
            "17:00",
            "26.04.2023",
            R.drawable.main_team_4,
            R.string.training_description.toString()
        ),
        Training(
            "Учимся програмировать",
            "23.05.2023",
            "Neobis coworking",
            "17:00",
            "26.04.2023",
            R.drawable.main_team_3,
            R.string.training_description.toString()
        ),
        Training(
            "Жизнь после замужества",
            "23.05.2023",
            "Джал 17",
            "17:00",
            "26.04.2023",
            R.drawable.main_team_2,
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
        binding = ItemPastTrainingsListVerticalBinding.inflate(
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
            Glide.with(this).load(training.images[0].url).centerCrop().into(binding.ivTrainingImage)
            binding.tvTrainingTitle.text = training.title
            binding.tvDate.text = training.eventDate.toFormattedDate()
            binding.tvTime.text = training.time
            binding.tvLocation.text = training.address
//            setOnClickListener {
//                onItemClickListener?.let { it(training) }
//            }
        }
    }

    private var onItemClickListener: ((Data) -> Unit)? = null

    fun setOnItemClickListener(listener: (Data) -> Unit) {
        onItemClickListener = listener
    }
}