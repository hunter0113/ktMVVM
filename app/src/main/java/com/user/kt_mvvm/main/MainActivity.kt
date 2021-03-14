package com.user.kt_mvvm.main

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.user.kt_mvvm.R
import com.user.kt_mvvm.databinding.ActivityMainBinding
import com.user.kt_mvvm.image.ImagesPagerAdapter
import com.user.kt_mvvm.login.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //建立MainViewModel的viewmodel物件
    private lateinit var viewModel: MainViewModel

    var prePosition: Int = 0
    var nowPosition: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //透過ViewModelProvider指派MainViewModel的viewModel，ViewModelFactory()帶Id與Name參數
        viewModel =
            ViewModelProvider(this, ViewModelFactory()).get(MainViewModel::class.java)

        //此Binding代表view中的data<>
        val Binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        //將view與MainViewModel的viewmodel連接
        Binding.viewModel = viewModel
        //加上這行才會自動更新
        Binding.lifecycleOwner = this

        val imageUrls = listOf(
                "https://i.imgur.com/dYQZ1CT.jpg",
                "https://i.imgur.com/BS8fykH.jpg",
                "https://i.imgur.com/KLscaeJ.jpg"
        )

        val imagesPagerAdapter = ImagesPagerAdapter(this, imageUrls)
        viewPager.adapter = imagesPagerAdapter

        initPositionPoints(imageUrls)

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
                when (state) {
                    1 -> Log.d("viewPager", "1:當手指按下開始滑動")
                    2 -> Log.d("viewPager", "2:當手指提起")
                    3 -> Log.d("viewPager", "3:滑動結束，也就是頁面停止時")
                }
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                Log.d("viewPager", "滑動至頁面_position:$position")
                Log.d("viewPager", "滑動百分比Offset:$positionOffset")
                Log.d("viewPager", "滑動Pixels:$positionOffsetPixels")
            //在螢幕滑動的過程會被呼叫
            //positionOffset：目前頁面因滑動而偏移了多少比例，
            // 如果往右滑動，這個值會不斷變大，最後再趨近1的時候會變為0
            // 如果往左滑動，這個值會不斷變小，最後變為0。
            //positionOffsetPixels，目前頁面因滑動而偏移了多少像素
            }

            override fun onPageSelected(position: Int) {
                linearLayout.getChildAt(prePosition).setBackgroundResource(R.drawable.point)
                linearLayout.getChildAt(position).setBackgroundResource(R.drawable.point_selected)
                nowPosition = position
                prePosition = position
            }
            })
        }

        private fun initPositionPoints(imageUrls: List<String>) {
            (0 until imageUrls.count()).forEach {
                val imageView = ImageView(this)
                imageView.setBackgroundResource(R.drawable.point)
                val layParams = LinearLayout.LayoutParams(20, 20)
                layParams.leftMargin = 30
                linearLayout.addView(imageView, layParams)
            }
            linearLayout.getChildAt(0).setBackgroundResource(R.drawable.point_selected)

        }
}