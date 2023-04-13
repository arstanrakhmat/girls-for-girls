package com.example.girls4girls.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class VideoBlogResponse(
    val id: Long,
    val blog: VideoBlog
): Parcelable
