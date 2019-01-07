package com.maning.imagebrowserlibrary;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.AnimRes;
import android.support.annotation.LayoutRes;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.maning.imagebrowserlibrary.listeners.OnClickListener;
import com.maning.imagebrowserlibrary.listeners.OnLongClickListener;
import com.maning.imagebrowserlibrary.listeners.OnPageChangeListener;
import com.maning.imagebrowserlibrary.model.ImageBrowserConfig;

import java.util.ArrayList;

/**
 * @author : maning
 * @desc : 启动管理
 */
public class MNImageBrowser {

    private Context context;
    private ImageBrowserConfig imageBrowserConfig;

    private MNImageBrowser(Context context) {
        this.context = context;
        imageBrowserConfig = new ImageBrowserConfig();
    }

    public static MNImageBrowser with(Context context) {
        return new MNImageBrowser(context);
    }

    public MNImageBrowser setImageUrl(String imageUrl) {
        ArrayList<String> imageList = new ArrayList<>();
        imageList.add(imageUrl);
        imageBrowserConfig.setImageList(imageList);
        return this;
    }

    public MNImageBrowser setImageList(ArrayList<String> imageList) {
        ArrayList<String> newImageList = new ArrayList<>();
        newImageList.addAll(imageList);
        imageBrowserConfig.setImageList(newImageList);
        return this;
    }

    public MNImageBrowser setCurrentPosition(int position) {
        imageBrowserConfig.setPosition(position);
        return this;
    }

    public MNImageBrowser setTransformType(ImageBrowserConfig.TransformType transformType) {
        imageBrowserConfig.setTransformType(transformType);
        return this;
    }

    public MNImageBrowser setImageEngine(ImageEngine imageEngine) {
        imageBrowserConfig.setImageEngine(imageEngine);
        return this;
    }

    public MNImageBrowser setOnClickListener(OnClickListener onClickListener) {
        imageBrowserConfig.setOnClickListener(onClickListener);
        return this;
    }

    public MNImageBrowser setOnLongClickListener(OnLongClickListener onLongClickListener) {
        imageBrowserConfig.setOnLongClickListener(onLongClickListener);
        return this;
    }

    public MNImageBrowser setOnPageChangeListener(OnPageChangeListener onPageChangeListener) {
        imageBrowserConfig.setOnPageChangeListener(onPageChangeListener);
        return this;
    }

    public MNImageBrowser setIndicatorType(ImageBrowserConfig.IndicatorType indicatorType) {
        imageBrowserConfig.setIndicatorType(indicatorType);
        return this;
    }

    public MNImageBrowser setIndicatorHide(boolean indicatorHide) {
        imageBrowserConfig.setIndicatorHide(indicatorHide);
        return this;
    }

    public MNImageBrowser setCustomShadeView(View customView) {
        imageBrowserConfig.setCustomShadeView(customView);
        return this;
    }

    public MNImageBrowser setCustomProgressViewLayoutID(@LayoutRes int customViewID) {
        imageBrowserConfig.setCustomProgressViewLayoutID(customViewID);
        return this;
    }

    public MNImageBrowser setScreenOrientationType(ImageBrowserConfig.ScreenOrientationType screenOrientationType) {
        imageBrowserConfig.setScreenOrientationType(screenOrientationType);
        return this;
    }

    public MNImageBrowser setFullScreenMode(boolean fullScreenMode) {
        imageBrowserConfig.setFullScreenMode(fullScreenMode);
        return this;
    }


    public MNImageBrowser setActivityOpenAnime(@AnimRes int activityOpenAnime) {
        imageBrowserConfig.setActivityOpenAnime(activityOpenAnime);
        return this;
    }

    public MNImageBrowser setActivityExitAnime(@AnimRes int activityExitAnime) {
        imageBrowserConfig.setActivityExitAnime(activityExitAnime);
        return this;
    }

    public void show(View view) {
        //判断是不是空
        if (imageBrowserConfig.getImageList() == null || imageBrowserConfig.getImageList().size() <= 0) {
            return;
        }
        if (imageBrowserConfig.getImageEngine() == null) {
            return;
        }
        if (imageBrowserConfig.getIndicatorType() == null) {
            imageBrowserConfig.setIndicatorType(ImageBrowserConfig.IndicatorType.Indicator_Number);
        }
        MNImageBrowserActivity.imageBrowserConfig = imageBrowserConfig;
        Intent intent = new Intent(context, MNImageBrowserActivity.class);
        startBrowserAvtivity(context, view, intent);
    }

    private void startBrowserAvtivity(Context context, View view, Intent intent) {
        if (imageBrowserConfig.getActivityOpenAnime() != R.anim.mn_browser_enter_anim) {
            context.startActivity(intent);
            ((Activity) context).overridePendingTransition(imageBrowserConfig.getActivityOpenAnime(), 0);
        } else {
            //android V4包的类,用于两个activity转场时的缩放效果实现
            ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeScaleUpAnimation(view, view.getWidth() / 2, view.getHeight() / 2, 0, 0);
            try {
                ActivityCompat.startActivity(context, intent, optionsCompat.toBundle());
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                context.startActivity(intent);
                ((Activity) context).overridePendingTransition(R.anim.mn_browser_enter_anim, 0);
            }
        }

    }

    /**
     * 获取当前Activity实例
     */
    public static FragmentActivity getCurrentActivity() {
        return MNImageBrowserActivity.getCurrentActivity();
    }

    /**
     * 手动关闭图片浏览器
     */
    public static void finishImageBrowser() {
        MNImageBrowserActivity.finishActivity();
    }

    /**
     * 获取当前ImageView
     */
    public static ImageView getCurrentImageView() {
        return MNImageBrowserActivity.getCurrentImageView();
    }

    /**
     * 获取当前位置
     */
    public static int getCurrentPosition() {
        return MNImageBrowserActivity.getCurrentPosition();
    }

    /**
     * 获取ViewPager
     */
    public static ViewPager getViewPager() {
        return MNImageBrowserActivity.getViewPager();
    }

    /**
     * 删除图片
     *
     * @param position
     */
    public static void removeImage(int position) {
        MNImageBrowserActivity.removeImage(position);
    }

    /**
     * 删除图片
     */
    public static void removeCurrentImage() {
        MNImageBrowserActivity.removeCurrentImage();
    }

    /**
     * 获取图片集合
     */
    public static ArrayList<String> getImageList() {
        return MNImageBrowserActivity.getImageList();
    }

}
