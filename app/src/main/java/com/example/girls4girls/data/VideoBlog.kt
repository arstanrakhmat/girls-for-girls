package com.example.girls4girls.data

import android.os.Parcelable
import androidx.lifecycle.LiveData
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class VideoBlog(
    val id: Long,
    val title: String,
    val date: String,
    val category: String,
    val speaker: Mentor,
    val description: String,
    var views: Long,
    var isWatched: Boolean,
    var isLiked: Boolean,
    val link: String
): Parcelable
