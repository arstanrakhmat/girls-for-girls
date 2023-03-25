package com.example.girls4girls.data.repository

import com.example.girls4girls.data.api.Api
import com.example.girls4girls.data.model.Message
import com.example.girls4girls.data.model.ResetPasswordConfirmResponse
import retrofit2.Response

class ResetPasswordRepository(private val api: Api) {
    suspend fun resetPasswordEmail(email: String): Response<Message> {
        return api.resetPasswordEmail(email)
    }

    suspend fun resetPasswordEmailConfirm(email: String, code: String): Response<ResetPasswordConfirmResponse> {
        return api.resetPasswordEmailConfirm(email, code)
    }

    suspend fun changePassword(token: String?, newPassword: String): Response<Message> {
        return api.changePassword(token, newPassword)
    }
}