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
    private var imageView: ImageView? = null

    init {
        init(context)
    }

    private fun init(context: Context) {
        imageView = ImageView(context)
        addView(imageView)
    }

    fun setImage(path: String?, width: Int, height: Int) {
        val target = object : CustomTarget<Bitmap>(width, height) {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                imageView!!.setImageBitmap(resource)
            }

            override fun onLoadCleared(placeholder: Drawable?) {}
        }
        val source = ImageSource(context, path)
        val uri = Uri.parse(
            source.uri.toString()
                .replace("res:/", "android.resource" + "://" + context.packageName + "/")
        )
        GlideApp.with(context)
            .`as`(Bitmap::class.java)
            .load(uri)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .into(target)
    }
}