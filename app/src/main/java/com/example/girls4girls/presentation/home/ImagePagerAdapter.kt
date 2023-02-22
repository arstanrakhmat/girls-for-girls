package com.example.girls4girls.presentation.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.girls4girls.R
import com.example.girls4girls.databinding.ItemImageBinding

class ImagePagerAdapter(): RecyclerView.Adapter<ImagePagerAdapter.PhotoViewHolder>() {

    private val photoList = listOf(R.drawable.main_team,
        R.drawable.main_team,
        R.drawable.main_team,
        R.drawable.main_team,)

    inner class PhotoViewHolder(item: View): RecyclerView.ViewHolder(item){
        private val binding = ItemImageBinding.bind(item)
        fun bind(image: Int){
            binding.teamImage.setImageResource(image)
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