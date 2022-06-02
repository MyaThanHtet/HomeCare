package com.myatech.homecare.ui.signup

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.myatech.homecare.model.User
import com.myatech.homecare.repository.MyDatabase
import com.myatech.homecare.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignUpViewModel(application: Application) : AndroidViewModel(application) {
    private val userRepository: UserRepository
    val getSignedInUser: LiveData<User>


    init {
        val userDao = MyDatabase.getInstance(application).userDao()
        userRepository = UserRepository(userDao)
        getSignedInUser = userRepository.getSignedInUser
    }


    fun addUser(user: User) = viewModelScope.launch(Dispatchers.IO) {
        userRepository.insert(user)
    }
    fun deleteUser(user: User) = viewModelScope.launch(Dispatchers.IO) {
        userRepository.delete(user)
    }
}