package com.example.girls4girls.data

import com.google.gson.annotations.SerializedName

data class Event(
    val id: Long,
    val type: String,
    val title: String,
    @SerializedName("eventDate")
    val date: String,
    val images: List<Image>,
    val description: String,
)
