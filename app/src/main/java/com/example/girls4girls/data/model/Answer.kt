package com.example.girls4girls.data.model

data class Answer(
    val questionId: Int,
    val type: String,
    val answerIndex: Int? = null,
    val multipleChoices: List<Int>? = null ,
    var text: String? = null
)