package com.svgrendernativeformat.svg

import android.graphics.Bitmap
import android.view.View
import com.facebook.react.bridge.ColorPropConverter
import com.facebook.react.bridge.ReactContext
import com.facebook.react.bridge.ReadableMap
import com.facebook.react.bridge.ReadableNativeMap
import com.facebook.react.bridge.WritableMap
import com.facebook.react.bridge.WritableNativeArray
import com.facebook.react.bridge.WritableNativeMap
import com.facebook.react.uimanager.ReactStylesDiffMap
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.UIManagerModule
import com.facebook.react.uimanager.ViewGroupManager
import com.facebook.react.uimanager.ViewManager
import com.facebook.react.uimanager.annotations.ReactProp
import com.horcrux.svg.GroupView
import com.horcrux.svg.SvgView

class SvgRendererManager(private val reactContext: ReactContext) : ViewGroupManager<SvgRenderer>() {
    private val bitmapCache = mutableMapOf<String, Bitmap>()
    private val renderCallbacks = mutableMapOf<String, MutableList<(Bitmap) -> Unit>>()
    private val dummyViewList = mutableListOf<View>()
    private lateinit var themedContext: ThemedReactContext

    override fun getName(): String {
        return NAME
    }

    override fun createViewInstance(context: ThemedReactContext): SvgRenderer {
        themedContext = context
        return SvgRenderer(context)
    }

    private fun demoSVGRender(themedReactContext: ThemedReactContext, props: SvgProps): SvgView {
        val svgManager = reactContext.getNativeModule(UIManagerModule::class.java)!!
            .viewManagerRegistry_DO_NOT_USE.get("RNSVGSvgViewAndroid")!!

        val pathManager = reactContext.getNativeModule(UIManagerModule::class.java)!!
            .viewManagerRegistry_DO_NOT_USE.get("RNSVGPath")!!

        val groupManager = reactContext.getNativeModule(UIManagerModule::class.java)!!
            .viewManagerRegistry_DO_NOT_USE.get("RNSVGGroup")

        val svgProps = ReactStylesDiffMap(WritableNativeMap().apply {
            putInt("color", ColorPropConverter.getColor(-65536.0, themedContext))
            putMap("stroke", WritableNativeMap().apply {
                putInt("type", 0)
                putInt("payload", -16181)
            })
            putMap("fill", WritableNativeMap().apply {
                putInt("type", 0)
                putInt("payload", -1)
            })
            putDouble("minX", 0.0)
            putDouble("minY", 0.0)
            putDouble("vbWidth", 1024.0)
            putDouble("vbHeight", 1024.0)
            putDouble("bbWidth", 150.0)
            putDouble("bbHeight", 150.0)
            putString("align", "xMidYMid")
            putDouble("meetOrSlice", 0.0)
            putDouble("strokeWidth", 5.0)
            putArray("propList", WritableNativeArray().apply {
                pushString("fill")
                pushString("stroke")
                pushString("strokeWidth")
            })
        })

        val svgView = svgManager.createView(10, themedReactContext, svgProps, null, null) as SvgView
        val groupView = groupManager.createView(10, themedReactContext, svgProps, null, null) as GroupView
        svgView.addView(groupView)

        groupView.addView(getPathView(pathManager, themedReactContext, "M964.858 634.71c-5.924 0-11.848-3.384-15.233-9.308-56.7-112.556-279.272-245.422-280.965-247.114-8.463-5.078-11.002-15.233-5.924-22.85 5.078-8.463 15.233-11.002 22.85-5.924 6.77 4.231 116.786 69.395 203.953 150.638-89.705-166.717-189.567-266.578-190.413-268.271-6.77-6.77-6.77-16.926 0-23.696 6.77-6.77 16.926-6.77 23.696 0 5.924 5.924 155.716 157.408 257.27 402.83 3.384 8.463 0 17.772-8.463 22.003-1.693.846-4.232 1.693-6.77 1.693z"))
        groupView.addView(getPathView(pathManager, themedReactContext, "M897.156 583.934a50.777 50.777 0 1 0 101.554 0 50.777 50.777 0 1 0-101.554 0Z"))
        groupView.addView(getPathView(pathManager, themedReactContext, "M947.933 651.636c-16.926 0-33.005-5.924-45.7-17.772-13.54-12.694-21.157-28.773-22.003-47.391-.846-17.772 5.924-35.544 17.772-48.238 11.848-13.54 28.774-21.157 47.392-22.004 17.772-.846 35.544 5.924 48.238 17.772 13.54 11.848 21.157 28.774 22.003 47.392.846 17.772-5.924 35.544-17.772 48.238s-28.773 21.157-47.391 22.003h-2.54zm0-101.553h-1.693c-9.309 0-17.772 4.231-23.696 11.001-5.924 6.77-9.309 15.233-9.309 24.542 0 9.31 4.232 17.772 11.002 23.696 13.54 12.695 35.544 11.848 48.238-1.692 5.924-6.77 9.309-15.233 9.309-24.542 0-9.31-4.231-17.772-11.002-23.696-5.924-5.924-14.387-9.31-22.85-9.31zM50.875 634.71c-2.539 0-5.078-.847-6.77-1.693-8.463-3.385-11.848-13.54-8.463-22.003 101.553-245.422 251.345-396.906 257.27-402.83 6.77-6.77 16.925-6.77 23.695 0s6.77 16.926 0 23.696c-.846.846-100.707 101.554-190.413 267.425 87.167-80.397 197.183-146.407 203.954-149.792 8.462-5.078 18.618-1.693 22.85 5.924 5.077 7.617 1.692 18.618-5.925 22.85-2.539 1.692-225.11 134.558-280.965 247.114-3.385 5.924-9.31 9.309-15.233 9.309z"))
        groupView.addView(getPathView(pathManager, themedReactContext, "M677.123 440.066c-23.696 0-42.314-18.618-42.314-42.314s18.618-42.314 42.314-42.314 42.314 18.618 42.314 42.314-18.618 42.314-42.314 42.314zm0-50.777c-5.078 0-8.463 3.385-8.463 8.463s3.385 8.463 8.463 8.463 8.463-3.385 8.463-8.463-3.386-8.463-8.463-8.463z"))
        groupView.addView(getPathView(pathManager, themedReactContext, "M584.032 507.769a25.388 25.388 0 1 0 50.777 0 25.388 25.388 0 1 0-50.777 0Z"))
        groupView.addView(getPathView(pathManager, themedReactContext, "M609.42 550.083c-23.696 0-42.314-18.619-42.314-42.314s18.618-42.314 42.314-42.314 42.314 18.618 42.314 42.314-18.618 42.314-42.314 42.314zm0-50.777c-5.077 0-8.463 3.385-8.463 8.463s3.386 8.462 8.463 8.462 8.463-3.385 8.463-8.462-3.385-8.463-8.463-8.463z"))

        return svgView
    }

    private fun getPathView(manager: ViewManager<*, *>, context: ThemedReactContext, path: String): View {
        return manager.createView(10, context, ReactStylesDiffMap(
            WritableNativeMap().apply {
                putString("d", path)
            }
        ), null, null)
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

    @ReactProp(name = "svgComponent")
    fun setSvgComponent(view: SvgRenderer, props: ReadableMap) {
        val renderer = ReactSvgRenderer(view.context as ThemedReactContext, props)
        renderer.renderToBitmap()
    }

    @ReactProp(name = "svgProps")
    fun setSvgName(view: SvgRenderer, props: ReadableMap) {
        val svgProps = SvgProps(
            props.getString("name")!!,
            props.getInt("width"),
            props.getInt("height")
        )
        val svgView = demoSVGRender(themedContext, svgProps)

        svgView.toDataURL(svgProps.width, svgProps.height)
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