package com.example.girls4girls.presentation.question

import androidx.lifecycle.ViewModel
import com.example.girls4girls.data.Question

class QuestionViewModel: ViewModel() {

    val questions = listOf(
        Question("9+10", listOf("21", "1", "2", "3")),
        Question("2+2", listOf("22", "1", "2", "3")),
        Question("250 / 2", listOf("125", "1", "3", "21"))
    )
}