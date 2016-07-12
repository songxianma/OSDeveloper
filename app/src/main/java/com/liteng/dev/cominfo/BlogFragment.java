package com.liteng.dev.cominfo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liteng.dev.R;
import com.liteng.dev.base.BaseFragment;

/**
 * 博客
 */
public class BlogFragment extends BaseFragment{

    public BlogFragment() {
    }
    public static BlogFragment newInstance() {
        BlogFragment fragment = new BlogFragment();
        return fragment;
    }


    @Override
    protected void initViews(View rootView) {

    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_blog;
    }
}
