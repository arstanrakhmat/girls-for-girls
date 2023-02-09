package com.example.girls4girls.presentation.videoblogsList

import androidx.lifecycle.ViewModel
import com.example.girls4girls.data.VideoBlog

class VideoblogsListViewModel : ViewModel() {
    val videos = mutableListOf<VideoBlog>()

    val videoList = listOf<VideoBlog>(
        VideoBlog(0, "Video 1", 60, "Sex", false, false),
        VideoBlog(1, "Video 2", 60, "Future", false, false),
        VideoBlog(2, "Video 3", 60, "Food", false, false),
        VideoBlog(3, "Video 4", 60, "Education", false, false),
        VideoBlog(4, "Video 5", 60, "Periods", false, false),
    )
}