package com.example.girls4girls.data.repository

import com.example.girls4girls.data.api.Api
import com.example.girls4girls.data.model.User
import retrofit2.Response

class UserRepository(private val api: Api) {
    suspend fun getUser(string: String?): Response<User> {
        return api.getUser(string)
    }
}