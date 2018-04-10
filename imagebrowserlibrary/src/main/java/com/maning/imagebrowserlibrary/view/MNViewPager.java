package com.maning.imagebrowserlibrary.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by maning on 2017/11/13.
 * 解决多点触摸和ViewPager焦点冲突
 * 报出下面的错误：java.lang.IllegalArgumentException: pointerIndex out of range
 */

public class MNViewPager extends ViewPager {

    public MNViewPager(Context context) {
        super(context);
    }

    public MNViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        try {
            return super.onTouchEvent(ev);
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        try {
            return super.onInterceptTouchEvent(ev);
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }
        return false;
    }

}
