package com.liteng.dev.cominfo.entry;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liteng on 16/7/13.
 */
public class NewsDetail {


    /**
     * author : qiujiayu
     * id : 75174
     * authorid : 1469495
     * title : AutoLoadCache 4.14 发布，增加 OGNL 表达式引擎支持
     * body : <body>sdfasfsdfsafasdfsddsf<body/>
     * pubDate : 2016-07-13 14:08:41
     * favorite : 0
     * url : http://www.oschina.net/news/75174/autoloadcach-4-14
     * mRelativies : [{"title":"AutoLoadCache 4.12 发布，优化 FastJson 深度复制功能","url":"http://www.oschina.net/news/74821/autoloadcach- 4-12"},{"title":"AutoLoadCache 4.11 发布，增加刷新缓存及续租缓存功能","url":"http://www.oschina.net/news/74519/autoloadcache-4-11"},{"title":"AutoLoadCache 4.10 发布，增加数据压缩支持","url":"http://www.oschina.net/news/74337/autoloadcache-4-10"},{"title":"AutoLoadCache 4.8 发布，实现了表达式的可扩展性","url":"http://www.oschina.net/news/73986/autoloadcache-4-8"},{"title":"AutoLoadCache 4.4 发布，完善使用 Map 本地缓存","url":"http://www.oschina.net/news/73308/autoloadcache-4-4"},{"title":"AutoLoadCache 4.0 发布，实现 AOP 的可扩展","url":"http://www.oschina.net/news/72935/autoloadcache-4-0"},{"title":"AutoLoadCache 3.7 发布，对一些细节进行优化","url":"http://www.oschina.net/news/72661/autoloadcache-3-7"},{"title":"AutoLoadCache 3.5 发布，增强了缓存设置功能","url":"http://www.oschina.net/news/72088/autoloadcache-3-5"},{"title":"AutoLoadCache 3.3 发布，提升 Sping EL  执行效率","url":"http://www.oschina.net/news/71295/autoloadcache-3-3"},{"title":"AutoLoadCache 3.2 发布，进一步优化\u201c拿来主义\u201d机制","url":"http://www.oschina.net/news/70912/autoloadcache-3-2"}]
     * commentCount : 6
     * notice : {"referCount":0,"replyCount":0,"msgCount":0,"fansCount":0}
     */

    private String author;
    private String id;
    private String authorid;
    private String title;
    private String body;
    private String pubDate;
    private String favorite;
    private String url;
    private String commentCount;
    /**
     * title : AutoLoadCache 4.12 发布，优化 FastJson 深度复制功能
     * url : http://www.oschina.net/news/74821/autoloadcach- 4-12
     */

    private List<Relativie> mRelativies;

    public NewsDetail(JSONObject jObj) {
        this.setId(jObj.optString("id"));
        this.setAuthor(jObj.optString("author"));
        this.setAuthorid(jObj.optString("authorid"));
        this.setTitle(jObj.optString("title"));
        this.setBody(jObj.optString("body"));
        this.setPubDate(jObj.optString("pubDate"));
        this.setFavorite(jObj.optString("favorite"));
        this.setUrl(jObj.optString("url"));
        this.setCommentCount(jObj.optString("commentCount"));


        mRelativies = new ArrayList<>();
        JSONArray jarr = jObj.optJSONArray("relativies");
        for (int i = 0; i < jarr.length(); i++) {
            JSONObject relativieObj = jarr.optJSONObject(i);
            Relativie relativie = new Relativie(relativieObj);
            mRelativies.add(relativie);
        }

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthorid() {
        return authorid;
    }

    public void setAuthorid(String authorid) {
        this.authorid = authorid;
    }

    public String getFavorite() {
        return favorite;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }

    public String getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(String commentCount) {
        this.commentCount = commentCount;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
     public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

     public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public List<Relativie> getRelativies() {
        return mRelativies;
    }

    public void setRelativies(List<Relativie> relativies) {
        this.mRelativies = relativies;
    }



    public static class Relativie {
        private String title;
        private String url;

        public Relativie(JSONObject relativieObj) {
            this.setTitle(relativieObj.optString("title"));
            this.setUrl(relativieObj.optString("url"));
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
