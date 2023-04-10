package com.example.girls4girls.data.api

import com.example.girls4girls.data.model.*
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Query

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
    ): Response<UserActivateResponse>

    @FormUrlEncoded
    @POST("auth/login")
    suspend fun userLogin(
        @Field("email") email: String,
        @Field("phoneNumber") phoneNumber: String,
        @Field("password") password: String
    ): Response<UserLoginResponse>

    @FormUrlEncoded
    @POST("auth/login")
    suspend fun userLoginEmail(
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<UserLoginResponse>

    @FormUrlEncoded
    @POST("auth/login")
    suspend fun userLoginPhoneNumber(
        @Field("phoneNumber") phoneNumber: String,
        @Field("password") password: String
    ): Response<UserLoginResponse>

    @GET("auth/profile")
    suspend fun getUser(
        @Header("Authorization") token: String?
    ): Response<User>

    @FormUrlEncoded
    @POST("auth/forgot-password")
    suspend fun resetPasswordEmail(
        @Field("email") email: String
    ): Response<Message>

    @FormUrlEncoded
    @POST("auth/forgot-password/confirm")
    suspend fun resetPasswordEmailConfirm(
        @Field("email") email: String,
        @Field("code") code: String
    ): Response<ResetPasswordConfirmResponse>

    @FormUrlEncoded
    @PATCH("auth/change-password")
    suspend fun changePassword(
        @Header("Authorization") token: String?,
        @Field("newPassword") newPassword: String
    ): Response<Message>

    @GET("training/future/trainings")
    suspend fun getUpcomingTrainings(
        @Query("page")
        pageNumber: Int = 1,

        @Query("limit")
        limitNumber: Int = 4,

        @Query("order")
        order: String = "ASC",

        @Query("orderField")
        orderField: String = "id"
    ): Response<TrainingResponse>

    @GET("training/past/trainings")
    suspend fun getPastTrainings(
        @Query("page")
        pageNumber: Int = 1,

        @Query("limit")
        limitNumber: Int = 4,

        @Query("order")
        order: String = "ASC",

        @Query("orderField")
        orderField: String = "id"
    ): Response<TrainingResponse>

}