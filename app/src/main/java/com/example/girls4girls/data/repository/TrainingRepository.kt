package com.example.girls4girls.data.repository

import com.example.girls4girls.data.api.Api
import com.example.girls4girls.data.model.*
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

    suspend fun getTrainingById(id: Int): Response<TrainingById> {
        return api.getTrainingById(id)
    }

    suspend fun questionnaireApply2(token: String?, answers: QuestionnaireFillOut):
            Response<QuestionnaireApplyResponse> {
        return api.questionnaireApply2(token, answers)
    }

    suspend fun trainingApply(
        token: String?,
        trainingId: Int,
        questionnaireResponseId: Int
    ): Response<TrainingApplyResponse> {
        return api.trainingApply(token, trainingId, questionnaireResponseId)
    }

}