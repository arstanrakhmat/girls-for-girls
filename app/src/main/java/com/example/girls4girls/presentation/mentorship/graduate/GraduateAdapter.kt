package com.example.girls4girls.presentation.mentorship.graduate

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.girls4girls.R
import com.example.girls4girls.databinding.ItemGraduateBinding

class GraduateAdapter: RecyclerView.Adapter<GraduateAdapter.GraduateViewHolder>() {

    data class Graduate(
        val id: Long,
        val name: String,
        val city: String,
        val age: Int,
        val review: String
    )

    val graduatesList = listOf(1,2,3,4,5)

    inner class GraduateViewHolder(item: View): RecyclerView.ViewHolder(item){
        val binding = ItemGraduateBinding.bind(item)
        fun bind(item: Int){
            binding.graduateName.text = item.toString()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GraduateViewHolder {
        val view = LayoutInflater.from((parent.context)).inflate(R.layout.item_graduate, parent, false)
        return GraduateViewHolder(view)
    }

    override fun onBindViewHolder(holder: GraduateViewHolder, position: Int) {
        holder.bind(graduatesList[position])
    }

    override fun getItemCount(): Int = graduatesList.size
}