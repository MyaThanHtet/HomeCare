package com.myatech.homecare.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

 /*
 //data repository
 private val _getUserList =MutableLiveData<List<User>>().apply {
        value = network call
    }

    //observable value
    val getUserList:LiveData<List<User>> = _getUserList*/
}