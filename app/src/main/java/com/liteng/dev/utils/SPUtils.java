package com.liteng.dev.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.liteng.dev.base.App;

/**
 * Created by liteng on 16/7/11.
 */
public class SPUtils {

    private SharedPreferences sp;

    private SPUtils() {
        sp = App.mContext.getSharedPreferences("global.xml", Context.MODE_PRIVATE);
    }

    public static SPUtils getSp() {

        return SP.spUtils;
    }

    private static class SP {
        private static final SPUtils spUtils = new SPUtils();
    }


    public SPUtils save(String key,String value){
        sp.edit().putString(key,value).commit();
        return getSp();
    }

    public String get(String key){
        return sp.getString(key,null);
    }


    public SPUtils saveTrue(String key){
        sp.edit().putBoolean(key,true).commit();
        return  getSp();
    }

    public SPUtils saveFalse(String key){
        sp.edit().putBoolean(key,false).commit();
        return  getSp();
    }

    public boolean getBoolean(String key){
        return  sp.getBoolean(key,false);
    }



    public String getAccessToken(){
        return get("accessToken");
    }

}
