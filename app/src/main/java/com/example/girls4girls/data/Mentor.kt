package com.example.girls4girls.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.coroutines.NonDisposableHandle.parent
import kotlinx.parcelize.IgnoredOnParcel

@Parcelize
data class Mentor(
    val name: String,
    val image: Int
): Parcelable{

    @IgnoredOnParcel
    var videos: List<VideoBlog> = mutableListOf()
    constructor(name: String, image: Int, videos: List<VideoBlog>) : this(name, image) {
        this.videos = videos
    }
}
