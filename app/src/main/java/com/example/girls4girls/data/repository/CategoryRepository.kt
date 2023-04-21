package com.example.girls4girls.data.repository

import com.example.girls4girls.data.Category
import com.example.girls4girls.data.CategoryList
import com.example.girls4girls.data.VideosList
import com.example.girls4girls.data.api.Api
import retrofit2.Response

class CategoryRepository (private val api: Api) {
    suspend fun getCategories(): Response<CategoryList> {
        return api.getCategories()
    }
}