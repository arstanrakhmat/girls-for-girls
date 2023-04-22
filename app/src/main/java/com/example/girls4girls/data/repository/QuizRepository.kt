package com.example.girls4girls.data.repository

import com.example.girls4girls.data.Quiz
import com.example.girls4girls.data.api.Api
import com.example.girls4girls.data.model.Jeton
import retrofit2.Response

class QuizRepository(private val api: Api) {
    suspend fun getQuiz(id: Long): Response<Quiz>{
        return api.getQuiz(id)
    }

    suspend fun getJeton(token: String?): Response<Jeton>{
        return api.getJeton(token)
    }
}