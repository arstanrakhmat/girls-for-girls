package com.example.girls4girls.data.model

data class Jeton(
    val cardInfo: CardInfo,
    val createdAt: String,
    val description: String,
    val descriptionKG: String,
    val id: Int,
    val image: Image,
    val isDeleted: Boolean,
    val quantityToGet: Int,
    val title: String,
    val titleKG: String,
    val type: String,
    val updatedAt: String
): java.io.Serializable