package com.svgrendernativeformat

import com.facebook.react.bridge.ReactContext
import com.facebook.react.bridge.ReadableMap
import com.facebook.react.uimanager.SimpleViewManager
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.UIManagerModule
import com.facebook.react.uimanager.annotations.ReactProp
import com.svgrendernativeformat.svg.ReactSvgRenderer
import com.svgrendernativeformat.svg.SvgRendererManager

class SvgImageViewManager(private val reactContext: ReactContext) :
    SimpleViewManager<SvgImageView>() {
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

    @ReactProp(name = "svgName")
    fun setSvgName(view: SvgImageView, name: String?) {
        val viewManager =
            reactContext.getNativeModule(UIManagerModule::class.java)?.viewManagerRegistry_DO_NOT_USE?.get(
                SvgRendererManager.NAME
            )
        if (viewManager is SvgRendererManager) {
            name?.let {
                viewManager.addRenderCallback(name) {
                    view.setImageBitmap(it)
                }
            }
        }
    }

    @ReactProp(name = "svgComponent")
    fun setSvgComponent(view: SvgImageView, props: ReadableMap) {
        val renderer = ReactSvgRenderer(view.context as ThemedReactContext, props)
        val bitmap = renderer.renderToImage()
        view.setImageBitmap(bitmap)
    }
}