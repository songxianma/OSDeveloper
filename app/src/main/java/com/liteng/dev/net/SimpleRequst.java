package com.liteng.dev.net;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by liteng on 16/7/11.
 */
public class SimpleRequst extends Request<JSONObject> {

    //超时时间
    private final int TIME_OUT_MS = 15000;
    //重试次数
    private final int RETRY_TIME = 0;

    private ResponseListener mResponseListener;

    private int mMethod;
    private Map<String, String> mParames;

    public SimpleRequst(int method, String url, Map<String, String> parames, final ResponseListener listener) {
        super(method, method == Method.POST ? url : HttpUtils.getURL(url, parames), new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onFailed(error);
            }
        });
        this.mMethod = method;
        this.mParames = parames;

        this.mResponseListener = listener;
        setRetryPolicy(new DefaultRetryPolicy(TIME_OUT_MS, RETRY_TIME, 1));
    }


    public SimpleRequst(String url, Map<String, String> parames, ResponseListener listener) {
        this(Method.GET, url, parames, listener);
    }


    @Override
    protected Response parseNetworkResponse(NetworkResponse response) {
        Response res ;
        String parsed;
        try {
            parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            res = Response.success(new JSONObject(parsed), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException e) {
            return Response.error(new ParseError(e));
        }

        return res;
    }

    @Override
    protected void deliverResponse(JSONObject response) {
        mResponseListener.onSuccess(response);
    }


    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        if (mMethod == Method.POST) {
            return this.mParames;
        } else {
            return null;
        }

    }

    public interface ResponseListener {
        void onSuccess(JSONObject jObj);

        void onFailed(VolleyError error);
    }


}
