package com.user.kt_mvvm.login.api

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    val isLoginSuccess:Boolean,
    val memberId: String,
    @SerializedName("memberName")
    val name: String
)
