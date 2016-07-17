package com.liteng.dev.cominfo.entry;

import org.json.JSONObject;

/**
 * Created by liteng on 16/7/17.
 */
public class Blog {


    //    1-原创 4-转载
    public static final long YUAN = 1;
    public static final long ZHUAN = 4;
    /**
     * id : 49090
     * pubDate : 2012-03-13 23:01:52.0
     * author : whxia320
     * title : mongodb for java使用中的小问题
     * authorid : 100399
     * type : 1
     * commentCount : 7
     */

    private String id;
    private String pubDate;
    private String author;
    private String title;
    private String authorid;
    private long type;
    private String commentCount;

    private boolean isYuan;


    public Blog(JSONObject blogObj) {
        this.setId(blogObj.optString("id"));
        this.setPubDate(blogObj.optString("pubDate"));
        this.setAuthor(blogObj.optString("author"));
        this.setTitle(blogObj.optString("title"));
        this.setAuthorid(blogObj.optString("authorid"));
        this.setType(blogObj.optLong("type"));

        this.setYuan(this.getType() == YUAN ? true : false);

        this.setCommentCount(blogObj.optString("commentCount"));
    }


    public boolean isYuan() {
        return isYuan;
    }

    public void setYuan(boolean yuan) {
        isYuan = yuan;
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

    public String getAuthorid() {
        return authorid;
    }

    public void setAuthorid(String authorid) {
        this.authorid = authorid;
    }


    public long getType() {
        return type;
    }

    public void setType(long type) {
        this.type = type;
    }

    public String getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(String commentCount) {
        this.commentCount = commentCount;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", pubDate='" + pubDate + '\'' +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", authorid=" + authorid +
                ", type=" + type +
                ", commentCount=" + commentCount +
                '}';
    }
}
