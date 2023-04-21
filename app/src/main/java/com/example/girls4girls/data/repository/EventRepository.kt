package com.example.girls4girls.data.repository

import com.example.girls4girls.data.Event
import com.example.girls4girls.data.api.Api
import retrofit2.Response

class EventRepository(private val api: Api) {

    suspend fun getUpcomingEvents(): Response<List<Event>>{
        return api.getUpcomingEvents()
    }

}