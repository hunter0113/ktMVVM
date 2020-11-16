package com.user.kt_mvvm.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class MainViewModel : ViewModel(){
    //api登入成功後的memberId與name
    var userId: MutableLiveData<String> = MutableLiveData()
    var userName: MutableLiveData<String> = MutableLiveData()

    fun showWelcome(Id:String, Name:String){
        userId.value = Id
        userName.value = Name
    }
}