package com.svgrendernativeformat

import android.util.Log
import com.facebook.react.bridge.ReactContext
import com.facebook.react.uimanager.UIManagerHelper

const val TAG = "Extensions"

inline fun <reified T> ReactContext.findView(tag: Int, crossinline callback: (T) -> Unit) {
    runOnUiQueueThread {
        try {
            val manager = UIManagerHelper.getUIManagerForReactTag(this, tag)
            val view = manager?.resolveView(tag)
            if (view == null) {
                Log.d(TAG, "findView: view with tag $tag not found")
                return@runOnUiQueueThread
            }
            if (view is T) {
                callback(view)
            } else {
                Log.d(TAG, "findView: view is not of type ${T::class.java}")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}