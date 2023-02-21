package com.example.girls4girls.presentation.welcome

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.girls4girls.R

class ViewPagerAdapterWelcome(private var title: List<String>, private var images: List<Int>) :
    RecyclerView.Adapter<ViewPagerAdapterWelcome.Pager2ViewHolder>() {

    inner class Pager2ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val itemTitle: TextView = itemView.findViewById(R.id.tvTitle_welcome_page)
        val itemImage: ImageView = itemView.findViewById(R.id.tvImage_welcome_page)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Pager2ViewHolder {
        return Pager2ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.welcome_item_page, parent, false)
        )
    }

    override fun onBindViewHolder(holder: Pager2ViewHolder, position: Int) {
        holder.itemTitle.text = title[position]
        holder.itemImage.setImageResource(images[position])
    }

    override fun getItemCount(): Int {
        return title.size
    }
}