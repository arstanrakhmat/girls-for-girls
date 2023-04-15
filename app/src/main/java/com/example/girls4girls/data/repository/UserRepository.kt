package com.example.girls4girls.data.repository

import com.example.girls4girls.data.api.Api
import com.example.girls4girls.data.model.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Part

class UserRepository(private val api: Api) {
    suspend fun getUser(string: String?): Response<User> {
        return api.getUser(string)
    }

    suspend fun getAllUserInfo(token: String): Response<UserAllData> {
        return api.getAllUserInfo(token)
    }

    suspend fun userUpdate(
        token: String,
        userUpdate: UserUpdate
    ): Response<UserRegistrationResponse> {
        return api.userUpdate(
            token,
            userUpdate.email, userUpdate.firstName,
            userUpdate.lastName, userUpdate.phoneNumber, userUpdate.dateOfBirth,
            userUpdate.gender, userUpdate.region
        )
    }

    suspend fun userUpdate2(
        token: String,
        userUpdate: UserUpdate2
    ): Response<UserRegistrationResponse> {
        return api.userUpdate2(
            token,
            userUpdate.dateOfBirth,
            userUpdate.gender, userUpdate.region
        )
    }

    suspend fun postPhoto(
        token: String,
        image: MultipartBody.Part
    ): Response<UserAllData> {
        return api.postPhoto(
            token, image
        )
    }
}