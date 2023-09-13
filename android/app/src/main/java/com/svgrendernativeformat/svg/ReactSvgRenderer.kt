package com.svgrendernativeformat.svg

import android.graphics.Bitmap
import android.view.View
import android.view.ViewGroup
import com.facebook.react.bridge.ReadableMap
import com.facebook.react.uimanager.ReactStylesDiffMap
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.UIManagerModule
import com.horcrux.svg.SvgView

class ReactSvgRenderer(private val context: ThemedReactContext, private val input: ReadableMap) {

    fun renderToImage(): Bitmap {
        val rootView = renderChildView(null, input) as SvgView
        val width = input.getMap("props")!!.getDouble("width").toInt()
        val height = input.getMap("props")!!.getDouble("height").toInt()
        return rootView.renderToBitmap(width, height)
    }

    private fun renderChildView(parent: View?, props: ReadableMap): View {
        val type = props.getString("type")!!
        val view = createView(type, props.getMap("props")!!)
        (parent as? ViewGroup)?.addView(view)

        val children = props.getArray("children")
        if (children == null || children.size() == 0) {
            return view
        }

        for (i in 0 until children.size()) {
            renderChildView(view, children.getMap(i))
        }

        return view
    }

    private fun createView(name: String, props: ReadableMap): View {
        val reactProps = ReactStylesDiffMap(props)
        return context.getNativeModule(UIManagerModule::class.java)!!
            .viewManagerRegistry_DO_NOT_USE.get(name)!!
            .createView(View.generateViewId(), context, reactProps, null, null)
    }

}