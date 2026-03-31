package com.feature.auth.data

import android.content.Context
import android.content.Intent
import android.content.IntentSender
import com.core.common.User
import com.core.database.UserAccountEntity
import com.feature.auth.data.local.UserDataProvider
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AuthManager @Inject constructor(
    private val auth: FirebaseAuth,
    private val userDataProvider: UserDataProvider,
    @ApplicationContext private val context: Context
) {

    private val oneTapClient: SignInClient = Identity.getSignInClient(context)


    val currentUser get() = auth.currentUser

    fun isLoggedIn(): Boolean = auth.currentUser != null

    fun signOut() {
        auth.signOut()
        MainScope().launch {
            userDataProvider.deleteUser()
        }
    }

    private fun buildSignInRequest(): BeginSignInRequest {
        return BeginSignInRequest.builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setFilterByAuthorizedAccounts(false)
                    .setServerClientId(BuildConfig.GOOGLE_CLIENT_ID)
                    .build()
            )
            .setAutoSelectEnabled(true)
            .build()
    }

    suspend fun getGoogleSignIn(): IntentSender? {
        val result = try {
            oneTapClient.beginSignIn(buildSignInRequest()).await()
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            null
        }
        return result?.pendingIntent?.intentSender
    }

    suspend fun handleGoogleSignInResult(data: Intent?): Result<Unit> {
        return try {
            val credential = oneTapClient.getSignInCredentialFromIntent(data)
            val idToken = credential.googleIdToken ?: throw Exception("No ID Token found")

            val firebaseCredential = GoogleAuthProvider.getCredential(idToken, null)

            val firebaseUser = auth.signInWithCredential(firebaseCredential).await().user!!

            // Save user to local DB
            userDataProvider.insertUser(
                UserAccountEntity(
                    uid = firebaseUser.uid,
                    name = firebaseUser.displayName,
                    email = firebaseUser.email,
                    profilePicUrl = firebaseUser.photoUrl?.toString()
                )
            )

            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun getUser(): Flow<User?> {
        return userDataProvider.getUser().map { entity ->
            entity?.let {
                User(
                    uid = it.uid,
                    name = it.name,
                    email = it.email,
                    profilePicUrl = it.profilePicUrl
                )
            }
        }
    }
}
