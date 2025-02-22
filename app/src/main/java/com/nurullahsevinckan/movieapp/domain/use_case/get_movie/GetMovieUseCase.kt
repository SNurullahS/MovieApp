package com.nurullahsevinckan.movieapp.domain.use_case.get_movie

import coil.network.HttpException
import com.nurullahsevinckan.movieapp.data.remote.datatransferobj.Search
import com.nurullahsevinckan.movieapp.data.remote.datatransferobj.toMovieList
import com.nurullahsevinckan.movieapp.domain.model.Movie
import com.nurullahsevinckan.movieapp.domain.repository.MovieRepository
import com.nurullahsevinckan.movieapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOError
import javax.inject.Inject

class GetMovieUseCase @Inject constructor(private val repository : MovieRepository)  {

        fun executeGetMovieRepository(search: String) : Flow<Resource<List<Movie>>> = flow {
            try {
                emit(Resource.Loading())
                val movieList = repository.getMovies(search)
                if (movieList.Response.equals("True")) {
                    emit(Resource.Success(movieList.toMovieList()))
                }else
                {
                    emit(Resource.Error("No movie found!"))
                }
            }catch (e : IOError){
                emit(Resource.Error(e.localizedMessage ?: "No internet connection!"))
            }catch (e : HttpException){
                emit(Resource.Error(e.localizedMessage ?: "No internet connection!"))
            }
        }

}