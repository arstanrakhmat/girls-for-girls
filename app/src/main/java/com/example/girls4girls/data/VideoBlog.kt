package com.example.girls4girls.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class VideoBlog(
    val id: Long,
    val title: String,
    val duration: Int,
    val category: String,
    val speaker: String,
    val description: String,
    val views: Long,
    val watched: Boolean,
    val liked: Boolean,
    val link: String
): Parcelable
