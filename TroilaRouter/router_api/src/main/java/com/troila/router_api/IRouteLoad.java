package com.troila.router_api;

import android.app.Activity;

import java.util.Map;

public interface IRouteLoad {
    void onLoad(Map<String, Class<? extends Activity>> routes);
}
