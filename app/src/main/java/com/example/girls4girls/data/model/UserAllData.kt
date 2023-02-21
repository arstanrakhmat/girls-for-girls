package com.example.girls4girls.data.model

data class UserAllData(
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val email: String,
    val password: String,
    val region: String? = null,
    val birthDate: String? = null,
    val gender: String? = null
) : java.io.Serializable
