package com.example.girls4girls.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Mentor(
    val name: String,
    val image: Int,
    val videos: List<VideoBlog>
): Parcelable
