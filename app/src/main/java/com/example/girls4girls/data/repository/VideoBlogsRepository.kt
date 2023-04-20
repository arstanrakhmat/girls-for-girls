package com.example.girls4girls.data.repository

import com.example.girls4girls.data.VideoBlog
import com.example.girls4girls.data.VideoBlogResponse
import com.example.girls4girls.data.VideosList
import com.example.girls4girls.data.api.Api
import retrofit2.Response

class VideoBlogsRepository(private val api: Api) {
    suspend fun getVideoBlogs(): Response<VideosList> {
        return api.getVideos()
    }

    suspend fun getLikedVideos(token: String?): Response<List<VideoBlogResponse>>{
        return api.getLikedVideos(token)
    }

    suspend fun likeVideo(token: String?, videoBlogId: Long): Response<Long>{
        return api.likeVideo(token, videoBlogId)
    }

    suspend fun unLikeVideo(token: String?, videoBlogId: Long): Response<Long>{
        return api.unlikeVideo(token, videoBlogId)
    }

    suspend fun toggleLikeVideo(token: String?, videoBlogId: Long): Response<VideoBlogResponse>{
        return api.toggleLikeVideo(token, videoBlogId.toInt())
    }

    suspend fun getWatchedVideos(token: String?): Response<List<VideoBlog>>{
        return api.getWatchedVideos(token)
    }

    suspend fun addToWatchedVideos(token: String?, videoBlogId: Long): Response<VideoBlog>{
        return api.addToWatchedVideo(token, videoBlogId.toInt())
    }
}