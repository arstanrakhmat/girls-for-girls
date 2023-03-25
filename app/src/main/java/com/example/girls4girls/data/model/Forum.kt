package com.example.girls4girls.data.model

data class Forum(
    val title: String,
    val date: String,
    val location: String,
    val time: String,
    val deadline: String,
    val image: Int,
    val description: String
) : java.io.Serializable
