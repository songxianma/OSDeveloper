package com.liteng.dev.base;

import android.app.Application;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by liteng on 16/7/11.
 */
public class App extends Application {

    public static Context mContext;


    public static RequestQueue mQueue;

    @Override
    public void onCreate() {
        super.onCreate();

        if (mContext == null) {
            mContext = getApplicationContext();
        }

        if(mQueue == null){
            mQueue = Volley.newRequestQueue(mContext);
        }
    }
}
