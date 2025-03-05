package com.nurullahsevinckan.movieapp.domain.mapper

import com.nurullahsevinckan.movieapp.data.local.entity.FavoriteMovieEntity
import com.nurullahsevinckan.movieapp.domain.model.MovieDetail

fun MovieDetail.toFavoriteMovieEntity(imdbID: String, userId: String): FavoriteMovieEntity {
    return FavoriteMovieEntity(
        imdbID = imdbID,
        title = this.Title,
        year = this.Year,
        poster = this.Poster,
        imdbRating = this.imdbRating,
        userId = userId
    )
}
