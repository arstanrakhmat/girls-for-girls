package com.example.girls4girls.presentation.forum

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.girls4girls.data.model.TrainingById
import com.example.girls4girls.data.model.TrainingResponse
import com.example.girls4girls.data.repository.ForumRepository
import kotlinx.coroutines.launch

class ForumViewModel(private val repository: ForumRepository) : ViewModel() {

    val upcomingForum = MutableLiveData<TrainingResponse>()
    val pastForum = MutableLiveData<TrainingResponse>()

    val forumById = MutableLiveData<TrainingById>()

    val error = MutableLiveData<String>()

    fun getUpcomingForums(
        page: Int,
        limit: Int,
        order: String,
        orderField: String
    ) {
        viewModelScope.launch {
            val response = repository.getUpcomingForums(
                page, limit, order, orderField
            )

            if (response.isSuccessful) {
                upcomingForum.postValue(response.body())
            } else {
                error.postValue(response.errorBody().toString())
            }
        }
    }

    fun getPastForums(
        page: Int,
        limit: Int,
        order: String,
        orderField: String
    ) {
        viewModelScope.launch {
            val response = repository.getPastForums(
                page, limit, order, orderField
            )

            if (response.isSuccessful) {
                pastForum.postValue(response.body())
            } else {
                error.postValue(response.errorBody().toString())
            }
        }
    }

    fun getForumById(
        id: Int
    ) {
        viewModelScope.launch {
            val response = repository.getForumById(id)
            if (response.isSuccessful) {
                forumById.postValue(response.body())
            } else {
                error.postValue(response.errorBody().toString())
            }
        }
    }
}