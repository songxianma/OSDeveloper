package com.liteng.dev.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by liteng on 16/7/12.
 */
public abstract class BaseFragment extends Fragment {

    protected View rootView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getRootView(inflater, container, savedInstanceState);
        return rootView;
    }


    protected void getRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int viewRes = getLayoutRes();
        rootView = inflater.inflate(viewRes, container, false);
        initViews(rootView);
    }

    protected abstract void initViews(View rootView);

    public abstract int getLayoutRes();


    protected ProgressDialog mProgressDialog;

    protected void showLoading(String msg) {
        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        if (!TextUtils.isEmpty(msg)) {
            mProgressDialog.setTitle(msg);
        }
        mProgressDialog.show();
    }

    protected void dismissLoading() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }


}
