package com.liteng.dev.tweet;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.liteng.dev.R;
import com.liteng.dev.base.App;
import com.liteng.dev.base.BaseFragment;
import com.liteng.dev.db.DBHelper;
import com.liteng.dev.db.DataCache;
import com.liteng.dev.net.HttpUtils;
import com.liteng.dev.net.SimpleRequst;
import com.liteng.dev.net.URLs;
import com.liteng.dev.tweet.adapter.TweetListAdapter;
import com.liteng.dev.tweet.entry.Tweet;
import com.liteng.dev.utils.ComUtils;
import com.liteng.dev.utils.SPUtils;
import com.liteng.dev.widget.LoadMoreListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class TweetListFragment extends BaseFragment {
    private static  String mCacheKey = "TweetList";

    private static final String ARG_TYPE = "ARG_TYPE";

    private String mType;
    private int pageNo;
    private LoadMoreListView mLvTweetList;
    private List<Tweet> mTweets = null;
    private TweetListAdapter mTweetListAdapter;

    public TweetListFragment() {
    }

    public static TweetListFragment newInstance(String arg) {
        TweetListFragment fragment = new TweetListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TYPE, arg);
        fragment.setArguments(args);

        mCacheKey = SPUtils.getSp().getAccessToken() + arg;

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mType = getArguments().getString(ARG_TYPE);
        }
    }


    @Override
    protected void initViews(View rootView) {
        mLvTweetList = (LoadMoreListView) rootView.findViewById(R.id.lvTweetList);
        mTweets = new ArrayList<>();
        mTweetListAdapter = new TweetListAdapter(mTweets,getActivity());
        mLvTweetList.setAdapter(mTweetListAdapter);


        showLoading();
        getWTweetList();

        mLvTweetList.setOnLoadMoreListener(new LoadMoreListView.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                getWTweetList();
            }
        });

    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_tweet_list;
    }


    private void getWTweetList(){

        /**
         *
         必选	类型及范围	说明	默认值
         user	false	long	用户ID [ 0：最新动弹，-1：热门动弹，其它：我的动弹 ]	0
         pageSize	true	int	每页条数	20
         page/pageIndex	true	int	页数	1
         */
        pageNo ++;
        Map<String,String> parames = HttpUtils.commonParames();
        parames.put("user",mType);
        parames.put("pageSize","20");
        parames.put("page",pageNo+"");

        SimpleRequst requst  = new SimpleRequst(URLs.TWEET_LIST_URL, parames, new SimpleRequst.ResponseListener() {
            @Override
            public void onSuccess(JSONObject jObj) {
                dismissLoading();
                saveDataCache(jObj);
                bindViews(jObj);
            }

            @Override
            public void onFailed(VolleyError error) {
                String cacheString = DBHelper.getDBHelper().getCache(mCacheKey).toString();
                try {
                    bindViews(new JSONObject(cacheString));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                ComUtils.showToastLong(R.string.net_error_response);
                dismissLoading();
            }
        });
        App.mQueue.add(requst);
    }

    private void bindViews(JSONObject jObj) {
        if (jObj == null) {
            ComUtils.showToastLong(R.string.net_error_response);
            String cacheString = DBHelper.getDBHelper().getCache(mCacheKey).toString();
            try {
                bindViews(new JSONObject(cacheString));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return;
        }
        List<Tweet> tweets= new ArrayList<>();

        JSONArray tweetArr = jObj.optJSONArray("tweetlist");
        for (int i = 0; i < tweetArr.length(); i++) {
            JSONObject tweetObj = tweetArr.optJSONObject(i);
            Tweet tweet = new Tweet(tweetObj);
            tweets.add(tweet);
        }
        mTweets.addAll(tweets);
        mTweetListAdapter.notifyDataSetChanged();

        if(mLvTweetList.isLoading()){
            mLvTweetList.loadComplete();
        }
    }

    private void saveDataCache(JSONObject jObj) {
        DataCache cache = new DataCache();
        cache.setKey(mCacheKey);
        cache.setContent(jObj.toString());
        DBHelper.getDBHelper().saveCache(cache);
    }

}
