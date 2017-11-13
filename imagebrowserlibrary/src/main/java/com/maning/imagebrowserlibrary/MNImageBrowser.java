package com.maning.imagebrowserlibrary;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by maning on 2017/5/25.
 */
public class MNImageBrowser {

    /**
     * 打开浏览页面
     *
     * @param context   上下文
     * @param view      点击的当前View
     * @param position  默认打开第几个
     * @param viewPagerTransformType  图片滑动切换的效果：支持七种效果
     * @param imageList 数据源ArrayList<String>
     */
    public static void showImageBrowser(Context context, View view, int position, int viewPagerTransformType, ArrayList<String> imageList) {
        Intent intent = new Intent(context, MNImageBrowserActivity.class);
        intent.putExtra(MNImageBrowserActivity.IntentKey_ImageList, imageList);
        intent.putExtra(MNImageBrowserActivity.IntentKey_CurrentPosition, position);
        intent.putExtra(MNImageBrowserActivity.IntentKey_ViewPagerTransformType, viewPagerTransformType);
        startBrowserAvtivity(context, view, intent);
    }

    /**
     * 打开浏览页面
     *
     * @param context   上下文
     * @param view      点击的当前View
     * @param position  默认打开第几个
     * @param imageList 数据源ArrayList<String>
     */
    public static void showImageBrowser(Context context, View view, int position, ArrayList<String> imageList) {
        Intent intent = new Intent(context, MNImageBrowserActivity.class);
        intent.putExtra(MNImageBrowserActivity.IntentKey_ImageList, imageList);
        intent.putExtra(MNImageBrowserActivity.IntentKey_CurrentPosition, position);
        startBrowserAvtivity(context, view, intent);
    }

    private static void startBrowserAvtivity(Context context, View view, Intent intent) {
        //android V4包的类,用于两个activity转场时的缩放效果实现
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeScaleUpAnimation(view, view.getWidth() / 2, view.getHeight() / 2, 0, 0);
        try {
            ActivityCompat.startActivity(context, intent, optionsCompat.toBundle());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            context.startActivity(intent);
            ((Activity) context).overridePendingTransition(R.anim.browser_enter_anim, 0);
        }
    }

}
