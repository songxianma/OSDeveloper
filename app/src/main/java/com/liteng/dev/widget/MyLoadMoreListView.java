package com.liteng.dev.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.liteng.dev.R;

/**
 * Created by liteng on 16/7/17.
 */
public class MyLoadMoreListView extends ListView implements AbsListView.OnScrollListener{

    private View mFooterView;

    private int mLastVisibleIndex;
    private int mTotalItemCount;
    private boolean isLoading;

    private OnMyLoadMoreListenr mListenr;

    public void setOnMyLoadMoreListener(OnMyLoadMoreListenr listener){
        this.mListenr = listener;
    }

    public MyLoadMoreListView(Context context) {
        super(context);
        initFooterView(context);
    }

    public MyLoadMoreListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initFooterView(context);
    }

    private void initFooterView(Context context){
        mFooterView = LayoutInflater.from(context).inflate(R.layout.include_loading_footer,null);
        this.setOnScrollListener(this);
    }

    private void setFooterVisible(){
        this.addFooterView(mFooterView);
    }

    private void setFooterGone(){
        this.removeFooterView(mFooterView);
    }


    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if(scrollState == SCROLL_STATE_IDLE && mLastVisibleIndex == mTotalItemCount){
            if(!isLoading && mListenr != null){
                isLoading = true;
                setFooterVisible();
                mListenr.onLoadMore();
            }
        }
    }

    public void loadCompete(){
        isLoading = false;
        setFooterGone();
    }


    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        mLastVisibleIndex =  firstVisibleItem + visibleItemCount;
        mTotalItemCount = totalItemCount;
    }

    public boolean isLoading() {
        return isLoading;
    }

    public interface  OnMyLoadMoreListenr{
        void onLoadMore();
    }



}
