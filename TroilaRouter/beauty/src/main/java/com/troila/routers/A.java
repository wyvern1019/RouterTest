package com.troila.routers;

import android.app.Activity;

import com.troila.beauty.MainActivity;
import com.troila.router_api.IRouteLoad;

import java.util.Map;

public class A implements IRouteLoad {
    @Override
    public void onLoad(Map<String, Class<? extends Activity>> routes) {
        routes.put("/beauty/main", MainActivity.class);


    }
}
