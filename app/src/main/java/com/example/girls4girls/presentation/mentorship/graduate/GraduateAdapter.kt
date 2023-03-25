package com.example.girls4girls.presentation.mentorship.graduate

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.girls4girls.R
import com.example.girls4girls.databinding.ItemGraduateBinding

class GraduateAdapter: RecyclerView.Adapter<GraduateAdapter.GraduateViewHolder>() {

    data class Graduate(
        val name: String,
        val city: String,
        val age: Int,
        val review: String
    )

    val graduatesList = listOf(
        Graduate("Салтанат Алиева", "Бишкек", 16, "Я получила от программы больше, чем ожидала!"),
        Graduate("Миргуль Андерасян", "Ош", 18, "Мне очень понравились менторши."),
        Graduate("Алия Ботонова", "Баткен", 15, "Спасибо Girls4Girls, я много чего узнала."),

    )

    inner class GraduateViewHolder(item: View): RecyclerView.ViewHolder(item){
        val binding = ItemGraduateBinding.bind(item)
        fun bind(graduate: Graduate){
            binding.graduateName.text = graduate.name
            binding.graduateAge.text = "${graduate.age} лет,"
            binding.graduateCity.text = graduate.city
            binding.graduateReview.text = graduate.review
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