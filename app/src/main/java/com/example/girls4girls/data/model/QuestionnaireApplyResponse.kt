package com.example.girls4girls.data.model

data class QuestionnaireApplyResponse(
    val createdAt: String,
    val id: Int,
    val questionnaire: QuestionnaireX,
    val updatedAt: String,
    val user: UserX
)