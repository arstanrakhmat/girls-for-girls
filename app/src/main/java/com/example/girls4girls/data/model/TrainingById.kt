package com.example.girls4girls.data.model

data class TrainingById(
    val address: String,
    val createdAt: String,
    val deadlineDate: String,
    val description: String,
    val descriptionKG: Any,
    val eventDate: String,
    val id: Int,
    val images: List<ImageX>?,
    val isDeleted: Boolean,
    val lecturers: List<Any>,
    val location: String,
    val locationKG: Any,
    val questionnaire: Questionnaire,
    val time: String,
    val title: String,
    val titleKG: Any,
    val updatedAt: String,
    val userToTraining: List<UserToTraining>
)