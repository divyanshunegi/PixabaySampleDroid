package com.divyanshu.pixabay;

import android.app.Application;

import timber.log.Timber;

/**
 * Created by divyanshunegi on 4/6/17.
 * Project : PixabaySampleApp
 */
public class PixabayApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

}
