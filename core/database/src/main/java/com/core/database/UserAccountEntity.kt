package com.core.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_account")
data class UserAccountEntity(
    @PrimaryKey val uid: String,
    val name: String?,
    val email: String?,
    val profilePicUrl: String?
)
