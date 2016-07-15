package com.liteng.dev.home;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.liteng.dev.R;
import com.liteng.dev.base.BaseActivity;
import com.liteng.dev.cominfo.fragment.CommonInfoFragment;
import com.liteng.dev.explore.ExploreFragment;
import com.liteng.dev.me.MeFragment;
import com.liteng.dev.tweet.TweetFragment;
import com.liteng.dev.utils.ComUtils;
import com.liteng.dev.widget.MyTabHost;


public class MainActivity extends BaseActivity implements View.OnClickListener {

    private String[] mTabsName = {"综合", "动弹", "", "发现", "我的"};
    private int[] mImageRes = {R.drawable.selector_nav_cominfo,R.drawable.selector_nav_tweet,R.drawable.selector_nav_add,R.drawable.selector_nav_explore,R.drawable.selector_nav_me};
    private Class<?>[] mFragmets = {CommonInfoFragment.class, TweetFragment.class, TweetFragment.class, ExploreFragment.class, MeFragment.class};
    private MyTabHost mTabHost;

    private LayoutInflater mInflater;

    private Toolbar mToolbar;

    private ImageView mIvQuickBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mInflater = getLayoutInflater();
        mTabHost = (MyTabHost) findViewById(R.id.tabHost);
        mIvQuickBtn = (ImageView) this.findViewById(R.id.ivQuickBtn);
        mIvQuickBtn.setOnClickListener(this);

        initToolbar();
        initTabHost();

    }

    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolBar);
        mToolbar.setTitleTextColor(ContextCompat.getColor(this,R.color.color_fafafa));
        mToolbar.setTitle(mTabsName[0]);//默认显示第一个tab的标题
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayShowTitleEnabled(true);
        }

        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_search:
                        ComUtils.showToastLong("shousuo");
                        break;
                }
                return false;
            }
        });

    }

    private void initTabHost() {
        mTabHost.setup(this, getSupportFragmentManager(), R.id.fragmentContainer);
        mTabHost.getTabWidget().setDividerDrawable(null);
        for (int i = 0; i < mTabsName.length; i++) {
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(mTabsName[i]).setIndicator(getIndicatorView(mTabsName[i],mImageRes[i]));
            if (i == 2) {
                mTabHost.setNoChangeTag(mTabsName[2]);
            }
            mTabHost.addTab(tabSpec, mFragmets[i], null);
        }

        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if(!mTabHost.getNoChangeTabId().equalsIgnoreCase(tabId)){
                 mToolbar.setTitle(tabId);
                }
            }
        });
    }


    private View getIndicatorView(String name,int imgId) {
        View view = mInflater.inflate(R.layout.tab_indicator, null);
        TextView tvName = (TextView) view.findViewById(R.id.tvTabName);
        ImageView ivImage = (ImageView) view.findViewById(R.id.ivTabImage);
        tvName.setText(name);
        ivImage.setImageResource(imgId);
        return view;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main,menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ivQuickBtn:
                Toast.makeText(MainActivity.this, "--------", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
