package com.example.girls4girls.presentation.videoblogsList

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.girls4girls.R
import com.example.girls4girls.data.*
import com.example.girls4girls.data.repository.CategoryRepository
import com.example.girls4girls.data.repository.VideoBlogsRepository
import com.example.girls4girls.presentation.videoblog.VideoblogFragment.Companion.TAG
import com.example.girls4girls.utils.Constants.TOKEN
import kotlinx.coroutines.launch
import okhttp3.Request
import org.koin.android.ext.android.inject
import org.koin.java.KoinJavaComponent.inject

class VideoblogsListViewModel(
    private val videoBlogsRepository: VideoBlogsRepository,
    private val categoryRepository: CategoryRepository
    ) : ViewModel() {

    val _videosList = MutableLiveData<List<VideoBlog>>()
    val _likedVideosList = MutableLiveData<List<VideoBlog>>()
    val _watchedVideosList = MutableLiveData<List<VideoBlog>>()
    var _categories = MutableLiveData<List<Category>>()


    fun getVideos(){
        viewModelScope.launch {
            val response = videoBlogsRepository.getVideoBlogs()
            if (response.isSuccessful){

                for (videoBlog in response.body()!!.videosList) {
                    if (_likedVideosList.value?.contains(videoBlog) == true){
                        videoBlog.isLiked == true
                    }
                }

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

    fun getWatchedVideos(token: String?){
        viewModelScope.launch{
            val response = videoBlogsRepository.getWatchedVideos(token)

            if (response.isSuccessful){
                _watchedVideosList.postValue(response.body())
            } else {
                Log.d(TAG, "getLikedVideos: ${response.errorBody()?.string()}")
            }

        }
    }

}