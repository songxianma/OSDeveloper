package com.liteng.dev.widget;

import android.content.Context;
import android.support.v4.app.FragmentTabHost;
import android.util.AttributeSet;

/**
 * Created by liteng on 16/7/12.
 */
public class MyTabHost extends FragmentTabHost {

    private String mNoChangeTabId;

    public MyTabHost(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTabHost(Context context) {
        super(context);
    }


    @Override
    public void onTabChanged(String tabId) {
        if (tabId.equalsIgnoreCase(mNoChangeTabId)){
            setNoChangeTag(tabId);
        }else {
            super.onTabChanged(tabId);
        }
    }

    public void setNoChangeTag(String tabId){
        mNoChangeTabId = tabId;
    }

    public String getNoChangeTabId() {
        return mNoChangeTabId;
    }
}
