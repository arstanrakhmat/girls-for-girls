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

            if (response.isSuccessful){
                Log.d(TAG, "Success")
            } else {
                Log.d(TAG, "likeVideo error: ${response.errorBody()?.string() ?: "Unknown"}")
            }
        }
    }

    fun addToWatched(token: String?, id: Long){
        viewModelScope.launch {
            val response = videoBlogRepository.addToWatchedVideos(token, id)

            if (response.isSuccessful){
                Log.d(TAG, "Success")
            } else {
                Log.d(TAG, "likeVideo error: ${response.errorBody()?.string() ?: "Unknown"}")
            }
        }
    }
}