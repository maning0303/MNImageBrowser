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
     * @param context         上下文
     * @param bitmap             图片
     * @param imageView       ImageView
     * @param progressView    进度View
     * @param customImageView 自定义加载图片，替换PhotoView
     */
    void loadImage(Context context, Bitmap bitmap, ImageView imageView, View progressView, View customImageView);

}