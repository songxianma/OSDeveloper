package com.liteng.dev.tweet;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.gxz.PagerSlidingTabStrip;
import com.liteng.dev.R;
import com.liteng.dev.base.BaseFragment;
import com.liteng.dev.tweet.entry.Tweet;


public class TweetFragment extends BaseFragment {


    private String[] mTiltes = {"最新动弹", "热门动弹", "我的动弹"};

    private PagerSlidingTabStrip mTabStrip;
    private ViewPager mVpTweetPagers;

    @Override
    protected void initViews(View rootView) {
        mTabStrip = (PagerSlidingTabStrip) rootView.findViewById(R.id.tabTweetStrip);
        mVpTweetPagers = (ViewPager) rootView.findViewById(R.id.vpTweet);
        TweetListAdapter adapter = new TweetListAdapter(getChildFragmentManager());
        mVpTweetPagers.setAdapter(adapter);
        mTabStrip.setViewPager(mVpTweetPagers);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_tweet;
    }

    private class TweetListAdapter extends FragmentPagerAdapter {

        public TweetListAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            int type = 0;
            switch (position) {
                case 0:
                    type = Tweet.TYPE_RENCENT;
                    break;
                case 1:
                    type = Tweet.TYPE_HOT;
                    break;
                case 2:
                    type = Tweet.TYPE_MINE;
                    break;
            }
            Fragment fragment = TweetListFragment.newInstance(type+"");
            return fragment;
        }

        @Override
        public int getCount() {
            return mTiltes.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTiltes[position];
        }
    }


}
