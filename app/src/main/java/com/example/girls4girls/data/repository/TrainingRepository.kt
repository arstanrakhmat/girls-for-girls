package com.example.girls4girls.data.repository

import com.example.girls4girls.data.api.Api
import com.example.girls4girls.data.model.TrainingById
import com.example.girls4girls.data.model.TrainingResponse
import retrofit2.Response

class TrainingRepository(private val api: Api) {
    suspend fun getUpcomingTrainings(
        page: Int,
        limit: Int,
        order: String,
        orderField: String
    ): Response<TrainingResponse> {
        return api.getUpcomingTrainings(page, limit, order, orderField)
    }

    suspend fun getPastTrainings(
        page: Int,
        limit: Int,
        order: String,
        orderField: String
    ): Response<TrainingResponse> {
        return api.getPastTrainings(page, limit, order, orderField)
    }

    suspend fun getTrainingById(id: Int) : Response<TrainingById> {
        return api.getTrainingById(id)
    }

}