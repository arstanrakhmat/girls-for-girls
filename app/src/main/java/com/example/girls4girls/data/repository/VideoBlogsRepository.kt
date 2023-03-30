package com.example.girls4girls.data.repository

import com.example.girls4girls.data.VideoBlog
import com.example.girls4girls.data.VideosList
import com.example.girls4girls.data.api.Api
import retrofit2.Response

class VideoBlogsRepository(private val api: Api) {
    suspend fun getVideoBlogs(): Response<VideosList> {
        return api.getVideos()
    }
}