package com.troila.router_api;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import com.troila.function.ClassUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Router {
    private static Router mInstance;
    private static Context mContext;
    private static Map<String, Class<? extends Activity>> routes = new HashMap<>();

    public static Router getInstance() {
        if (mInstance == null) {
            synchronized (Router.class) {
                if (mInstance == null) {
                    mInstance = new Router();
                }
            }
        }
        return mInstance;
    }

    public void regist(String path, Class<? extends Activity> cls) {
        routes.put(path, cls);
    }

    public static void init(Application application) {
        mContext = application;
        try {
            Set<String>  classNames = ClassUtils.getFileNameByPackageName(application, "com.troila.routers");
            for (String className : classNames) {
                Class<?> aClass = Class.forName(className);
                if (IRouteLoad.class.isAssignableFrom(aClass)) {
                    IRouteLoad load = (IRouteLoad)aClass.newInstance();
                    load.onLoad(routes);
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void startActivity(String path) {
        Class<? extends Activity> cls = routes.get(path);
        if (cls == null) {
            return;
        }
        Intent intent = new Intent(mContext, cls);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);

    }
}
