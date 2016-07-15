package com.liteng.dev.cominfo.fragment;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.android.volley.VolleyError;
import com.liteng.dev.R;
import com.liteng.dev.base.App;
import com.liteng.dev.base.BaseFragment;
import com.liteng.dev.cominfo.activities.NewsDetailsActivity;
import com.liteng.dev.cominfo.adapter.NewsListAdapter;
import com.liteng.dev.cominfo.entry.NewsDetail;
import com.liteng.dev.cominfo.entry.NewsList;
import com.liteng.dev.cundong.recyclerview.EndlessRecyclerOnScrollListener;
import com.liteng.dev.cundong.recyclerview.HeaderAndFooterRecyclerViewAdapter;
import com.liteng.dev.cundong.recyclerview.RecyclerViewStateUtils;
import com.liteng.dev.cundong.recyclerview.RecyclerViewUtils;
import com.liteng.dev.cundong.recyclerview.weight.LoadingFooter;
import com.liteng.dev.net.HttpUtils;
import com.liteng.dev.net.SimpleRequst;
import com.liteng.dev.net.URLs;
import com.liteng.dev.utils.SPUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 资讯
 */
public class InfosFragment extends BaseFragment {

    public static final  String KEY_NEWS_ID = "keys_news_id";

    public static final int TOTAL_COUNT = 10000;

    private RecyclerView mRvNewList;
    private List<NewsList.News> mNewsList = new ArrayList<>();
    private NewsListAdapter mNewsListAdapter;
    private HeaderAndFooterRecyclerViewAdapter mHeaderAndFooterRecyclerViewAdapter;

    private SwipeRefreshLayout mRefreshLayout;

    private final  int  PAGE_NO = 20;
    private int page;

    public InfosFragment() {
    }

    public static InfosFragment newInstance() {
        InfosFragment fragment = new InfosFragment();
        return fragment;
    }


    @Override
    protected void initViews(View rootView) {
        mRvNewList = (RecyclerView) rootView.findViewById(R.id.rvNewsList);
        mRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.refreshNewsLayout);

        mRvNewList.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));

        mNewsListAdapter  =new NewsListAdapter(getActivity(),mNewsList);
        mHeaderAndFooterRecyclerViewAdapter = new HeaderAndFooterRecyclerViewAdapter(mNewsListAdapter);
        View view  = getActivity().getLayoutInflater().inflate(R.layout.include_loading_footer,null);
        RecyclerViewUtils.setFooterView(mRvNewList,view);

        mRvNewList.setAdapter(mHeaderAndFooterRecyclerViewAdapter);
        showLoading();
        getNewsList();

        mRvNewList.addOnScrollListener(mOnScreenListener);


        mNewsListAdapter.setOnItemClickListener(new NewsListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                Intent intent = new Intent(getActivity(), NewsDetailsActivity.class);
                intent.putExtra(KEY_NEWS_ID,mNewsList.get(position).getId());
                startActivity(intent);
            }
        });

        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 0;
                mNewsList.clear();
                getNewsList();
            }
        });

    }

    private int mCurrentCount = 0;
    private EndlessRecyclerOnScrollListener mOnScreenListener = new EndlessRecyclerOnScrollListener(){
        @Override
        public void onLoadNextPage(View view) {
            super.onLoadNextPage(view);

            LoadingFooter.State state = RecyclerViewStateUtils.getFooterViewState(mRvNewList);
            mCurrentCount = mNewsList.size();
            if(mCurrentCount<TOTAL_COUNT){
                RecyclerViewStateUtils.setFooterViewState(getActivity(),mRvNewList,page, LoadingFooter.State.Loading,null);
                getNewsList();
            }else{
                RecyclerViewStateUtils.setFooterViewState(getActivity(),mRvNewList,page, LoadingFooter.State.TheEnd,null);
            }

        }
    };



    @Override
    public int getLayoutRes() {
        return R.layout.fragment_infos;
    }


    /**
     * 必选	类型及范围	说明	默认值
     * access_token	true	string	oauth2_token获取的access_token
     * catalog	true	int	1-所有|2-综合新闻|3-软件更新	1
     * page/pageIndex	true	int	页数	1
     * pageSize	true	int	每页条数	20
     * dataType	true	string	返回数据类型['json'|'jsonp'|'xml']	json
     */
    private void getNewsList() {
        page++;

        Map<String, String> parames = HttpUtils.commonParames();
        parames.put("catalog", "1");
        parames.put("page", page+"");
        parames.put("pageSize", PAGE_NO+"");
        SimpleRequst requst = new SimpleRequst(URLs.NEWS_LIST_URL, parames, new SimpleRequst.ResponseListener() {
            @Override
            public void onSuccess(JSONObject jObj) {
                if (jObj != null) {
                    mNewsList.addAll(new NewsList(jObj).getNewslist());
                    mNewsListAdapter.notifyDataSetChanged();
                    dismissLoading();
                    mRefreshLayout.setRefreshing(false);
                }
            }

            @Override
            public void onFailed(VolleyError error) {
                dismissLoading();
            }
        });
        App.mQueue.add(requst);
    }

}
