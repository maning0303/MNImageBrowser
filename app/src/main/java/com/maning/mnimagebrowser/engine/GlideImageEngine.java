package com.maning.mnimagebrowser.engine;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
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
    public void loadImage(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .asBitmap()
                .fitCenter()
                .placeholder(R.drawable.default_placeholder)
                .into(imageView);
    }

}
