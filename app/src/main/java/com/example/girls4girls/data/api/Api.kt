package com.example.girls4girls.data.api

import com.example.girls4girls.data.*
import com.example.girls4girls.data.model.*
import retrofit2.Response
import retrofit2.http.*

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
    ): Response<UserRegistrationResponse>

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

    @GET("video-blog")
    suspend fun getVideos(): Response<VideosList>

    @GET("categories")
    suspend fun getCategories(): Response<CategoryList>

    @GET("like")
    suspend fun getLikedVideos(
        @Header("Authorization") token: String?
    ): Response<List<VideoBlogResponse>>

    @FormUrlEncoded
    @POST("like/{blogId}")
    suspend fun likeVideo(
        @Header("Authorization") token: String?,
        @Field("blogId") blogId: Long,
    ): Response<Long>

    @DELETE("like/{blogId}")
    suspend fun unlikeVideo(
        @Header("Authorization") token: String?,
        @Path("blogId") blogId: Long
    ): Response<Long>

//    @FormUrlEncoded
    @PUT("like/toggle")
    suspend fun toggleLikeVideo(
        @Header("Authorization") token: String?,
        @Query("blogId") blogId: Int,
    ): Response<VideoBlogResponse>

    @GET("video-blog/get/watched")
    suspend fun getWatchedVideos(
        @Header("Authorization") token: String?
    ): Response<List<VideoBlog>>

    @PUT("video-blog/add-to-watched")
    suspend fun addToWatchedVideo(
        @Header("Authorization") token: String?,
        @Query("blogId") blogId: Int,
    ): Response<VideoBlog>

    @GET("event/upcoming/events")
    suspend fun getUpcomingEvents(): Response<List<Event>>

    @GET("https://girls4girls.herokuapp.com/api/quiz/{id}")
    suspend fun getQuiz(
        @Path ("id") id: Long
    ): Response<Quiz>
}































































































