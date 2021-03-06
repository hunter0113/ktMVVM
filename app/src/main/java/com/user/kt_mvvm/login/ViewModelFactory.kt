package com.user.kt_mvvm.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.user.kt_mvvm.main.MainViewModel

//當要給予viewmodel預設值時使用
class ViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel() as T
        }
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}




