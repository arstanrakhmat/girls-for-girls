package com.example.girls4girls.data.model

data class UserRegistrationResponse(
    val createdAt: String,
    val dateOfBirth: Any,
    val email: String,
    val firstName: String,
    val gender: String,
    val id: Int,
    val lastName: String,
    val password: String,
    val phoneNumber: String,
    val role: String,
    val status: String,
    val updatedAt: String
)