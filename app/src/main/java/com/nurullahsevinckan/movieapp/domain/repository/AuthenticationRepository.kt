package com.nurullahsevinckan.movieapp.domain.repository

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.nurullahsevinckan.movieapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface AuthenticationRepository {

    suspend fun loginUser(email: String, password: String): Flow<Resource<AuthResult>>
    suspend fun registerUser(email: String, password: String): Flow<Resource<AuthResult>>
    fun getCurrentUser(): FirebaseUser?
    fun isUserAuthenticated(): Boolean
    suspend fun signOut(): Flow<Resource<Unit>>
    fun isEmailVerified(): Flow<Resource<Boolean>>
    fun currentUserUid(): Flow<Resource<String>>
}