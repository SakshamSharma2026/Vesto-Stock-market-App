package com.feature.auth.data.repo

import android.content.Intent
import android.content.IntentSender
import com.core.common.User
import com.feature.auth.data.AuthManager
import com.feature.auth.domain.repo.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val authManager: AuthManager) : AuthRepository {
    override fun isLoggedIn(): Boolean = authManager.isLoggedIn()


    override fun signOut() {
        authManager.signOut()
    }

    override suspend fun googleSignIn(): IntentSender? {
        return authManager.getGoogleSignIn()
    }

    override suspend fun handleGoogleSignInResult(data: Intent?): Result<Unit> {
        return authManager.handleGoogleSignInResult(data)
    }

    override fun getUser(): Flow<User?> {
        return authManager.getUser()
    }
}