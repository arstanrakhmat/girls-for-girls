package com.example.girls4girls.presentation.videoblogsList

import androidx.lifecycle.ViewModel
import com.example.girls4girls.R
import com.example.girls4girls.data.Mentor
import com.example.girls4girls.data.VideoBlog

class VideoblogsListViewModel : ViewModel() {

    val mentor1 = Mentor("Айканыш Сыдыкова", R.drawable.mentor_aikanysh_sydykova)
    val mentor2 = Mentor("Айсезим Арымбаева", R.drawable.mentor_aisezim_arymbaeva)
    val mentor3 = Mentor("Нурайым Нургазиева", R.drawable.mentor_nuraiym_nurgazieva)
    val mentor4 = Mentor("Салкынай Эмильбекова", R.drawable.mentor_salkynai_emilbekova)

    val video1 = VideoBlog(0, "Video 1", "21/12/22", "Бизнес", mentor1, TEXT,192,"12:18",true, false, LINK)
    val video2 = VideoBlog(1, "Video 2", "01/10/22", "Будущее", mentor2, TEXT,12,"15:30",false, true, LINK2)
    val video3 = VideoBlog(2, "Video 3", "01/07/22", "Еда", mentor3, TEXT,999,"31:16",false, true, LINK3)
    val video4 = VideoBlog(3, "Video 4", "08/06/22", "Образование",mentor1, TEXT,10000, "15:00",true, false, LINK2)
    val video5 = VideoBlog(4, "Video 5", "02/12/21", "Программирование", mentor4, TEXT,0,"43:11",false, true, LINK)

    val videoList = listOf<VideoBlog>(
        video1,
        video2,
        video3,
        video4,
        video5
    )

    companion object{
        val TEXT = "In up so discovery my middleton eagerness dejection explained. Estimating excellence ye contrasted insensible as. Oh up unsatiable advantages decisively as at interested. Present suppose in esteems in demesne colonel it to. End horrible she landlord screened stanhill. Repeated offended you opinions off dissuade ask packages screened. She alteration everything sympathize impossible his get compliment. Collected few extremity suffering met had sportsman."
        val LINK = "https://www.youtube.com/watch?v=l-ooCKssPv4"
        val LINK2 = "https://www.youtube.com/watch?v=zvTr3P43yUg"
        val LINK3 = "https://www.youtube.com/watch?v=_jwBhhbf-PQ"
    }
}