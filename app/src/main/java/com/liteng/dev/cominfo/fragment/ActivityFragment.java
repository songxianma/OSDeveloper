package com.liteng.dev.cominfo.fragment;

import android.view.View;

import com.liteng.dev.R;
import com.liteng.dev.base.BaseFragment;

/**
 * 活动
 */
public class ActivityFragment extends BaseFragment {

    public ActivityFragment(){}


    public static ActivityFragment newInstance() {
        ActivityFragment fragment = new ActivityFragment();
        return fragment;
    }

    @Override
    protected void initViews(View rootView) {

    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_activity;
    }
}
