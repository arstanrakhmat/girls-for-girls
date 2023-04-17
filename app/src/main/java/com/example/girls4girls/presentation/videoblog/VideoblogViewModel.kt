package com.example.girls4girls.presentation.videoblog

import android.icu.lang.UCharacter
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.girls4girls.data.model.User
import com.example.girls4girls.data.repository.UserRepository
import com.example.girls4girls.data.repository.VideoBlogsRepository
import com.example.girls4girls.presentation.videoblog.VideoblogFragment.Companion.TAG
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import kotlinx.coroutines.launch
import okhttp3.Request

class VideoblogViewModel(
    private val videoBlogRepository: VideoBlogsRepository) : ViewModel() {

    var defaultHeight: Int? = null

    fun toggleVideoLike(token: String?, id: Long){
        viewModelScope.launch {
            val response = videoBlogRepository.toggleLikeVideo(token, id)
            val gson = GsonBuilder().setPrettyPrinting().create()
            val prettyJson = gson.toJson(
                JsonParser.parseString(
                    response.body().toString() // About this thread blocking annotation : https://github.com/square/retrofit/issues/3255
                )
            )

            if (!response.isSuccessful){
                Log.d(TAG, "likeVideo error: ${response.errorBody()?.string() ?: "Unknown"}")
                Log.d(TAG, "prettyJson: ${ prettyJson}")
            }
        }
    }

    fun likeVideo(token: String?, id: Long){
        viewModelScope.launch {
            val response = videoBlogRepository.likeVideo(token, id)

            if (response.isSuccessful){
                Log.d(TAG, "videoBlog.id: ${id} ")
            } else {
                Log.d(TAG, "body(): ${response.code()}")
                Log.d(TAG, "likeVideo error: ${response.errorBody()?.string() ?: "Unknown"}")
            }
        }
    }

    fun unLikeVideo(token: String?, id: Long){
        viewModelScope.launch {
            val response = videoBlogRepository.unLikeVideo(token, id)
            if (response.isSuccessful){
//                _likedVideosList.postValue(response.body()!!)
            } else {
                Log.d(VideoblogFragment.TAG, "getVideos: ${response.errorBody().toString()}")
            }
        }
    }
}