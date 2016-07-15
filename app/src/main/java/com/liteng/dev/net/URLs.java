package com.liteng.dev.net;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liteng on 16/7/10.
 */
public class URLs {

    public static final String BASE_ROOT_URL = "http://www.oschina.net/";

    public static final String AUTHORISE_CODE_URL = BASE_ROOT_URL +"action/oauth2/authorize";

    public static final String AUTHORISE_TOKEN_URL = BASE_ROOT_URL +"action/openapi/token";

    public static final String NEWS_LIST_URL = BASE_ROOT_URL +"action/openapi/news_list";

    public static final String NEWS_DETAIL_URL = BASE_ROOT_URL +"action/openapi/news_detail";

    public static final String TWEET_LIST_URL = BASE_ROOT_URL +"action/openapi/tweet_list";




    public static String getAuthoriseCodeUrl() {
        Map<String,String> map = new HashMap<>();
        map.put("response_type","code");
        map.put("client_id","TaWYletXYtnXuuuc3phd");
        map.put("redirect_uri","http://litengit.com");

        return HttpUtils.getURL(AUTHORISE_CODE_URL,map);
    }

}
