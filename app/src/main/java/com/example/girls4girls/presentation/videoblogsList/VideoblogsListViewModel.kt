package com.example.girls4girls.presentation.videoblogsList

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.girls4girls.R
import com.example.girls4girls.data.Category
import com.example.girls4girls.data.Mentor
import com.example.girls4girls.data.VideoBlog
import com.example.girls4girls.data.VideoBlogResponse
import com.example.girls4girls.data.repository.CategoryRepository
import com.example.girls4girls.data.repository.VideoBlogsRepository
import com.example.girls4girls.presentation.videoblog.VideoblogFragment.Companion.TAG
import com.example.girls4girls.utils.Constants.TOKEN
import kotlinx.coroutines.launch
import okhttp3.Request

class VideoblogsListViewModel(
    private val videoBlogsRepository: VideoBlogsRepository,
    private val categoryRepository: CategoryRepository
    ) : ViewModel() {

    val _videosList = MutableLiveData<List<VideoBlog>>()
    val _likedVideosList = MutableLiveData<List<VideoBlog>>()
    var _categories = MutableLiveData<List<Category>>()

    fun getVideos(){
        viewModelScope.launch {
            val response = videoBlogsRepository.getVideoBlogs()
            Log.d(TAG, "getVideos: ${response.toString()}")
            if (response.isSuccessful){
                _videosList.postValue(response.body()!!.videosList)
            } else {
                Log.d(TAG, "getVideos: ${response.errorBody().toString()}")
            }

        }
    }

    fun getCategories(){
        viewModelScope.launch {
            val response = categoryRepository.getCategories()
            if (response.isSuccessful){
                _categories.postValue(response.body()!!.categoryList)
            } else {
                Log.d(TAG, "getVideos: ${response.errorBody().toString()}")
            }
        }
    }

    fun getLikedVideos(token: String?){
        viewModelScope.launch {
            val response = videoBlogsRepository.getLikedVideos(token)
            var request = Request.Builder()
                .url("https://girls4girls.herokuapp.com/api/like")
                .build()

            request = request.newBuilder()
                .header("Authorization", TOKEN)
                .build()

            Log.d(TAG, "getLikedVideos: ${request}")
            if (response.isSuccessful){

                val likedVideos = mutableListOf<VideoBlog>()

                response.body()?.forEach {
                    if (it.blog != null){
                        likedVideos.add(it.blog)
                    }
                }
                _likedVideosList.postValue(likedVideos)
                Log.d(TAG, "getLikedVideos: ${(response.body()!!)}")
            } else {
                Log.d(TAG, "getLikedVideos: ${response.errorBody()?.string()}")
            }
        }
    }

}