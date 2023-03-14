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
import com.example.girls4girls.presentation.videoblogsList.VideoblogsListViewModel

class MentorsAdapter(): RecyclerView.Adapter<MentorsAdapter.MentorsViewHolder>() {

    var onMentorClickListener: ((Mentor) -> Unit)? = null

    val video1 = VideoBlog(0, "Video 1", "21/12/22", "Бизнес", "Ширин Айтмак", VideoblogsListViewModel.TEXT,192,false, false, VideoblogsListViewModel.LINK)
    val video2 = VideoBlog(1, "Video 2", "01/10/22", "Будущее", "Нельсон Вумандела", VideoblogsListViewModel.TEXT,12,false, false, VideoblogsListViewModel.LINK2)
    val video3 = VideoBlog(2, "Video 3", "01/07/22", "Еда", "Unknown", VideoblogsListViewModel.TEXT,999,false, false, VideoblogsListViewModel.LINK)
    val video4 = VideoBlog(3, "Video 4", "08/06/22", "Образование","Султан Сарыгулов", VideoblogsListViewModel.TEXT,10000, false, false, VideoblogsListViewModel.LINK2)
    val video5 = VideoBlog(4, "Video 5", "02/12/21", "Программирование", "Какой-то чувак", VideoblogsListViewModel.TEXT,0,false, false, VideoblogsListViewModel.LINK)

    private val mentorsList = listOf(
        Mentor("Айканыш Сыдыкова", R.drawable.mentor_aikanysh_sydykova, listOf(video1, video3)),
        Mentor("Айсезим Арымбаева", R.drawable.mentor_aisezim_arymbaeva, listOf(video2, video4, video5)),
        Mentor("Нурайым Нургазиева", R.drawable.mentor_nuraiym_nurgazieva, listOf(video2, video3)),
        Mentor("Салкынай Эмильбекова", R.drawable.mentor_salkynai_emilbekova, listOf(video1)))

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