package com.maning.imagebrowserlibrary;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

/**
 * author : maning
 * time   : 2018/04/10
 * desc   : 图片引擎
 * version: 1.0
 */
public interface ImageEngine {

    /**
     * 加载图片方法
     *
     * @param context      上下文
     * @param url          图片地址
     * @param imageView    ImageView
     * @param progressView 进度View
     */
    void loadImage(Context context, String url, ImageView imageView, View progressView);

}
