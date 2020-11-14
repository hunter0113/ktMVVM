package com.user.kt_mvvm.login
import android.os.Handler

class LoginRepository : ILoginRepository {
    interface LoginCallback {
        fun loginResult(isLoginSuccess: Boolean)
    }

    override fun login(loginId: String, password: String, listener: LoginCallback) {
        //帳密皆為123則登入成功，延遲1500。
        if (loginId == "123" && password == "123") {
            Handler().postDelayed({
                listener.loginResult(true)
            }, 1500)
        } else {
            Handler().postDelayed({
                listener.loginResult(false)
            }, 1500)
        }

    }
}
