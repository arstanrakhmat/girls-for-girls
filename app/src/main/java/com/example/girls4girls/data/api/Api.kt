package com.example.girls4girls.data.api

import com.example.girls4girls.data.model.UserLoginResponse
import com.example.girls4girls.data.model.UserRegistrationResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Api {

    @FormUrlEncoded
    @POST("auth/signup")
    suspend fun userSignUp(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("firstName") firstName: String,
        @Field("lastName") lastName: String,
        @Field("phoneNumber") phoneNumber: String,
    ): Response<UserRegistrationResponse>

    @FormUrlEncoded
    @POST("auth/confirm")
    suspend fun userActivate(
        @Field("email") email: String,
        @Field("phoneNumber") phoneNumber: String,
        @Field("code") code: String
    ) : Response<UserRegistrationResponse>

    @FormUrlEncoded
    @POST("auth/login")
    suspend fun userLogin(
        @Field("email") email: String,
        @Field("phoneNumber") phoneNumber: String,
        @Field("password") password: String
    ) : Response<UserLoginResponse>

    @FormUrlEncoded
    @POST("auth/login")
    suspend fun userLoginEmail(
        @Field("email") email: String,
        @Field("password") password: String
    ) : Response<UserLoginResponse>

    @FormUrlEncoded
    @POST("auth/login")
    suspend fun userLoginPhoneNumber(
        @Field("phoneNumber") phoneNumber: String,
        @Field("password") password: String
    ) : Response<UserLoginResponse>
}