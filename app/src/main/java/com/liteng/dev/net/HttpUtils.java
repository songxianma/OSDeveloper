package com.liteng.dev.net;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by liteng on 16/7/11.
 */
public class HttpUtils {

    public static final String ENCODE_CHARSET = "UTF-8";

    public static final String getURL(String rootURL, Map<String, String> parames) {

        StringBuilder builder = new StringBuilder();
        builder.append(rootURL).append("?");

        try {
            for (Map.Entry<String, String> entry : parames.entrySet()) {
                builder.append(URLEncoder.encode(entry.getKey(), ENCODE_CHARSET));
                builder.append("=");
                builder.append(URLEncoder.encode(entry.getValue(), ENCODE_CHARSET));
                builder.append("&");
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return builder.toString();
    }


}
