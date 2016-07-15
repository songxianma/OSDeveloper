package com.liteng.dev.tweet.entry;

import org.json.JSONObject;

/**
 * Created by liteng on 16/7/14.
 */
public class Tweet {

    //0：最新动弹，-1：热门动弹，其它：我的
    public static final int TYPE_RENCENT = 0;
    public static final int TYPE_HOT = -1;
    public static final int TYPE_MINE = 2;

    /**
     * id : 1121274
     * pubDate : 2013-08-28 18:40:07.0
     * body : @做最好的三三
     * author : 彭博
     * authorid : 89964
     * commentCount : 0
     * portrait : http://static.oschina.net/uploads/user/44/89964_50.jpg?t=1376365607000
     */

    private String id;
    private String pubDate;
    private String body;
    private String author;
    private String authorid;
    private String commentCount;
    private String portrait;

    public Tweet(JSONObject tweetObj) {
        this.setId(tweetObj.optString("id"));
        this.setPubDate(tweetObj.optString("pubDate"));
        this.setBody(tweetObj.optString("body"));
        this.setAuthor(tweetObj.optString("author"));
        this.setAuthorid(tweetObj.optString("auhtorid"));
        this.setCommentCount(tweetObj.optString("commentCount"));
        this.setPortrait(tweetObj.optString("portrait"));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthorid() {
        return authorid;
    }

    public void setAuthorid(String authorid) {
        this.authorid = authorid;
    }

    public String getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(String commentCount) {
        this.commentCount = commentCount;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }
}
