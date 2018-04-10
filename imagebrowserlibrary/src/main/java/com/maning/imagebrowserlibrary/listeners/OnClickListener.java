package com.maning.imagebrowserlibrary.listeners;

import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;

/**
 * <pre>
 *     author : maning
 *     e-mail : xxx@xx
 *     time   : 2018/04/10
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public interface OnClickListener {

    void onClick(FragmentActivity activity,ImageView view, int position, String url);

}
