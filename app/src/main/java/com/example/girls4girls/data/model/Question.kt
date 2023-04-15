package com.example.girls4girls.data.model

data class Question(
    val createdAt: String,
    val description: Any,
    val descriptionKG: Any,
    val id: Int,
    val text: String,
    val textKG: Any,
    val type: String,
    val updatedAt: String,
    val variants: List<Variant>
)