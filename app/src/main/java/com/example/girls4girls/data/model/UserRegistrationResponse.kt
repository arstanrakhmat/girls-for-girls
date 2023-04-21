package com.example.girls4girls.data.model

data class UserRegistrationResponse(
    val confirmed: Boolean,
    val createdAt: String,
    val dateOfBirth: Any,
    val email: String,
    val firstName: String,
    val gender: String,
    val id: Int,
    val isDeleted: Boolean,
    val lastName: String,
    val password: String,
    val phoneNumber: String,
    val region: Any,
    val role: String,
    val status: String,
    val updatedAt: String
)