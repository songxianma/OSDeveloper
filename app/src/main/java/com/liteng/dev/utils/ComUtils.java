package com.liteng.dev.utils;

import android.widget.Toast;

import com.liteng.dev.base.App;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by liteng on 16/7/13.
 */
public class ComUtils {

    public static void showToastLong(int msg){
        String msgStr = App.mContext.getString(msg);
        Toast.makeText(App.mContext, msgStr, Toast.LENGTH_LONG).show();
    }


    public static void showToastShort(int msg){
        String msgStr = App.mContext.getString(msg);
        Toast.makeText(App.mContext, msgStr, Toast.LENGTH_SHORT).show();
    }


    public static void showToastLong(String msg){
        Toast.makeText(App.mContext, msg, Toast.LENGTH_LONG).show();
    }


    public static void showToastShort(String msg){
        Toast.makeText(App.mContext, msg, Toast.LENGTH_SHORT).show();
    }



    /**
     * 得到网页中图片的地址
     */
    public static List<String> getImgStr(String htmlStr){
        String img="";
        Pattern p_image;
        Matcher m_image;
        List<String> pics = new ArrayList<>();

        String regEx_img = "<img.*src=(.*?)[^>]*?>"; //图片链接地址
        p_image = Pattern.compile(regEx_img,Pattern.CASE_INSENSITIVE);
        m_image = p_image.matcher(htmlStr);
        while(m_image.find()){
            img = img + "," + m_image.group();
            Matcher m  = Pattern.compile("src=\"?(.*?)(\"|>|\\s+)").matcher(img); //匹配src
            while(m.find()){
                pics.add(m.group(1));
            }
        }
        return pics;
    }
    //重点在于正则表达式 <img.*src=(.*?)[^>]*?>
    //               src=\"?(.*?)(\"|>|\\s+)



}
