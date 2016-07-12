package com.liteng.dev.cominfo;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.android.volley.VolleyError;
import com.liteng.dev.R;
import com.liteng.dev.base.App;
import com.liteng.dev.base.BaseFragment;
import com.liteng.dev.net.SimpleRequst;
import com.liteng.dev.net.URLs;
import com.liteng.dev.utils.SPUtils;

import org.json.JSONObject;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * 资讯
 */
public class InfosFragment extends BaseFragment {
    private RecyclerView mRvNewList;

    public InfosFragment() {
    }

    public static InfosFragment newInstance() {
        InfosFragment fragment = new InfosFragment();
        return fragment;
    }


    @Override
    protected void initViews(View rootView) {
        mRvNewList = (RecyclerView) rootView.findViewById(R.id.rvNewsList);

        getNewsList();
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_infos;
    }


    /**
     * 必选	类型及范围	说明	默认值
     access_token	true	string	oauth2_token获取的access_token
     catalog	true	int	1-所有|2-综合新闻|3-软件更新	1
     page/pageIndex	true	int	页数	1
     pageSize	true	int	每页条数	20
     dataType	true	string	返回数据类型['json'|'jsonp'|'xml']	json
     */
    private void getNewsList(){
        Map<String,String> parames = new HashMap<>();
        parames.put("access_token", SPUtils.getSp().getAccessToken());
        parames.put("catalog","1");
        parames.put("page","1");
        parames.put("pageSize","20");
        parames.put("dataType","json");
        SimpleRequst requst = new SimpleRequst(URLs.NEWS_LIST_URL, parames, new SimpleRequst.ResponseListener() {
            @Override
            public void onSuccess(JSONObject jObj) {
                Log.e("----",""+jObj);
            }

            @Override
            public void onFailed(VolleyError error) {

            }
        });
        App.mQueue.add(requst);
    }



}
