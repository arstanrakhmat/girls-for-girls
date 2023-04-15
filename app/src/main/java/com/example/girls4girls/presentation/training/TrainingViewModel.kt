package com.example.girls4girls.presentation.training

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.girls4girls.data.model.TrainingById
import com.example.girls4girls.data.model.TrainingResponse
import com.example.girls4girls.data.repository.TrainingRepository
import kotlinx.coroutines.launch

class TrainingViewModel(private val repository: TrainingRepository) : ViewModel() {

    val upcomingTraining = MutableLiveData<TrainingResponse>()

    val pastTraining = MutableLiveData<TrainingResponse>()
    val trainingById = MutableLiveData<TrainingById>()

    val error = MutableLiveData<String>()

    fun getUpcomingTrainings(
        page: Int,
        limit: Int,
        order: String,
        orderField: String
    ) {
        viewModelScope.launch {
            val response = repository.getUpcomingTrainings(
                page, limit, order, orderField
            )

            if (response.isSuccessful) {
                upcomingTraining.postValue(response.body())
            } else {
                error.postValue(response.errorBody().toString())
            }
        }
    }

    fun getPastTrainings(
        page: Int,
        limit: Int,
        order: String,
        orderField: String
    ) {
        viewModelScope.launch {
            val response = repository.getPastTrainings(
                page, limit, order, orderField
            )

            if (response.isSuccessful) {
                pastTraining.postValue(response.body())
            } else {
                error.postValue(response.errorBody().toString())
            }
        }
    }


    fun getTrainingById(
        id: Int
    ) {
        viewModelScope.launch {
            val response = repository.getTrainingById(id)
            if (response.isSuccessful) {
                trainingById.postValue(response.body())
            } else {
                error.postValue(response.errorBody().toString())
            }
        }

    }

}