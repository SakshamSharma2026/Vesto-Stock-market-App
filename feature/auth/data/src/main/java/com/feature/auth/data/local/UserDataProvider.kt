package com.feature.auth.data.local

import com.core.database.UserAccountEntity
import com.core.database.dao.UserDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserDataProvider @Inject constructor(private val userDao: UserDao) {

    suspend fun insertUser(user: UserAccountEntity) = userDao.insertUser(user)
    fun getUser() = userDao.getUser()
    suspend fun deleteUser() = userDao.deleteUser()

}