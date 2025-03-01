package com.nurullahsevinckan.movieapp.data.dependencyinjection

import android.content.Context
import androidx.room.Room
import com.google.firebase.auth.FirebaseAuth
import com.nurullahsevinckan.movieapp.data.local.dao.FavoriteMovieDao
import com.nurullahsevinckan.movieapp.data.local.database.MovieDatabase
import com.nurullahsevinckan.movieapp.data.remote.MovieAPI
import com.nurullahsevinckan.movieapp.data.repository.FavoriteMovieRepositoryImpl
import com.nurullahsevinckan.movieapp.data.repository.MovieRepositoryImpl
import com.nurullahsevinckan.movieapp.domain.repository.AuthenticationRepository
import com.nurullahsevinckan.movieapp.domain.repository.FavoriteMovieRepository
import com.nurullahsevinckan.movieapp.domain.repository.MovieRepository
import com.nurullahsevinckan.movieapp.domain.use_case.firebase_auth.AuthenticationRepositoryImpl
import com.nurullahsevinckan.movieapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMovieApi() : MovieAPI{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieAPI::class.java    )
    }


    @Provides
    @Singleton
    fun provideMovieRepository(api : MovieAPI) : MovieRepository{
        return MovieRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideMovieDatabase(@ApplicationContext context: Context): MovieDatabase {
        return Room.databaseBuilder(
            context,
            MovieDatabase::class.java,
            "movie_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideFavoriteMovieDao(database: MovieDatabase): FavoriteMovieDao {
        return database.favoriteMovieDao()
    }

    @Provides
    @Singleton
    fun provideFavoriteMovieRepository(dao: FavoriteMovieDao): FavoriteMovieRepository {
        return FavoriteMovieRepositoryImpl(dao)
    }


    @Provides
    @Singleton
    fun providesFirebaseAuth() = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun providesAuthRepositoryImpl(firebaseAuth: FirebaseAuth): AuthenticationRepository {
        return AuthenticationRepositoryImpl(firebaseAuth = firebaseAuth)
    }

}