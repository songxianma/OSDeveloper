package com.liteng.dev.cominfo;

import android.view.View;

import com.liteng.dev.R;
import com.liteng.dev.base.BaseFragment;

/**
 * 问答
 */
public class QAFragment extends BaseFragment {

    public QAFragment() {
        // Required empty public constructor
    }


    public static QAFragment newInstance() {
        QAFragment fragment = new QAFragment();
        return fragment;
    }


    @Override
    protected void initViews(View rootView) {

    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_qa;
    }
}
