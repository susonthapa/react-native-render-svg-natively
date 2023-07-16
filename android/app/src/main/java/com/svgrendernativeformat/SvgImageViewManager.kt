package com.svgrendernativeformat

import com.facebook.react.bridge.ReadableMap
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

    @ReactProp(name = "param")
    fun setImageParam(view: SvgImageView, props: ReadableMap) {
        val uri = props.getString("uri")!!
        val width = props.getInt("width")
        val height = props.getInt("height")
        view.setImage(uri, width, height)
    }
}