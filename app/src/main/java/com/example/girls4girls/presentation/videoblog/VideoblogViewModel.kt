package com.example.girls4girls.presentation.videoblog

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.girls4girls.data.repository.VideoBlogsRepository
import kotlinx.coroutines.launch

class VideoblogViewModel(private val videoBlogRepository: VideoBlogsRepository,) : ViewModel() {

//    var isFullscreen = false

    var defaultHeight: Int? = null

    fun likeVideo(token: String?, id: Long){
        viewModelScope.launch {
            val response = videoBlogRepository.likeVideo(token, id)
            if (response.isSuccessful){
//                _likedVideosList.postValue(response.body()!!)
            } else {
                Log.d(VideoblogFragment.TAG, "likeVideo error: ${response.errorBody()?.string() ?: "Unknown"}")
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