package com.user.kt_mvvm.login.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface MemberApi {
    @POST(ApiConfig.loginUrl)
    fun login(@Body request: LoginRequest): Call<LoginResponse>
    
}
