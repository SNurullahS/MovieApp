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

            fun executeGetMovieRepository(search: String, page: Int = 1, type : String? = null) : Flow<Resource<List<Movie>>> = flow {
                try {
                    emit(Resource.Loading())
                    val movieList = repository.getMovies(search, page, type)
                    if (movieList.Response.equals("True")) {
                        emit(Resource.Success(movieList.toMovieList(), movieList.totalResults.toIntOrNull() ?: 0))
                    } else {
                        emit(Resource.Error("No movie found!"))
                    }
                } catch (e : IOError) {
                    emit(Resource.Error(e.localizedMessage ?: "No internet connection!"))
                } catch (e : HttpException) {
                    emit(Resource.Error(e.localizedMessage ?: "No internet connection!"))
                }
            }

            fun loadMoreMovies(search: String, page: Int, type: String? = null) : Flow<Resource<List<Movie>>> = flow {
                try {
                    emit(Resource.Loading())
                    val movieList = repository.getMovies(search, page, type)
                    if (movieList.Response.equals("True")) {
                        emit(Resource.Success(movieList.toMovieList(), movieList.totalResults.toIntOrNull() ?: 0))
                    } else {
                        emit(Resource.Error("No more movies found!"))
                    }
                } catch (e : IOError) {
                    emit(Resource.Error(e.localizedMessage ?: "No internet connection!"))
                } catch (e : HttpException) {
                    emit(Resource.Error(e.localizedMessage ?: "No internet connection!"))
                }
            }
    }