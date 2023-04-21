package com.example.girls4girls.data.model

data class UserUpdate(
    val email: String,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val dateOfBirth: String?,
    val gender: String?,
    val region: String?
)