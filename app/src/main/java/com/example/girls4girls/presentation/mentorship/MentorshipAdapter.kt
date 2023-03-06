package com.example.girls4girls.presentation.mentorship

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.girls4girls.R
import com.example.girls4girls.databinding.ItemImageBinding
import com.example.girls4girls.databinding.ItemMentorshipBinding
import com.example.girls4girls.presentation.home.ImagePagerAdapter

class MentorshipAdapter(): RecyclerView.Adapter<MentorshipAdapter.MentorsViewHolder>() {

    data class Mentorshiper(
        val name: String,
        val image: Int
    )

    private val mentorsList = listOf(
        Mentorshiper("Айканыш Сыдыкова", R.drawable.mentor_aikanysh_sydykova),
        Mentorshiper("Айсезим Арымбаева", R.drawable.mentor_aisezim_arymbaeva),
        Mentorshiper("Нурайым Нургазиева", R.drawable.mentor_nuraiym_nurgazieva),
        Mentorshiper("Салкынай Эмильбекова", R.drawable.mentor_salkynai_emilbekova))

    inner class MentorsViewHolder(item: View): RecyclerView.ViewHolder(item){
        private val binding = ItemMentorshipBinding.bind(item)
        fun bind(mentorshiper: Mentorshiper){
            binding.mentorshiperName.text = mentorshiper.name
            Glide
                .with(binding.root)
                .load(mentorshiper.image)
                .into(binding.mentorshiperImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MentorsViewHolder {
        val view = LayoutInflater.from((parent.context)).inflate(R.layout.item_mentorship, parent, false)
        return MentorsViewHolder(view)
    }

    override fun onBindViewHolder(holder: MentorsViewHolder, position: Int) {
        holder.bind(mentorsList[position])
    }

    override fun getItemCount(): Int = mentorsList.size

//    inner class MentorshiperUtilCallback: DiffUtil.ItemCallback<Mentorshiper>
}