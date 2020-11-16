package com.user.kt_mvvm.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class MainViewModel(private val defaultId:String, private val defaultName:String) : ViewModel(){
    //api登入成功後的memberId與name
    var userId: MutableLiveData<String> = MutableLiveData()
    var userName: MutableLiveData<String> = MutableLiveData()

    init {
        userId.value = defaultId
        userName.value = defaultName
    }

}