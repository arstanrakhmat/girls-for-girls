package com.example.girls4girls.data.model

data class Meta(
    val itemsCount: Int,
    val limit: Int,
    val offset: Int,
    val order: String,
    val orderField: String,
    val page: Int,
    val pagesCount: Int,
    val returned: Int
)