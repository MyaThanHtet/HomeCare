package com.myatech.homecare.repository

import androidx.lifecycle.LiveData
import com.myatech.homecare.dao.UserDao
import com.myatech.homecare.model.User

class UserRepository(private val userDao: UserDao) {

    suspend fun insert(user: User) {
        userDao.insertUser(user)
    }
    suspend fun delete(user: User) {
        userDao.delete(user)
    }
    val getSignedInUser: LiveData<User> = userDao.getSignedInUser()

}