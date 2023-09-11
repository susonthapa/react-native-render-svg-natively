package com.svgrendernativeformat.svg

import android.graphics.Bitmap
import android.view.View
import com.facebook.react.bridge.ReactContext
import com.facebook.react.bridge.ReadableMap
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.ViewGroupManager
import com.facebook.react.uimanager.annotations.ReactProp

class SvgRendererManager(reactContext: ReactContext) : ViewGroupManager<SvgRenderer>() {
    private val bitmapCache = mutableMapOf<String, Bitmap>()
    private val renderCallbacks = mutableMapOf<String, MutableList<(Bitmap) -> Unit>>()
    private val dummyViewList = mutableListOf<View>()

    override fun getName(): String {
        return NAME
    }

    override fun createViewInstance(context: ThemedReactContext): SvgRenderer {
        return SvgRenderer(context)
    }

    fun addRenderCallback(name: String, callback: (Bitmap) -> Unit) {
        val cachedBitmap = bitmapCache[name]
        if (cachedBitmap != null) {
            callback(cachedBitmap)
        } else {
            if (renderCallbacks[name] == null) {
                renderCallbacks[name] = mutableListOf()
            }
            renderCallbacks[name]?.add(callback)
        }
    }

    @ReactProp(name = "svgProps")
    fun setSvgName(view: SvgRenderer, props: ReadableMap) {
        val svgProps = SvgProps(
            props.getString("name")!!,
            props.getInt("width"),
            props.getInt("height")
        )
        view.svgProp = svgProps
    }

    override fun addView(parent: SvgRenderer, child: View, index: Int) {
        dummyViewList.add(index, child)
        parent.svgProp?.let {
            renderView(parent, it, child)
        }
    }

    private fun renderView(parent: SvgRenderer, props: SvgProps, child: View) {
        val bitmap = parent.getRenderedBitmap(child) ?: return
        bitmapCache[props.name] = bitmap
        renderCallbacks[props.name]?.forEach { it.invoke(bitmap) }
        renderCallbacks.remove(props.name)
    }

    override fun removeViewAt(parent: SvgRenderer, index: Int) {
        bitmapCache.remove(parent.svgProp?.name)
        dummyViewList.removeAt(index)
    }

    override fun getChildAt(parent: SvgRenderer, index: Int): View {
        return dummyViewList[index]
    }

    override fun getChildCount(parent: SvgRenderer): Int {
        return dummyViewList.size
    }

    companion object {
        const val NAME = "SvgRenderer"
    }

}