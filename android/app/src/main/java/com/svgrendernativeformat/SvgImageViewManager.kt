package com.svgrendernativeformat

import com.facebook.react.bridge.ReactContext
import com.facebook.react.bridge.ReadableMap
import com.facebook.react.uimanager.SimpleViewManager
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.annotations.ReactProp
import com.svgrendernativeformat.svg.ReactSvgRenderer

class SvgImageViewManager(private val reactContext: ReactContext) :
    SimpleViewManager<SvgImageView>() {
    override fun getName(): String {
        return "SvgImageView"
    }

    override fun createViewInstance(themedReactContext: ThemedReactContext): SvgImageView {
        return SvgImageView(themedReactContext)
    }

    @ReactProp(name = "svgComponent")
    fun setSvgComponent(view: SvgImageView, props: ReadableMap) {
        val renderer = ReactSvgRenderer(view.context as ThemedReactContext, props)
        val bitmap = renderer.renderToImage()
        view.setImageBitmap(bitmap)
    }
}