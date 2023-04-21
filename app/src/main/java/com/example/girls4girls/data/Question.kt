package com.example.girls4girls.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Question(
    val question: String,
    val options: List<Option>
): Parcelable