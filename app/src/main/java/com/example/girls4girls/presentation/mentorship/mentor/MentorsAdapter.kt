package com.example.girls4girls.presentation.mentorship.mentor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.girls4girls.R
import com.example.girls4girls.data.Mentor
import com.example.girls4girls.data.VideoBlog
import com.example.girls4girls.databinding.ItemMentorshipBinding

class MentorsAdapter(): RecyclerView.Adapter<MentorsAdapter.MentorsViewHolder>() {

    var onMentorClickListener: ((Mentor) -> Unit)? = null

    private val mentorsList = listOf<Mentor>(
        Mentor("Айканыш Сыдыкова", R.drawable.mentor_aikanysh_sydykova),
        Mentor("Айсезим Арымбаева", R.drawable.mentor_aisezim_arymbaeva),
        Mentor("Салкынай Эмилбекова", R.drawable.mentor_salkynai_emilbekova),
        Mentor("Нурайым Нургазиева", R.drawable.mentor_nuraiym_nurgazieva),
    )

    inner class MentorsViewHolder(
        item: View,
        private val onMentorClickListener: ((Mentor) -> Unit)?
    ): RecyclerView.ViewHolder(item){

        private val binding = ItemMentorshipBinding.bind(item)
        fun bind(mentor: Mentor){
            binding.mentorshiperName.text = mentor.name
            Glide
                .with(binding.root)
                .load(mentor.image)
                .into(binding.mentorshiperImage)
            binding.mentorItem.setOnClickListener {
                onMentorClickListener?.invoke(mentor)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MentorsViewHolder {
        val view = LayoutInflater.from((parent.context)).inflate(R.layout.item_mentorship, parent, false)
        return MentorsViewHolder(view, onMentorClickListener)
    }

    override fun onBindViewHolder(holder: MentorsViewHolder, position: Int) {
        holder.bind(mentorsList[position])
    }

    override fun getItemCount(): Int = mentorsList.size

//    inner class MentorshiperUtilCallback: DiffUtil.ItemCallback<Mentorshiper>
}