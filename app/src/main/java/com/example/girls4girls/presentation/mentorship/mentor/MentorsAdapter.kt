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
import com.example.girls4girls.presentation.videoblogsList.VideoblogsListViewModel.Companion.LINK
import com.example.girls4girls.presentation.videoblogsList.VideoblogsListViewModel.Companion.LINK2
import com.example.girls4girls.presentation.videoblogsList.VideoblogsListViewModel.Companion.LINK3
import com.example.girls4girls.presentation.videoblogsList.VideoblogsListViewModel.Companion.NAME1
import com.example.girls4girls.presentation.videoblogsList.VideoblogsListViewModel.Companion.NAME2
import com.example.girls4girls.presentation.videoblogsList.VideoblogsListViewModel.Companion.NAME3
import com.example.girls4girls.presentation.videoblogsList.VideoblogsListViewModel.Companion.NAME4
import com.example.girls4girls.presentation.videoblogsList.VideoblogsListViewModel.Companion.NAME5
import com.example.girls4girls.presentation.videoblogsList.VideoblogsListViewModel.Companion.TEXT1
import com.example.girls4girls.presentation.videoblogsList.VideoblogsListViewModel.Companion.TEXT2
import com.example.girls4girls.presentation.videoblogsList.VideoblogsListViewModel.Companion.TEXT3
import com.example.girls4girls.presentation.videoblogsList.VideoblogsListViewModel.Companion.TEXT4

class MentorsAdapter(): RecyclerView.Adapter<MentorsAdapter.MentorsViewHolder>() {

    var onMentorClickListener: ((Mentor) -> Unit)? = null

    val mentor1 = Mentor("Айканыш Сыдыкова", R.drawable.mentor_aikanysh_sydykova)
    val mentor2 = Mentor("Айсезим Арымбаева", R.drawable.mentor_aisezim_arymbaeva)
    val mentor3 = Mentor("Нурайым Нургазиева", R.drawable.mentor_nuraiym_nurgazieva)
    val mentor4 = Mentor("Салкынай Эмильбекова", R.drawable.mentor_salkynai_emilbekova)

    val video1 = VideoBlog(0, NAME1, "21/12/22", "Бизнес", mentor1, TEXT1,192,true, false, LINK)
    val video2 = VideoBlog(1, NAME2, "01/10/22", "Будущее", mentor2, TEXT2,12,false, true, LINK2)
    val video3 = VideoBlog(2, NAME3, "01/07/22", "Еда", mentor3, TEXT3,999,false, true, LINK3)
    val video4 = VideoBlog(3, NAME4, "08/06/22", "Образование",mentor1, TEXT4,10000, true, false, LINK2)
    val video5 = VideoBlog(4, NAME5, "02/12/21", "Программирование", mentor4, TEXT2,0,false, true, LINK)

    private val mentorsList = listOf(
        Mentor("Айканыш Сыдыкова", R.drawable.mentor_aikanysh_sydykova, listOf(video1, video3)),
        Mentor("Айсезим Арымбаева", R.drawable.mentor_aisezim_arymbaeva, listOf(video2, video4, video5)),
        Mentor("Нурайым Нургазиева", R.drawable.mentor_nuraiym_nurgazieva, listOf(video2, video3)),
        Mentor("Салкынай Эмильбекова", R.drawable.mentor_salkynai_emilbekova, listOf(video1)),
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