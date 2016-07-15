package com.liteng.dev.cominfo.activities;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;

import com.android.volley.VolleyError;
import com.liteng.dev.R;
import com.liteng.dev.base.App;
import com.liteng.dev.base.BaseActivity;
import com.liteng.dev.cominfo.entry.NewsDetail;
import com.liteng.dev.cominfo.fragment.InfosFragment;
import com.liteng.dev.net.HttpUtils;
import com.liteng.dev.net.SimpleRequst;
import com.liteng.dev.net.URLs;
import com.liteng.dev.utils.TimeUtils;

import org.json.JSONObject;

import java.util.Map;

public class NewsDetailsActivity extends BaseActivity {

    private String mNewsId;

    private Toolbar mToolbar;
    private WebView mWvNewsDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        mToolbar = (Toolbar) findViewById(R.id.toolBar);
        mToolbar.setTitleTextColor(ContextCompat.getColor(this,R.color.color_fafafa));
        mToolbar.setTitle("资讯详情");
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        mWvNewsDetails = (WebView) findViewById(R.id.wvNewsDetails);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        initIntent();

        getNewsDetails();
    }

    private void getNewsDetails() {
        Map<String,String> parames = HttpUtils.commonParames();
        parames.put("id",mNewsId);
        SimpleRequst requst = new SimpleRequst(URLs.NEWS_DETAIL_URL, parames, new SimpleRequst.ResponseListener() {
            @Override
            public void onSuccess(JSONObject jObj) {
                Log.e("-=-=",jObj.toString());
                if(jObj!=null){
                    NewsDetail detail = new NewsDetail(jObj);
                    String webViewBody = getWebViewBody(detail);
                    mWvNewsDetails.loadDataWithBaseURL("",webViewBody,"text/html","utf-8","");
                }
            }

            @Override
            public void onFailed(VolleyError error) {

            }
        });
        App.mQueue.add(requst);

    }

    private void initIntent() {
        Intent intent = getIntent();
        mNewsId = intent.getStringExtra(InfosFragment.KEY_NEWS_ID);
    }


    protected String getWebViewBody(NewsDetail detail) {
        StringBuffer body = new StringBuffer();
        body.append(HttpUtils.WEB_STYLE).append(HttpUtils.WEB_LOAD_IMAGES);
        body.append(HttpUtils.getWebViewBodyString());
        // 添加title
        body.append(String.format("<div class='title'>%s</div>", detail.getTitle()));
        // 添加作者和时间
        String time = TimeUtils.friendly_time(detail.getPubDate());
        String author = String.format("<a class='author' href='http://my.oschina.net/u/%s'>%s</a>", detail.getAuthorid(), detail.getAuthor());
        body.append(String.format("<div class='authortime'>%s&nbsp;&nbsp;&nbsp;&nbsp;%s</div>", author, time));
        // 添加图片点击放大支持
        body.append(HttpUtils.setHtmlCotentSupportImagePreview(detail.getBody()));



        // 相关新闻
        if (detail != null && detail.getRelativies() != null
                && detail.getRelativies().size() > 0) {
            String strRelative = "";
            for (NewsDetail.Relativie relative : detail.getRelativies()) {
                strRelative += String.format(
                        "<li><a href='%s' style='text-decoration:none'>%s</a></li>",
                        relative.getUrl(), relative.getTitle());
            }
            body.append("<p/><div style=\"height:1px;width:100%;background:#DADADA;margin-bottom:10px;\"/>"
                    + String.format("<br/> <b>相关资讯</b><ul class='about'>%s</ul>",
                    strRelative));
        }
        body.append("<br/>");
        // 封尾
        body.append("</div></body>");
        return  body.toString();
    }



}
