package com.example.girls4girls.presentation.videoblogsList

import androidx.lifecycle.ViewModel
import com.example.girls4girls.data.VideoBlog

class VideoblogsListViewModel : ViewModel() {

    val video1 = VideoBlog(0, "Video 1", "21/12/22", "Бизнес", "Ширин Айтмак", TEXT,192,"12:18",true, false, LINK)
    val video2 = VideoBlog(1, "Video 2", "01/10/22", "Будущее", "Нельсон Вумандела", TEXT,12,"15:30",false, true, LINK2)
    val video3 = VideoBlog(2, "Video 3", "01/07/22", "Еда", "Unknown", TEXT,999,"31:16",false, true, LINK3)
    val video4 = VideoBlog(3, "Video 4", "08/06/22", "Образование","Султан Сарыгулов", TEXT,10000, "15:00",true, false, LINK2)
    val video5 = VideoBlog(4, "Video 5", "02/12/21", "Программирование", "Какой-то чувак", TEXT,0,"43:11",false, true, LINK)

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