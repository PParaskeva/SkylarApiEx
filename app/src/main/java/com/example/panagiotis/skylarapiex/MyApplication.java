package com.example.panagiotis.skylarapiex;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by Panagiotis on 23/01/2018.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}