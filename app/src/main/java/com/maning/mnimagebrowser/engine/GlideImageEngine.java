package com.maning.mnimagebrowser.engine;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.maning.imagebrowserlibrary.ImageEngine;
import com.maning.mnimagebrowser.R;

/**
 * <pre>
 *     author : maning
 *     e-mail : xxx@xx
 *     time   : 2018/04/10
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class GlideImageEngine implements ImageEngine {

    @Override
    public void loadImage(Context context, final String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .asBitmap()
                .fitCenter()
                .placeholder(R.drawable.default_placeholder)
                .error(R.mipmap.ic_launcher)
                .into(imageView);
    }

}
