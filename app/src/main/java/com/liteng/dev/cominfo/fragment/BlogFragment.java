package com.liteng.dev.cominfo.fragment;

import android.view.View;

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
