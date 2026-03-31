package com.core.database.dao

import androidx.room.*
import com.core.database.UserAccountEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserAccountEntity)

    @Query("SELECT * FROM user_account LIMIT 1")
    fun getUser(): Flow<UserAccountEntity?>

    @Query("DELETE FROM user_account")
    suspend fun deleteUser()
}
