package com.example.girls4girls.data.repository

import com.example.girls4girls.data.api.Api
import com.example.girls4girls.data.model.*
import okhttp3.MultipartBody
import retrofit2.Response

class UserRepository(private val api: Api) {
    suspend fun getUser(string: String?): Response<User> {
        return api.getUser(string)
    }

    suspend fun getAllUserInfo(token: String): Response<UserAllDataNewVersion> {
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
    ): Response<UserAllDataNewVersion> {
        return api.postPhoto(
            token, image
        )
    }
}