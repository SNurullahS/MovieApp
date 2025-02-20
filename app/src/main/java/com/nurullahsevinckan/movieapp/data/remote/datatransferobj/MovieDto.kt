package com.nurullahsevinckan.movieapp.data.remote.datatransferobj

data class MovieDto(
    val Response: String,
    val Search: List<Search>,
    val totalResults: String
)