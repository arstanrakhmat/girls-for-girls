package com.example.girls4girls.presentation.home

import androidx.lifecycle.ViewModel
import com.example.girls4girls.data.Event

class HomeViewModel : ViewModel() {
    val list = listOf<Event>(
        Event(0),
        Event(1),
        Event(2),
        Event(3),
    )
}