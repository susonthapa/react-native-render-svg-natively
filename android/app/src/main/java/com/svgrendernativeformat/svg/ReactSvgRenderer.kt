package com.svgrendernativeformat.svg

import android.graphics.Bitmap
import android.view.View
import android.view.ViewGroup
import com.facebook.react.bridge.ReadableMap
import com.facebook.react.uimanager.ReactStylesDiffMap
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.UIManagerModule
import com.facebook.react.uimanager.ViewManager
import com.horcrux.svg.SvgView

class ReactSvgRenderer(private val context: ThemedReactContext, private val input: ReadableMap) {


    fun renderToBitmap(): Bitmap {
        val rootView = renderChildView(null, input) as SvgView
        return rootView.renderToBitmap(413, 413)
    }

    private fun renderChildView(parent: View?, childProps: ReadableMap): View {
        val type = childProps.getString("type")!!
        val manager = getViewManager(type)

        val props = ReactStylesDiffMap(childProps.getMap("props")!!)
        val view = manager.createView(View.generateViewId(), context, props, null, null)
        if (parent != null && parent is ViewGroup) {
            parent.addView(view)
        }

        val children = childProps.getArray("children")
        if (children == null || children.size() == 0) {
            return view
        }

        for (i in 0 until children.size()) {
            val tempProp = children.getMap(i)
            renderChildView(view, tempProp)
        }

        return view
    }

    private fun getViewManager(name: String): ViewManager<*, *> {
        return context.getNativeModule(UIManagerModule::class.java)!!
            .viewManagerRegistry_DO_NOT_USE.get(name)!!
    }

}