package com.maning.mnimagebrowser.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * @author : maning
 * @date : 2020/11/23
 * @desc :
 */
public class SuperGridView extends GridView {

    public SuperGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SuperGridView(Context context) {
        super(context);
    }

    public SuperGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
} 