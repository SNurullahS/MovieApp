package com.nurullahsevinckan.movieapp.domain.use_case.get_movie_detail

import coil.network.HttpException
import com.nurullahsevinckan.movieapp.data.remote.datatransferobj.toMovieDetail
import com.nurullahsevinckan.movieapp.domain.model.MovieDetail
import com.nurullahsevinckan.movieapp.domain.repository.MovieRepository
import com.nurullahsevinckan.movieapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOError
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(private val repository: MovieRepository) {

    fun executeGetMovieDetail(imdbId : String) : Flow<Resource<MovieDetail>>  = flow {
        try {
            emit(Resource.Loading())
            val movieDetail = repository.getMovieDetail(imdbId)
            emit(Resource.Success(movieDetail.toMovieDetail()))
        }catch (e: IOError){
            emit(Resource.Error(e.localizedMessage ?: "No internet connection!"))
        }catch (e : HttpException){
            emit(Resource.Error(e.localizedMessage ?: "No internet connection!"))
        }
    }
}