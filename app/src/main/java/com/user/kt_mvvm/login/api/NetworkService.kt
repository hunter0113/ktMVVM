package com.user.kt_mvvm.login.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiConfig {

    const val WEB_HOST = "https://run.mocky.io/"

    const val TIME_OUT_CONNECT = 30
    const val TIME_OUT_READ = 30
    const val TIME_OUT_WRITE = 30

    const val loginUrl = "https://run.mocky.io/v3/0be18f07-62a6-41f3-b76c-8a3acf6fa41a"
}

class NetworkService {

    var memberAPI: MemberApi

    init {

        val client = OkHttpClient.Builder()
            .connectTimeout(ApiConfig.TIME_OUT_CONNECT.toLong(), TimeUnit.SECONDS)
            .readTimeout(ApiConfig.TIME_OUT_READ.toLong(), TimeUnit.SECONDS)
            .writeTimeout(ApiConfig.TIME_OUT_WRITE.toLong(), TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(ApiConfig.WEB_HOST)
            .client(client)
            .build()

        memberAPI = retrofit.create(MemberApi::class.java)

    }
}
