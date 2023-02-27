package com.example.girls4girls.presentation.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.girls4girls.R
import com.example.girls4girls.databinding.ItemImageBinding

class ImagePagerAdapter(): RecyclerView.Adapter<ImagePagerAdapter.PhotoViewHolder>() {

    private val photoList = listOf(R.drawable.main_team_1,
        R.drawable.main_team_2,
        R.drawable.main_team_3,
        R.drawable.main_team_4,)

    inner class PhotoViewHolder(item: View): RecyclerView.ViewHolder(item){
        private val binding = ItemImageBinding.bind(item)
        fun bind(image: Int){
            Glide.with(binding.root.context)
                .load(image)
                .into(binding.teamImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val view = LayoutInflater.from((parent.context)).inflate(R.layout.item_image, parent, false)
        return PhotoViewHolder(view)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(photoList[position])
    }

    override fun getItemCount(): Int = photoList.size
}