package com.user.kt_mvvm.image

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.user.kt_mvvm.R
import kotlinx.android.synthetic.main.top_image_layout_item.view.*

class ImagesPagerAdapter (private val context: Context, private val imageUrls:List<String>):PagerAdapter(){
    var options : RequestOptions = RequestOptions().fitCenter()

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    //產生item，填充圖片
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(context).inflate( R.layout.top_image_layout_item, null)
        Glide.with(context).load(imageUrls[position]).apply(options).into(view.imageView)
        container.addView(view)
        return view
    }

    //當item被銷毀時的處理，當被銷毀時將其移除
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as ConstraintLayout)
    }

    override fun getCount(): Int {
        return imageUrls.count()
    }

}