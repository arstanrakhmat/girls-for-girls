package com.example.girls4girls.data

import com.google.gson.annotations.SerializedName

data class VideosList(
    @SerializedName("data")
    val videosList: List<VideoBlog>
)
