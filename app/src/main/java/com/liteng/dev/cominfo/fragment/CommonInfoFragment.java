package com.liteng.dev.cominfo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.gxz.PagerSlidingTabStrip;
import com.liteng.dev.R;
import com.liteng.dev.base.BaseFragment;


/**
 * 综合
 */
public class CommonInfoFragment extends BaseFragment {

    private final String[] mTitles ={"资讯","博客","问答","活动"};

    private PagerSlidingTabStrip mTabStrip;
    private ViewPager mVpCommonInfos;
    private MyViewPagerAdapter mAdapter;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    protected void initViews(View rootView) {
        mTabStrip = (PagerSlidingTabStrip) rootView.findViewById(R.id.tabStrip);
        mVpCommonInfos = (ViewPager) rootView.findViewById(R.id.vpCommonInfos);
        mAdapter =  new MyViewPagerAdapter(getChildFragmentManager());
        mVpCommonInfos.setAdapter(mAdapter);
        mTabStrip.setViewPager(mVpCommonInfos);

    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_common_info;
    }

    private class MyViewPagerAdapter extends FragmentPagerAdapter{

        public MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position){
                case 0:
                    fragment = InfosFragment.newInstance();
                    break;
                case 1:
                    fragment = BlogFragment.newInstance();
                    break;
                case 2:
                    fragment = QAFragment.newInstance();
                    break;
                case 3:
                    fragment = ActivityFragment.newInstance();
                    break;
            }
            return fragment;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public int getCount() {
            return mTitles.length;
        }

    }




}
