package com.example.girls4girls.data.model

data class UserAllData(
    val character: Any,
    val confirmed: Boolean,
    val createdAt: String,
    val dateOfBirth: String?,
    val email: String,
    val firstName: String,
    val gender: String?,
    val id: Int,
    val image: Image?,
    val isDeleted: Boolean,
    val jetons: List<Any>,
    val lastName: String,
    val password: String,
    val phoneNumber: String,
    val region: String?,
    val role: String,
    val status: String,
    val updatedAt: String
)