package com.maning.imagebrowserlibrary.model;

import android.view.View;

import androidx.annotation.AnimRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.LayoutRes;

import com.maning.imagebrowserlibrary.ImageEngine;
import com.maning.imagebrowserlibrary.R;
import com.maning.imagebrowserlibrary.listeners.OnActivityLifeListener;
import com.maning.imagebrowserlibrary.listeners.OnClickListener;
import com.maning.imagebrowserlibrary.listeners.OnLongClickListener;
import com.maning.imagebrowserlibrary.listeners.OnPageChangeListener;

import java.util.ArrayList;

/**
 * author : maning
 * time   : 2018/04/10
 * desc   : 相关配置信息
 * version: 1.0
 */
public class ImageBrowserConfig {

    //枚举类型：切换动画类型
    public enum TransformType {
        Transform_Default,
        Transform_DepthPage,
        Transform_RotateDown,
        Transform_RotateUp,
        Transform_ZoomIn,
        Transform_ZoomOutSlide,
        Transform_ZoomOut,
    }

    //枚举类型：指示器类型
    public enum IndicatorType {
        Indicator_Circle,
        Indicator_Number,
    }

    //枚举类型：屏幕方向
    public enum ScreenOrientationType {
        //默认：竖屏
        ScreenOrientation_Portrait,
        //横屏
        Screenorientation_Landscape,
        //横竖屏全部支持
        Screenorientation_All,
    }

    //当前位置
    private int position;
    //切换效果
    private TransformType transformType = TransformType.Transform_Default;
    //指示器类型
    private IndicatorType indicatorType = IndicatorType.Indicator_Number;
    //图片源
    private ArrayList<String> imageList;

    private List<Bitmap> bitmapList;
    //图片加载引擎
    private ImageEngine imageEngine;
    //单击监听
    private OnClickListener onClickListener;
    //长按监听
    private OnLongClickListener onLongClickListener;
    //页面切换监听
    private OnPageChangeListener onPageChangeListener;
    //页面关闭监听
    private OnActivityLifeListener onActivityLifeListener;
    //设置屏幕的方向
    private ScreenOrientationType screenOrientationType = ScreenOrientationType.ScreenOrientation_Portrait;
    //是否隐藏指示器
    private boolean indicatorHide = false;
    //自定义View
    private View customShadeView;
    //自定义ProgressView
    private int customProgressViewLayoutID;
    //自定义显示ImageView
    private int customImageViewLayoutID;
    //全部模式：默认false
    private boolean isFullScreenMode = false;
    //下拉缩小效果：默认开启true
    private boolean isOpenPullDownGestureEffect = true;
    //打开动画
    @AnimRes
    private int activityOpenAnime = R.anim.mn_browser_enter_anim;
    //关闭动画
    @AnimRes
    private int activityExitAnime = R.anim.mn_browser_exit_anim;
    //3.1.6新增
    //状态字体是否黑色模式
    private boolean isStatusBarDarkFont = false;
    //自定义背景色
    private String windowBackgroundColor = "#000000";
    //文字指示器颜色
    private String indicatorTextColor = "#FFFFFF";
    //文字指示器文字大小，单位sp
    private int indicatorTextSize = 16;
    //指示器选中的背景
    @DrawableRes
    private int indicatorSelectedResId = R.drawable.mn_browser_indicator_bg_selected;
    //指示器默认
    @DrawableRes
    private int indicatorUnSelectedResId = R.drawable.mn_browser_indicator_bg_unselected;

    public int getIndicatorTextSize() {
        return indicatorTextSize;
    }

    public void setIndicatorTextSize(int indicatorTextSize) {
        this.indicatorTextSize = indicatorTextSize;
    }

    public String getWindowBackgroundColor() {
        return windowBackgroundColor;
    }

    public void setWindowBackgroundColor(String windowBackgroundColor) {
        this.windowBackgroundColor = windowBackgroundColor;
    }

    public String getIndicatorTextColor() {
        return indicatorTextColor;
    }

    public void setIndicatorTextColor(String indicatorTextColor) {
        this.indicatorTextColor = indicatorTextColor;
    }

    public int getIndicatorSelectedResId() {
        return indicatorSelectedResId;
    }

    public void setIndicatorSelectedResId(int indicatorSelectedResId) {
        this.indicatorSelectedResId = indicatorSelectedResId;
    }

    public int getIndicatorUnSelectedResId() {
        return indicatorUnSelectedResId;
    }

    public void setIndicatorUnSelectedResId(int indicatorUnSelectedResId) {
        this.indicatorUnSelectedResId = indicatorUnSelectedResId;
    }

    public boolean isStatusBarDarkFont() {
        return isStatusBarDarkFont;
    }

    public void setStatusBarDarkFont(boolean statusBarDarkFont) {
        isStatusBarDarkFont = statusBarDarkFont;
    }

    public boolean isOpenPullDownGestureEffect() {
        return isOpenPullDownGestureEffect;
    }

    public void setOpenPullDownGestureEffect(boolean openPullDownGestureEffect) {
        isOpenPullDownGestureEffect = openPullDownGestureEffect;
    }

    public int getActivityOpenAnime() {
        return activityOpenAnime;
    }

    public void setActivityOpenAnime(@AnimRes int activityOpenAnime) {
        this.activityOpenAnime = activityOpenAnime;
    }

    public int getActivityExitAnime() {
        return activityExitAnime;
    }

    public void setActivityExitAnime(@AnimRes int activityExitAnime) {
        this.activityExitAnime = activityExitAnime;
    }

    public boolean isFullScreenMode() {
        return isFullScreenMode;
    }

    public void setFullScreenMode(boolean fullScreenMode) {
        isFullScreenMode = fullScreenMode;
    }

    public int getCustomProgressViewLayoutID() {
        return customProgressViewLayoutID;
    }

    public void setCustomProgressViewLayoutID(@LayoutRes int customProgressViewLayoutID) {
        this.customProgressViewLayoutID = customProgressViewLayoutID;
    }

    public int getCustomImageViewLayoutID() {
        return customImageViewLayoutID;
    }

    public void setCustomImageViewLayoutID(int customImageViewLayoutID) {
        this.customImageViewLayoutID = customImageViewLayoutID;
    }

    public View getCustomShadeView() {
        return customShadeView;
    }

    public void setCustomShadeView(View customView) {
        this.customShadeView = customView;
    }

    public boolean isIndicatorHide() {
        return indicatorHide;
    }

    public void setIndicatorHide(boolean indicatorHide) {
        this.indicatorHide = indicatorHide;
    }

    public ScreenOrientationType getScreenOrientationType() {
        return screenOrientationType;
    }

    public void setScreenOrientationType(ScreenOrientationType screenOrientationType) {
        this.screenOrientationType = screenOrientationType;
    }

    public OnPageChangeListener getOnPageChangeListener() {
        return onPageChangeListener;
    }

    public void setOnPageChangeListener(OnPageChangeListener onPageChangeListener) {
        this.onPageChangeListener = onPageChangeListener;
    }

    public IndicatorType getIndicatorType() {
        return indicatorType;
    }

    public void setIndicatorType(IndicatorType indicatorType) {
        this.indicatorType = indicatorType;
    }

    public OnClickListener getOnClickListener() {
        return onClickListener;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public OnLongClickListener getOnLongClickListener() {
        return onLongClickListener;
    }

    public void setOnLongClickListener(OnLongClickListener onLongClickListener) {
        this.onLongClickListener = onLongClickListener;
    }

    public ArrayList<String> getImageList() {
        return imageList;
    }

    public void setImageList(ArrayList<String> imageList) {
        this.imageList = imageList;
    }

    public List<Bitmap> getBitmapList() {
        return bitmapList;
    }
    
    public void setBitmapList(List<Bitmap> bitmapList){
        this.bitmapList = bitmapList;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public ImageEngine getImageEngine() {
        return imageEngine;
    }

    public void setImageEngine(ImageEngine imageEngine) {
        this.imageEngine = imageEngine;
    }

    public TransformType getTransformType() {
        return transformType;
    }

    public void setTransformType(TransformType transformType) {
        this.transformType = transformType;
    }

    public OnActivityLifeListener getOnActivityLifeListener() {
        return onActivityLifeListener;
    }

    public void setOnActivityLifeListener(OnActivityLifeListener onActivityLifeListener) {
        this.onActivityLifeListener = onActivityLifeListener;
    }
}