package com.svgrendernativeformat

import com.facebook.react.ReactPackage
import com.facebook.react.bridge.NativeModule
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.uimanager.ViewManager
import com.svgrendernativeformat.svg.SvgRendererManager

class SvgImagePackage : ReactPackage {
    override fun createNativeModules(reactApplicationContext: ReactApplicationContext): List<NativeModule> {
        return emptyList()
    }

    override fun createViewManagers(reactApplicationContext: ReactApplicationContext): List<ViewManager<*, *>> {
        return listOf(SvgImageViewManager(reactApplicationContext), SvgRendererManager(reactApplicationContext))
    }
}