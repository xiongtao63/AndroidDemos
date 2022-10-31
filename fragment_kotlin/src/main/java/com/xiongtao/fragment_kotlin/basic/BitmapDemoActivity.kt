package com.xiongtao.fragment_kotlin.basic

import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.media.Image
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.commit
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.CustomViewTarget
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition
import com.xiongtao.fragment_kotlin.MyTransform
import com.xiongtao.fragment_kotlin.ProgressDialog
import com.xiongtao.fragment_kotlin.R
import org.jetbrains.anko.find

class BitmapDemoActivity : AppCompatActivity() {
    private lateinit var image: ImageView
    private var bitmap: Bitmap? = null
    private var bitmapFragment: BitmapFragment? = null
    private val progressDialog = ProgressDialog()

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_bitmap)
        image = find(R.id.image)

        bitmapFragment = supportFragmentManager.findFragmentByTag("data") as? BitmapFragment
        if(bitmapFragment == null){
            bitmapFragment = BitmapFragment()
            supportFragmentManager.commit {
                add(bitmapFragment!!,"data")
            }
        }
        bitmap = bitmapFragment!!.data
        initData()
    }

    private fun initData() {

        if(bitmap == null){
            Glide.with(this)
                .load("https://img1.baidu.com/it/u=3009731526,373851691&fm=253&app=138&size=w931&n=0&f=JPEG&fmt=auto?sec=1667322000&t=c314da1abdcbb08d7812f8c8d97faac6")
                .transform(MyTransform(this,150))
                .into(object : CustomViewTarget<ImageView,Drawable>(image){
                    override fun onStart() {
                        super.onStart()
                        progressDialog.show(supportFragmentManager,"")
                    }
                    override fun onLoadFailed(errorDrawable: Drawable?) {
                    }

                    override fun onResourceReady(
                        resource: Drawable,
                        transition: Transition<in Drawable>?
                    ) {
                        bitmapFragment!!.data = Bitmap.createBitmap(resource.toBitmap())
                        image.setImageDrawable(resource )
                        progressDialog.dismiss()
                    }

                    override fun onResourceCleared(placeholder: Drawable?) {

                    }

                })

        }else {
            image.setImageBitmap(bitmap)
        }
    }
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        Log.i("Zero","${this::class.simpleName} onConfigurationChanged")
    }

}