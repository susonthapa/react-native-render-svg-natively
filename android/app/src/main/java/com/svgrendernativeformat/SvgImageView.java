package com.svgrendernativeformat;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.facebook.react.views.imagehelper.ImageSource;

public class SvgImageView extends FrameLayout {

    private ImageView imageView;

    public SvgImageView(@NonNull Context context) {
        this(context, null);
    }

    public SvgImageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SvgImageView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public SvgImageView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        imageView = new ImageView(context);
        addView(imageView);
    }

    public void setImageUri(String path) {
        CustomTarget<Bitmap> target = new CustomTarget<Bitmap>(1200, 1200) {
            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                imageView.setImageBitmap(resource);
            }

            @Override
            public void onLoadCleared(@Nullable Drawable placeholder) {}
        };

        ImageSource source = new ImageSource(getContext(), path);
        Uri uri = Uri.parse(source.getUri().toString().replace("res:/", "android.resource" + "://" + getContext().getPackageName() + "/"));
        GlideApp.with(getContext())
                .as(Bitmap.class)
                .load(uri)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(target);
    }
}
