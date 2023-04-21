package com.example.girls4girls.data.repository

import com.example.girls4girls.data.Quiz
import com.example.girls4girls.data.api.Api
import retrofit2.Response

class QuizRepository(private val api: Api) {
    suspend fun getQuiz(id: Long): Response<Quiz>{
        return api.getQuiz(id)
    }
}