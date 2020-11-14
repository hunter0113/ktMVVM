package com.user.kt_mvvm.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel :ViewModel(){
    var loginId: MutableLiveData<String> = MutableLiveData()
    var password: MutableLiveData<String> = MutableLiveData()
    var loginSuccess: MutableLiveData<Boolean> = MutableLiveData()

    var loginIdError: MutableLiveData<String> = MutableLiveData()
    var passwordError: MutableLiveData<String> = MutableLiveData()

    var snackBarText: MutableLiveData<Event<String>> = MutableLiveData()

    //載入中
    var isLoading: MutableLiveData<Boolean> = MutableLiveData(false)

    //登入的系統錯誤
    var loginError: MutableLiveData<Event<String>> = MutableLiveData()

    fun login() {
        try {
            loginIdError.value = ""
            passwordError.value = ""

            val currentLoginId = loginId.value
            val currentPassword = password.value

            if (currentLoginId.isNullOrEmpty() || currentPassword.isNullOrEmpty()) {
                if (currentLoginId.isNullOrEmpty()) {
                    snackBarText.value = Event("帳號不能是空白")
                    loginIdError.value = "帳號不能是空白"
                }

                if (currentPassword.isNullOrEmpty()) {
                    snackBarText.value = Event("密碼不能是空白")
                    passwordError.value = "密碼不能是空白"
                }

                return
            }

            val loginRepository = LoginRepository()

            isLoading.value = true

            loginRepository.login(
                currentLoginId,
                currentPassword,
                object : LoginRepository.LoginCallback {
                    override fun loginResult(isLoginSuccess: Boolean) {
                        loginSuccess.value = isLoginSuccess
                        isLoading.value = false

                        snackBarText.value = Event("登入成功")
                    }

                })
        } catch (e: Exception) {
            loginError.value = Event("系統異常")
            isLoading.value = false
        }

    }

}