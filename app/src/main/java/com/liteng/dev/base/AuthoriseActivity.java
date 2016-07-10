package com.liteng.dev.base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.liteng.dev.R;
import com.liteng.dev.net.URLs;

public class AuthoriseActivity extends AppCompatActivity {

    private WebView mWvAthorize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorise);
        mWvAthorize = (WebView) findViewById(R.id.wvAuthorise);

        mWvAthorize.loadUrl(URLs.AUTHORISE_URL);

        mWvAthorize.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if(url.contains("http://litengit.com")){
                    String code = getCode();
                }
                return super.shouldOverrideUrlLoading(view, url);
            }
        });

    }

    private String getCode() {

        return null;
    }
}
