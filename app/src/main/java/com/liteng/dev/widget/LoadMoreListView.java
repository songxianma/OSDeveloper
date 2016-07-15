package com.liteng.dev.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.liteng.dev.R;

/**
 * Created by liteng on 16/7/15.
 */
public class LoadMoreListView extends ListView implements AbsListView.OnScrollListener {

    private View mFooterview;

    private int mLastVisibleItem;
    private int mTotalItemCount;
    private boolean isLoading;

    private OnLoadMoreListener mListener;

    public void setOnLoadMoreListener(OnLoadMoreListener listener) {
        this.mListener = listener;
    }

    public LoadMoreListView(Context context) {
        super(context);
        initFooterView(context);
    }

    public LoadMoreListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initFooterView(context);
    }


    private void initFooterView(Context context) {
        mFooterview = LayoutInflater.from(context).inflate(R.layout.include_loading_footer, null);
        this.setOnScrollListener(this);
    }


    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        this.mLastVisibleItem = firstVisibleItem + visibleItemCount;
        this.mTotalItemCount = totalItemCount;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (this.mTotalItemCount == mLastVisibleItem && scrollState == SCROLL_STATE_IDLE) {
            if (!isLoading && this.mListener != null) {

                isLoading = true;
                setLoadingVisible();

                mListener.onLoadMore();
            }
        }
    }

    public void loadComplete() {
        isLoading = false;
        setLoadingGone();
    }

    public boolean isLoading() {
        return isLoading;
    }

    private void setLoadingGone() {
        this.removeFooterView(mFooterview);
    }

    private void setLoadingVisible() {
        this.addFooterView(mFooterview);
    }


    public interface OnLoadMoreListener {
        void onLoadMore();
    }


}
