package com.example.girls4girls.data.api

import com.example.girls4girls.data.*
import com.example.girls4girls.data.model.*
import okhttp3.MultipartBody
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

    @GET("user/profile")
    suspend fun getAllUserInfo(
        @Header("Authorization") token: String?
    ): Response<UserAllDataNewVersion>

    @FormUrlEncoded
    @PATCH("user/profile")
    suspend fun userUpdate(
        @Header("token") token: String,
        @Field("email") email: String,
        @Field("firstName") firstName: String,
        @Field("lastName") lastName: String,
        @Field("phoneNumber") phoneNumber: String,
        @Field("dateOfBirth") dateOfBirth: String?,
        @Field("gender") gender: String?,
        @Field("region") region: String?
    ): Response<UserRegistrationResponse>

    @FormUrlEncoded
    @PATCH("user/profile")
    suspend fun userUpdate2(
        @Header("Authorization") token: String,
        @Field("dateOfBirth") dateOfBirth: String?,
        @Field("gender") gender: String?,
        @Field("region") region: String?
    ): Response<UserRegistrationResponse>

    @FormUrlEncoded
    @POST("auth/forgot-password")
    suspend fun resetPasswordEmail(
        @Field("email") email: String
    ): Response<Message>

    @Multipart
    @POST("user")
    suspend fun postPhoto(
        @Header("Authorization") token: String,
        @Part image: MultipartBody.Part
    ): Response<UserAllDataNewVersion>

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

    @GET("https://girls4girls.herokuapp.com/api/quiz/get-jeton")
    suspend fun getJeton(
        @Header("Authorization") token: String?
    ): Response<Jeton>

    @GET("training/{id}")
    suspend fun getTrainingById(@Path("id") id: Int): Response<TrainingById>

    @GET("forum/{id}")
    suspend fun getForumById(@Path("id") id: Int): Response<TrainingById>

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

    @GET("forum/future/forums")
    suspend fun getUpcomingForums(
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

    @GET("forum/past/forums")
    suspend fun getPastForums(
        @Query("page")
        pageNumber: Int = 1,

        @Query("limit")
        limitNumber: Int = 4,

        @Query("order")
        order: String = "ASC",

        @Query("orderField")
        orderField: String = "id"
    ): Response<TrainingResponse>

    @POST("questionnaire/response")
    suspend fun questionnaireApply2(
        @Header("Authorization") token: String?,
        @Body answers: QuestionnaireFillOut
    ): Response<QuestionnaireApplyResponse>

    @POST("training/apply")
    suspend fun trainingApply(
        @Header("Authorization") token: String?,
        @Query("trainingId") trainingId: Int,
        @Query("questionnaireResponseId") questionnaireResponseId: Int
    ): Response<TrainingApplyResponse>

}

