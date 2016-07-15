package com.liteng.dev.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.liteng.dev.R;

/**
 * Created by liteng on 16/7/12.
 */
public abstract class BaseFragment extends Fragment {

    protected View rootView;

    private RelativeLayout mLoadingLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getRootView(inflater, container);
        return rootView;
    }


    protected void getRootView(LayoutInflater inflater, ViewGroup container) {
        int viewRes = getLayoutRes();
        rootView = inflater.inflate(viewRes, container, false);
        initViews(rootView);
        initLoading();
    }

    protected void initLoading(){
        mLoadingLayout = (RelativeLayout) rootView.findViewById(R.id.loadingLayout);
    }

    protected abstract void initViews(View rootView);

    public abstract int getLayoutRes();


    protected void showLoading() {
        if(null != mLoadingLayout){
            mLoadingLayout.setVisibility(View.VISIBLE);
        }
    }

    protected void dismissLoading() {
        if(null != mLoadingLayout){
            mLoadingLayout.setVisibility(View.GONE);
        }
    }


}
