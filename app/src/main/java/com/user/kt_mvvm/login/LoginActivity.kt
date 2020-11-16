package com.user.kt_mvvm.login

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.user.kt_mvvm.R
import com.user.kt_mvvm.databinding.ActivityLoginBinding
import com.user.kt_mvvm.main.MainActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.MainScope


class LoginActivity : AppCompatActivity() {
    //建立LoginViewModel的viewmodel物件
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //透過ViewModelProvider指派LoginViewModel的viewModel，ViewModelFactory()可帶參數
        viewModel =
            ViewModelProvider(this, ViewModelFactory()).get(LoginViewModel::class.java)

        //此Binding代表view中的data<>
        val Binding =
            DataBindingUtil.setContentView<ActivityLoginBinding>(this, R.layout.activity_login)

        //將view與LoginViewModel的viewmodel連接
        Binding.viewModel = viewModel
        //加上這行才會自動更新
        Binding.lifecycleOwner = this

        //出現提示bar
        setupSnackBar()
        //出現異常
        loginErrorObserve()
        //登入成功後跳轉到首頁
        loginSucessObserve()

    }

    private fun setupSnackBar() {
        //當事件改變時(帳密空白，登入成功失敗...)，出現提示
        viewModel.snackBarText.observe(this, Observer { event ->
            event.getContentIfNotHandled()?.let {
                val snackBarText = it
                Snackbar.make(layoutView, snackBarText, Snackbar.LENGTH_LONG).show()
            }
        })
    }

    private fun loginErrorObserve(){
        viewModel.loginError.observe(this, Observer {event ->
            event.getContentIfNotHandled()?.let {
                //val snackBarText = it
                MaterialAlertDialogBuilder(this)
                    .setMessage("系統異常，請稍後再試")
                    .setPositiveButton("重試") { _: DialogInterface?, _: Int ->
                        viewModel.login()
                    }
                    .setNegativeButton("確定") { _, _ ->
                    }
                    .show()
            }
        })
    }

    private fun loginSucessObserve() {
        //當事件改變時(帳密空白，登入成功失敗...)，出現提示
        viewModel.userIdName.observe(this, Observer {
            val userIdName = it
            if(userIdName.isNotEmpty()){
                var intent = Intent(this, MainActivity::class.java)
                var bundle = Bundle()
                bundle.putStringArray("keyIdName",userIdName)
                intent.putExtra("bundle",bundle)
                startActivity(intent)
            }
        })
    }
}
