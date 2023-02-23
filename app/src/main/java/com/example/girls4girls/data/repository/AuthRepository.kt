package com.example.girls4girls.data.repository

import com.example.girls4girls.data.api.Api
import com.example.girls4girls.data.model.*
import retrofit2.Response

class AuthRepository(private val api: Api) {
    suspend fun userSignUp(user: UserRegistration): Response<UserRegistrationResponse> {
        return api.userSignUp(
            user.email, user.password, user.firstName, user.lastName, user.phoneNumber,
        )
    }

    suspend fun userActivate(user: UserActivate): Response<UserRegistrationResponse> {
        return api.userActivate(
            user.email, user.phoneNumber, user.code
        )
    }

    suspend fun userLogin(user: UserLogin): Response<UserLoginResponse> {
        return api.userLogin(
            user.email, user.phoneNumber, user.password
        )
    }

    suspend fun userLoginEmail(user: UserLoginEmail): Response<UserLoginResponse> {
        return api.userLoginEmail(
            user.email, user.password
        )
    }

    suspend fun userLoginPhoneNumber(user: UserLoginPhoneNumber): Response<UserLoginResponse> {
        return api.userLoginPhoneNumber(
            user.phoneNumber, user.password
        )
    }
}