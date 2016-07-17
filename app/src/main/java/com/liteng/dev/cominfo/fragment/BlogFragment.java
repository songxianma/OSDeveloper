package com.liteng.dev.cominfo.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.liteng.dev.R;
import com.liteng.dev.base.App;
import com.liteng.dev.base.BaseFragment;
import com.liteng.dev.cominfo.adapter.BlogAdatper;
import com.liteng.dev.cominfo.entry.Blog;
import com.liteng.dev.net.HttpUtils;
import com.liteng.dev.net.SimpleRequst;
import com.liteng.dev.net.URLs;
import com.liteng.dev.utils.LogUtils;
import com.liteng.dev.widget.MyLoadMoreListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 博客
 */
public class BlogFragment extends BaseFragment{

  private final String TAG_RQUEST_BLOG_LIST = "TAG_RQUEST_BLOG_LIST";




    public BlogFragment() {
    }
    public static BlogFragment newInstance() {
        BlogFragment fragment = new BlogFragment();
        return fragment;
    }


    private int mPage;
    private MyLoadMoreListView mLvBlog;

    private List<Blog> mBlogs;
    private BlogAdatper mBlogAdatper;

    private SwipeRefreshLayout mRefreshBlogList;


    @Override
    protected void initViews(View rootView) {
        mRefreshBlogList = (SwipeRefreshLayout) rootView.findViewById(R.id.refreshBlogList);
        mLvBlog = (MyLoadMoreListView) rootView.findViewById(R.id.lvBlogList);
        mBlogs = new ArrayList<>();
        mBlogAdatper = new BlogAdatper(getActivity(),mBlogs);
        mLvBlog.setAdapter(mBlogAdatper);

        showLoading();
        getBlogList();

        mRefreshBlogList.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPage =0;
                mBlogs.clear();
                getBlogList();
            }
        });

        mLvBlog.setOnMyLoadMoreListener(new MyLoadMoreListView.OnMyLoadMoreListenr() {
            @Override
            public void onLoadMore() {
                getBlogList();
            }
        });

    }


    @Override
    public int getLayoutRes() {
        return R.layout.fragment_blog;
    }


    private void getBlogList(){

        mPage++;
        Map<String,String>  parames = HttpUtils.commonParames();
        parames.put("page",""+mPage);
        parames.put("pageSize","20");
        SimpleRequst requst = new SimpleRequst(URLs.BLOG_LIST_URL, parames, new SimpleRequst.ResponseListener() {
            @Override
            public void onSuccess(JSONObject jObj) {
                List<Blog> blogs = new ArrayList<>();
                JSONArray blogArr = jObj.optJSONArray("bloglist");
                for (int i = 0; i < blogArr.length(); i++) {
                    JSONObject blogObj = blogArr.optJSONObject(i);
                    Blog blog = new Blog(blogObj);
                    blogs.add(blog);
                }

                mBlogs.addAll(blogs);
                mBlogAdatper.notifyDataSetChanged();

                dismissLoading();

                if(mRefreshBlogList.isRefreshing()){
                    mRefreshBlogList.setRefreshing(false);
                }

                if(mLvBlog.isLoading()) {
                    mLvBlog.loadCompete();
                }

            }

            @Override
            public void onFailed(VolleyError error) {

            }
        });
        requst.setTag(TAG_RQUEST_BLOG_LIST);
        App.mQueue.add(requst);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        App.mQueue.cancelAll(TAG_RQUEST_BLOG_LIST);
    }
}
