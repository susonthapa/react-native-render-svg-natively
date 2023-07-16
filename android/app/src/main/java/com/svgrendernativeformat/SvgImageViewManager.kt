package com.svgrendernativeformat

import com.facebook.react.uimanager.SimpleViewManager
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.annotations.ReactProp

class SvgImageViewManager : SimpleViewManager<SvgImageView>() {
    override fun getName(): String {
        return "SvgImageView"
    }

    override fun createViewInstance(themedReactContext: ThemedReactContext): SvgImageView {
        return SvgImageView(themedReactContext)
    }

    @ReactProp(name = "imageUri")
    fun setImageUri(view: SvgImageView, uri: String?) {
        view.setImageUri(uri)
    }
}