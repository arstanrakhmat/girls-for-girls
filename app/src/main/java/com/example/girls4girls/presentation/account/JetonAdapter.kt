package com.example.girls4girls.presentation.account

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.girls4girls.data.model.Jeton
import com.example.girls4girls.databinding.ItemJetonCardBinding

class JetonAdapter : RecyclerView.Adapter<JetonAdapter.ViewHolder>() {

    private lateinit var binding: ItemJetonCardBinding

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<Jeton>() {
        override fun areItemsTheSame(oldItem: Jeton, newItem: Jeton): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Jeton, newItem: Jeton): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemJetonCardBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val jeton = differ.currentList[position]
        holder.itemView.apply {
            if (jeton.type == "CARD") {
                Glide.with(this).load(jeton.image.url).centerCrop()
                    .into(binding.jetonImage)
                binding.jetonName.text = jeton.title
                binding.jetonDescription.text = jeton.description
                setOnClickListener {
                    onItemClickListener?.let { it(jeton) }
                }
            }
        }
    }

    private var onItemClickListener: ((Jeton) -> Unit)? = null

    fun setOnJetonClickListener(listener: (Jeton) -> Unit) {
        onItemClickListener = listener
    }
}