package com.svgrendernativeformat.svg

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.View
import android.view.ViewGroup
import com.facebook.react.bridge.ReactContext
import com.facebook.react.views.view.ReactViewGroup
import com.horcrux.svg.SvgView


@SuppressLint("ViewConstructor")
class SvgRenderer(context: ReactContext) : ReactViewGroup(context) {

    var svgProp: SvgProps? = null

    private fun measureAndLayoutAll(view: ViewGroup) {

        for (i in 0 until view.childCount) {
            val child = view.getChildAt(i)
            if (child is ViewGroup) {
                measureAndLayoutAll(child)
            }
            child.measure(
                MeasureSpec.makeMeasureSpec(view.measuredWidth, MeasureSpec.AT_MOST),
                MeasureSpec.makeMeasureSpec(view.measuredHeight, MeasureSpec.AT_MOST)
            )
            child.layout(0, 0, child.measuredWidth, child.measuredHeight)
        }

    }

    fun getRenderedBitmap(child: View): Bitmap? {
        val props = svgProp!!

        if (child is SvgView) {
            return child.renderToBitmap(props.width, props.height)
        }

        return null

//        val bitmap = Bitmap.createBitmap(props.width, props.height, Bitmap.Config.ARGB_8888)
//        val canvas = Canvas(bitmap)
//
//        child.measure(
//            MeasureSpec.makeMeasureSpec(props.width, MeasureSpec.EXACTLY),
//            MeasureSpec.makeMeasureSpec(props.height, MeasureSpec.EXACTLY)
//        )
//        child.layout(0, 0, child.measuredWidth, child.measuredHeight)
//        if (child is ViewGroup) {
//            measureAndLayoutAll(child)
//        }

//        renderViewToCanvas(canvas, child)
//        return bitmap
    }

    private fun renderViewToCanvas(canvas: Canvas, view: View) {
        // Draw children if the view has children
        if (view is ViewGroup) {
            // Draw children
            // Hide visible children - this needs to be done because view.draw(canvas)
            // will render all visible non-texture/surface views directly - causing
            // views to be rendered twice - once by view.draw() and once when we
            // enumerate children. We therefore need to turn off rendering of visible
            // children before we call view.draw:
            val visibleChildren: MutableList<View> = ArrayList()
            for (i in 0 until view.childCount) {
                val child = view.getChildAt(i)
                if (child.visibility == VISIBLE) {
                    visibleChildren.add(child)
                    child.visibility = INVISIBLE
                }
            }

            // Draw ourselves
            view.draw(canvas)

            // Enable children again
            for (i in visibleChildren.indices) {
                val child = visibleChildren[i]
                child.visibility = VISIBLE
            }

            // Draw children
            for (i in 0 until view.childCount) {
                val child = view.getChildAt(i)

                // skip all invisible to user child views
                if (child.visibility != VISIBLE) continue

                // Regular views needs to be rendered again to ensure correct z-index
                // order with texture views and surface views.
                renderViewToCanvas(canvas, child)
            }
        } else {
            // Draw ourselves
            view.draw(canvas)
        }

    }


}