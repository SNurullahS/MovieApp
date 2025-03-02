package com.nurullahsevinckan.movieapp.domain.use_case.firebase_auth

import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.nurullahsevinckan.movieapp.domain.repository.AuthenticationRepository
import com.nurullahsevinckan.movieapp.util.Resource
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthenticationRepository {


    override suspend fun loginUser(email: String, password: String): Flow<Resource<AuthResult>> {
        return flow {
            emit(Resource.Loading())
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            emit(Resource.Success(data = result))
        }.catch {
            emit(value = Resource.Error(it.message.toString()))
        }
    }

    override suspend fun registerUser(email: String, password: String): Flow<Resource<AuthResult>> {
        return flow {
            emit(Resource.Loading())
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            result.user?.sendEmailVerification()?.await()
            emit( Resource.Success(data = result))
        }.catch {
            emit(Resource.Error(it.message.toString()))
        }
    }


    override fun getCurrentUser(): FirebaseUser? {
        return firebaseAuth.currentUser
    }

    override fun isUserAuthenticated(): Boolean {
        return firebaseAuth.currentUser != null
    }

    override suspend fun signOut(): Flow<Resource<Unit>> {
        return flow {
            emit(Resource.Loading())
            try {
                firebaseAuth.signOut()
                emit(Resource.Success(Unit))
            } catch (e: Exception) {
                emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
            }
        }.catch {
            emit(Resource.Error(it.message ?: "Error while signOut!"))
        }
    }

    override fun isEmailVerified(): Flow<Resource<Boolean>> = flow {
        emit(value = Resource.Loading())
        val currentUser = firebaseAuth.currentUser
        if (currentUser != null) {
            emit(Resource.Success(currentUser.isEmailVerified))
        } else {
            emit(Resource.Error("User is not valid!"))
        }
    }

    override fun currentUserUid(): Flow<Resource<String>> = flow {
        emit(Resource.Loading())
        val currentUser = firebaseAuth.currentUser
        if(currentUser != null){
        val currentUserUid = firebaseAuth.currentUser?.uid
            emit(Resource.Success(currentUserUid!!))
        }else{
            emit(Resource.Error("User uid is not found!"))
        }
    }

}
