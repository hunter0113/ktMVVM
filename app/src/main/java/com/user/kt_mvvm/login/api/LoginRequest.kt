package com.user.kt_mvvm.login.api

import com.google.gson.annotations.SerializedName

data class LoginRequest (
    val id:String,
    @SerializedName("Password")
    val password:String
)