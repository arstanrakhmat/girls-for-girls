package com.example.girls4girls.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Option (
    val text: String,
    val isCorrect: Boolean
):Parcelable