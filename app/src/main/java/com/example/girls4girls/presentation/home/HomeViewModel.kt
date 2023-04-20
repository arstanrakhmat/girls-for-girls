package com.example.girls4girls.presentation.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.girls4girls.data.Event
import com.example.girls4girls.data.repository.EventRepository
import com.example.girls4girls.data.repository.VideoBlogsRepository
import com.example.girls4girls.presentation.videoblog.VideoblogFragment.Companion.TAG
import kotlinx.coroutines.launch

class HomeViewModel(
    private val eventRepository: EventRepository,
) : ViewModel() {

    val _upcomingEvents = MutableLiveData<List<Event>>()

    fun getUpcomingEvents(){
        viewModelScope.launch {
            val response = eventRepository.getUpcomingEvents()

            if (response.isSuccessful){
                _upcomingEvents.postValue(response.body())
                Log.d(TAG, "succ getUpcomingEvents: ${_upcomingEvents}")
            } else {
                Log.d(TAG, "err getUpcomingEvents: ${response.errorBody()}")
            }
        }
    }
}