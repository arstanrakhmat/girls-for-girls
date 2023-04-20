package com.example.girls4girls.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Message(
    val message: String
): Parcelable