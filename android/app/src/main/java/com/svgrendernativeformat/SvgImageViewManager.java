package com.svgrendernativeformat;

import androidx.annotation.NonNull;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;

public class SvgImageViewManager extends SimpleViewManager<SvgImageView> {
    @NonNull
    @Override
    public String getName() {
        return "SvgImage";
    }

    @NonNull
    @Override
    protected SvgImageView createViewInstance(@NonNull ThemedReactContext themedReactContext) {
        return new SvgImageView(themedReactContext);
    }

    @ReactProp(name = "imageUri")
    public void setImageUri(SvgImageView view, String uri) {
        view.setImageUri(uri);
    }
}
