package com.user.kt_mvvm.login
import android.os.Handler
import com.user.kt_mvvm.login.api.LoginRequest
import com.user.kt_mvvm.login.api.LoginResponse
import com.user.kt_mvvm.login.api.NetworkService
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback



class LoginRepository : ILoginRepository {
    interface LoginCallback {
        //回傳登入是否成功與id與username
        fun loginResult(isLoginSuccess: Boolean, memberId:String, name:String)
    }

    override fun login(loginId: String, password: String, loginCallback: LoginCallback) {

        val networkServiceApi = NetworkService()
        val request = LoginRequest(loginId, password)

        networkServiceApi.memberAPI.login(request).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                //success
                println("response:$response")
                if ( response.isSuccessful ) {
                    //200
                    val loginResponse = response.body()!!

                    println("id:${loginResponse.memberId}, name:${loginResponse.name}")
                    loginCallback.loginResult(response.body()!!.isLoginSuccess,loginResponse.memberId,loginResponse.name)
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                //fail
                //網路異常、Server異常等等
                println("onFailure:$t")
            }
        })
    }
}
