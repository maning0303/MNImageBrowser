package com.maning.mnimagebrowser.engine;

import android.content.Context;
import android.widget.ImageView;

import com.maning.imagebrowserlibrary.ImageEngine;
import com.maning.mnimagebrowser.R;
import com.squareup.picasso.Picasso;

/**
 * <pre>
 *     author : maning
 *     e-mail : xxx@xx
 *     time   : 2018/04/10
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class PicassoImageEngine implements ImageEngine {

    @Override
    public void loadImage(Context context, String url, ImageView imageView) {
        Picasso.with(context).load(url).placeholder(R.mipmap.ic_launcher).into(imageView);
    }

}
