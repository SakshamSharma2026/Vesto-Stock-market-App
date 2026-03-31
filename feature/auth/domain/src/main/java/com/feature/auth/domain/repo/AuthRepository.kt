package com.feature.auth.domain.repo

import android.content.Intent
import android.content.IntentSender
import com.core.common.User
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    fun isLoggedIn(): Boolean

    fun signOut()

    suspend fun googleSignIn(): IntentSender?

    suspend fun handleGoogleSignInResult(data: Intent?): Result<Unit>

    fun getUser(): Flow<User?>
}