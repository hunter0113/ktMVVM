package com.user.kt_mvvm.login

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.user.kt_mvvm.R
import com.user.kt_mvvm.databinding.ActivityLoginBinding
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        viewModel =
            ViewModelProvider(this, ViewModelFactory()).get(LoginViewModel::class.java)

        val dataBinding =
            DataBindingUtil.setContentView<ActivityLoginBinding>(this, R.layout.activity_login)
        dataBinding.viewModel = viewModel

        dataBinding.lifecycleOwner = this

        setupSnackBar()

        viewModel.loginError.observe(this, Observer {event ->
            event.getContentIfNotHandled()?.let {
                val snackBarText = it
                MaterialAlertDialogBuilder(this)
                    .setMessage("系統異常")
                    .setPositiveButton("重試") { _: DialogInterface?, _: Int ->
                        viewModel.login()
                    }
                    .setNegativeButton("確定") { _, _ ->

                    }
                    .show()
            }
        })

    }

    private fun setupSnackBar() {
        viewModel.snackBarText.observe(this, Observer { event ->
            event.getContentIfNotHandled()?.let {
                val snackBarText = it
                Snackbar.make(layoutView, snackBarText, Snackbar.LENGTH_LONG).show()
            }
        })
    }
}
