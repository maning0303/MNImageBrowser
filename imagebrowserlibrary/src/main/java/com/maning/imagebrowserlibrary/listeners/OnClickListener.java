package com.maning.imagebrowserlibrary.listeners;

import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;

/**
 * author : maning
 * time   : 2018/04/10
 * desc   : 点击监听
 * version: 1.0
 */
public interface OnClickListener {

    void onClick(FragmentActivity activity, View view, int position, String url);

}
