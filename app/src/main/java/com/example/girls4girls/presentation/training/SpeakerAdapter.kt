package com.example.girls4girls.presentation.training

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.girls4girls.R
import com.example.girls4girls.data.model.Speaker
import com.example.girls4girls.databinding.ItemSpeakerBinding

class SpeakerAdapter : RecyclerView.Adapter<SpeakerAdapter.ViewHolder>() {

    private lateinit var binding: ItemSpeakerBinding

    private val speakerList = listOf(
        Speaker(
            "Aikanysh Sydykova",
            "Mentor, co-founder",
            R.drawable.aikanysh_sydykova
        )
    )

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

//    private val differCallback = object : DiffUtil.ItemCallback<Speaker>() {
//        override fun areItemsTheSame(oldItem: Speaker, newItem: Speaker): Boolean {
//            return oldItem.name == newItem.name
//        }
//
//        override fun areContentsTheSame(oldItem: Speaker, newItem: Speaker): Boolean {
//            return oldItem == newItem
//        }
//
//    }

//    private val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemSpeakerBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val speaker = speakerList[position]
        holder.itemView.apply {
            Glide.with(this).load(speaker.image).centerCrop().into(binding.speakerImage)
            binding.speakerName.text = speaker.name
            binding.speakerInfo.text = speaker.info
        }
    }

    override fun getItemCount(): Int {
        return speakerList.size
    }

//    private var onItemClickListener: ((Speaker) -> Unit)? = null

//    fun setOnItemClickListener(listener: (Speaker) -> Unit) {
//        onItemClickListener = listener
//    }
}