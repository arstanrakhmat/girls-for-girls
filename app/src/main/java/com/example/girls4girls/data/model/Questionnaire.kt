package com.example.girls4girls.data.model

data class Questionnaire(
    val createdAt: String,
    val description: Any,
    val descriptionKG: Any,
    val id: Int,
    val name: String,
    val nameKG: Any,
    val questions: List<Question>,
    val updatedAt: String
)