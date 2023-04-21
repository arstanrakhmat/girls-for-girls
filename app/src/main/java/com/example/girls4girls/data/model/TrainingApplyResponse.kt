package com.example.girls4girls.data.model

data class TrainingApplyResponse(
    val applyStatus: String,
    val createdAt: String,
    val id: Int,
    val training: Training,
    val updatedAt: String,
    val user: UserX
)