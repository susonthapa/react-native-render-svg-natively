package com.svgrendernativeformat

import android.content.Context
import android.graphics.Bitmap
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.ImageView

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
}