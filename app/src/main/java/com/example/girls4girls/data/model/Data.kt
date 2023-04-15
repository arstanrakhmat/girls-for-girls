package com.example.girls4girls.data.model

data class Data(
    val address: String?,
    val createdAt: String,
    val deadlineDate: String,
    val description: String,
    val eventDate: String,
    val id: Int,
    val images: List<Image>?,
    val location: String?,
    val time: String?,
    val title: String,
    val updatedAt: String
) : java.io.Serializable