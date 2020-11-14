package com.user.kt_mvvm.login

interface ILoginRepository {
    fun login(loginId: String, password: String, listener:LoginRepository.LoginCallback)
}