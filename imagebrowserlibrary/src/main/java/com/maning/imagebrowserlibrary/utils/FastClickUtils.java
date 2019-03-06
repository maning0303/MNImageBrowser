package com.maning.imagebrowserlibrary.utils;


public class FastClickUtils {
    private static long lastClickTime = 0;//上次点击的时间
    private static int spaceTime = 500;//时间间隔

    public static boolean isFastClick() {
        long currentTime = System.currentTimeMillis();
        boolean isFastClick;//是否快速点击
        if (currentTime - lastClickTime > spaceTime) {
            isFastClick = false;
        } else {
            isFastClick = true;
        }
        lastClickTime = currentTime;
        return isFastClick;
    }
}
