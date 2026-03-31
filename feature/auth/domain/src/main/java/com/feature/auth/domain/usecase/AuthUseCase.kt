package com.feature.auth.domain.usecase

import android.content.Intent
import android.content.IntentSender
import com.feature.auth.domain.repo.AuthRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthUseCase @Inject constructor(private val repo: AuthRepository) {
    suspend fun googleSignIn(): IntentSender? = repo.googleSignIn()

    suspend fun handleGoogleSignInResult(data: Intent?): Result<Unit> =
        repo.handleGoogleSignInResult(data)

    fun getUser() = repo.getUser()

    fun signOut() = repo.signOut()

    fun isLoggedIn(): Boolean = repo.isLoggedIn()
}