package com.maning.imagebrowserlibrary.listeners;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;

/**
 * author : maning
 * time   : 2018/04/10
 * desc   : 长按监听
 * version: 1.0
 */
public interface OnLongClickListener {

    void onLongClick(FragmentActivity activity, ImageView view, int position, String url);

}
