package com.example.girls4girls.data.repository

import com.example.girls4girls.data.model.TrainingResponse
import com.example.girls4girls.data.api.Api
import com.example.girls4girls.data.model.TrainingById
import retrofit2.Response

class ForumRepository(private val api: Api) {
    suspend fun getUpcomingForums(
        page: Int,
        limit: Int,
        order: String,
        orderField: String
    ): Response<TrainingResponse> {
        return api.getUpcomingForums(page, limit, order, orderField)
    }

    suspend fun getPastForums(
        page: Int,
        limit: Int,
        order: String,
        orderField: String
    ): Response<TrainingResponse> {
        return api.getPastForums(page, limit, order, orderField)
    }

    suspend fun getForumById(id: Int): Response<TrainingById> {
        return api.getForumById(id)
    }
}