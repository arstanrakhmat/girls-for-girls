package com.example.girls4girls.data

import android.os.Parcelable
import androidx.lifecycle.LiveData
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class VideoBlog(
    val id: Long,
    val title: String,
    val duration: Int,
    val category: String,
    val speaker: String,
    val description: String,
    var views: Long,
    var isWatched: Boolean,
    var isLiked: Boolean,
    val link: String
): Parcelable
