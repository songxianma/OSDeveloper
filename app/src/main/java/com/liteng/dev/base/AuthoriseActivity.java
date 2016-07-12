package com.liteng.dev.base;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.android.volley.VolleyError;
import com.liteng.dev.R;
import com.liteng.dev.home.MainActivity;
import com.liteng.dev.net.SimpleRequst;
import com.liteng.dev.net.URLs;
import com.liteng.dev.utils.SPUtils;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AuthoriseActivity extends BaseActivity {

    public static final String TAG = AuthoriseActivity.class.getSimpleName();

    private WebView mWvAthorize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorise);
        mWvAthorize = (WebView) findViewById(R.id.wvAuthorise);
        mWvAthorize.getSettings().setJavaScriptEnabled(true );

        mWvAthorize.loadUrl(URLs.getAuthoriseCodeUrl());

        mWvAthorize.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.e("url",url);
                if(url.contains("code=")){
                    String code = getCode(url);
                    SPUtils.getSp().save("code",code);//保存authorise code
                    getAuthoriseToken(code);
                }
                return true;
            }
        });

    }

    /**
     * {
     "access_token": "8447ff97-9b8c-4224-9cec-63b97d34ba65",
     "refresh_token": "8447ff97-9b8c-4224-9cec",
     "token_type": "bearer",
     "expires_in": 43199,
     "uid": 12
     }
     * @param code
     */

    private void getAuthoriseToken(String code) {
        Map<String,String> parames = new HashMap<>();
        parames.put("client_id","TaWYletXYtnXuuuc3phd");
        parames.put("redirect_uri","http://litengit.com");
        parames.put("client_secret","Ziolifwdx7GNVEAAnptLE7TjHG8nDLzC");
        parames.put("grant_type","authorization_code");
        parames.put("dataType","json");
        parames.put("code",code);

        SimpleRequst requst = new SimpleRequst(URLs.AUTHORISE_TOKEN_URL, parames, new SimpleRequst.ResponseListener() {
            @Override
            public void onSuccess(JSONObject jObj) {
                String accessToken = jObj.optString("access_token");
                String uid = jObj.optString("uid");
                String refreshToken =jObj.optString("refresh_token");
                String expiresIn = jObj.optString("expires_in");//超时时间

                SPUtils.getSp().save("accessToken",accessToken)
                .save("uid",uid)
                .save("refresh_token",refreshToken)
                .save("expires_in",expiresIn);

                startActivity(new Intent(AuthoriseActivity.this,MainActivity.class));
                finish();
            }

            @Override
            public void onFailed(VolleyError error) {

            }
        });
        requst.setTag(TAG);
        App.mQueue.add(requst);
    }

    private String getCode(String url) {
        String[] strings = url.split("code=");
        String code = strings[1].split("&")[0];
        return code;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.mQueue.cancelAll(TAG);
    }
}
