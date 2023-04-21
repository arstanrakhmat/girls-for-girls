package com.example.girls4girls.data

import com.google.gson.annotations.SerializedName

data class CategoryList (
    @SerializedName("data")
    val categoryList: List<Category>
)