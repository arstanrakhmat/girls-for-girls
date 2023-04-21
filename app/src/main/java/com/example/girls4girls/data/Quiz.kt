package com.example.girls4girls.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Quiz(
    val id: Long,
    val title: String,
    val description: String,
    val questions: List<Question>
): Parcelable
