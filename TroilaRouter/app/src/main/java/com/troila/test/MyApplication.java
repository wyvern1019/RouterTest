package com.troila.test;

import android.app.Application;

import com.troila.router_api.Router;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Router.getInstance().init(this);
    }
}
