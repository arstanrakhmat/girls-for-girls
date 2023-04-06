package com.example.girls4girls.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Answer (
    val question: Question,
    val chosenId: Int,
    val isCorrect: Boolean
): Parcelable