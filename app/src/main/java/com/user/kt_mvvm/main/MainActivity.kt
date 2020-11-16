package com.user.kt_mvvm.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.user.kt_mvvm.R
import com.user.kt_mvvm.databinding.ActivityMainBinding
import com.user.kt_mvvm.login.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    //建立MainViewModel的viewmodel物件
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var getIdtName = intent.getBundleExtra("bundle").getStringArray("keyIdName")

        //透過ViewModelProvider指派MainViewModel的viewModel，ViewModelFactory()帶Id與Name參數
        viewModel =
            ViewModelProvider(this, MainViewModelFactory(getIdtName!![0],getIdtName!![1])).get(MainViewModel::class.java)

        //此Binding代表view中的data<>
        val Binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        //將view與MainViewModel的viewmodel連接
        Binding.viewModel = viewModel
        //加上這行才會自動更新
        Binding.lifecycleOwner = this

    }
}