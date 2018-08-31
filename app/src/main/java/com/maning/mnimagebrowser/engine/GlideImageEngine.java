package com.maning.mnimagebrowser.engine;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.maning.imagebrowserlibrary.ImageEngine;
import com.maning.mnimagebrowser.R;

/**
 * author : maning
 * time   : 2018/04/10
 * desc   : GlideImageEngine
 * version: 1.0
 */
public class GlideImageEngine implements ImageEngine {

    @Override
    public void loadImage(Context context, String url, ImageView imageView, final View progressView) {
        Glide.with(context)
                .load(url)
                .asBitmap()
                .fitCenter()
                .placeholder(R.drawable.default_placeholder)
                .error(R.mipmap.ic_launcher)
                .listener(new RequestListener<String, Bitmap>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
                        progressView.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        progressView.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(imageView);
    }

}
