package com.example.girls4girls.presentation.videoblogsList

import androidx.lifecycle.ViewModel
import com.example.girls4girls.data.VideoBlog

class VideoblogsListViewModel : ViewModel() {
    val videos = mutableListOf<VideoBlog>()

    val videoList = listOf<VideoBlog>(
        VideoBlog(0, "Video 1", 60, "Business", "Ширин Айтмак", TEXT,192,false, false, LINK),
        VideoBlog(1, "Video 2", 60, "Future", "Нельсон Вумандела", TEXT,12,false, false, LINK2),
        VideoBlog(2, "Video 3", 60, "Food", "Unknown", TEXT,999,false, false, LINK),
        VideoBlog(3, "Video 4", 60, "Education","Султан Сарыгулов", TEXT,10000, false, false, LINK2),
        VideoBlog(4, "Video 5", 60, "Periods", "Какой-то чувак", TEXT,0,false, false, LINK),
    )

    companion object{
        val TEXT = "In up so discovery my middleton eagerness dejection explained. Estimating excellence ye contrasted insensible as. Oh up unsatiable advantages decisively as at interested. Present suppose in esteems in demesne colonel it to. End horrible she landlord screened stanhill. Repeated offended you opinions off dissuade ask packages screened. She alteration everything sympathize impossible his get compliment. Collected few extremity suffering met had sportsman."
        val LINK = "https://www.youtube.com/watch?v=l-ooCKssPv4"
        val LINK2 = "https://www.youtube.com/watch?v=zvTr3P43yUg"
    }
}