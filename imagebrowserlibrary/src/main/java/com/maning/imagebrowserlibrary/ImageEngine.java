package com.maning.imagebrowserlibrary;

import android.content.Context;
import android.widget.ImageView;

/**
 * <pre>
 *     author : maning
 *     e-mail : xxx@xx
 *     time   : 2018/04/10
 *     desc   : 图片引擎
 *     version: 1.0
 * </pre>
 */
public interface ImageEngine {

    void loadImage(Context context, String url, ImageView imageView);

}
