package com.svgrendernativeformat

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.ImageView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.facebook.react.views.imagehelper.ImageSource

class SvgImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : FrameLayout(context, attrs, defStyleAttr, defStyleRes) {
    private lateinit var imageView: ImageView

    init {
        init(context)
    }

    private fun init(context: Context) {
        imageView = ImageView(context)
        addView(imageView)
    }

    fun setImageBitmap(bitmap: Bitmap) {
        imageView.setImageBitmap(bitmap)
    }

    fun setImage(path: String, width: Int, height: Int) {
        val target = object : CustomTarget<Bitmap>(width, height) {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                imageView.setImageBitmap(resource)
            }

            override fun onLoadCleared(placeholder: Drawable?) {}
        }
        if (path.contains("http")) {
            GlideApp.with(context)
                .`as`(Bitmap::class.java)
                .load(Uri.parse(path))
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(target)
        } else {
            val id = context.resources.getIdentifier(path, "raw", context.packageName)
            GlideApp.with(context)
                .`as`(Bitmap::class.java)
                .load(id)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(target)
        }
    }
}