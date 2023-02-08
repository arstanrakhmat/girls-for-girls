package com.example.girls4girls.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class VideoBlog(
    val id: Long,
    val title: String,
    val duration: Int
): Parcelable
