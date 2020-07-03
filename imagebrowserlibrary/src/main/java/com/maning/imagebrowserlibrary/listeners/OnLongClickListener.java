package com.maning.imagebrowserlibrary.listeners;

import android.view.View;
import android.widget.ImageView;

import androidx.fragment.app.FragmentActivity;

/**
 * author : maning
 * time   : 2018/04/10
 * desc   : 长按监听
 * version: 1.0
 */
public interface OnLongClickListener {

    void onLongClick(FragmentActivity activity, View view, int position, String url);

}
