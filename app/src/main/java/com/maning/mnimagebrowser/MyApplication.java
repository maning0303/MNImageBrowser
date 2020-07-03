package com.maning.mnimagebrowser;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * @author : maning
 * @date : 2020-07-02
 * @desc :
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }

}
