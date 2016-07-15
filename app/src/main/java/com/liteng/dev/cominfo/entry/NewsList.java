package com.liteng.dev.cominfo.entry;

import android.view.View;

import com.liteng.dev.R;
import com.liteng.dev.utils.TimeUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 新闻列表
 */
public class NewsList {

    /**
     * 新闻的各个提醒
     */
    private Notice notice;



    private List<News> newslist;

    public NewsList(JSONObject jObj) {
        newslist = new ArrayList<>();
        JSONArray jArr = jObj.optJSONArray("newslist");
        for (int i = 0; i < jArr.length(); i++) {
            News news =new News(jArr.optJSONObject(i));
            newslist.add(news);
        }

        notice = new Notice(jObj.optJSONObject("notice"));


    }

    public Notice getNotice() {
        return notice;
    }

    public void setNotice(Notice notice) {
        this.notice = notice;
    }

    public List<News> getNewslist() {
        return newslist;
    }


    /**
     "replyCount": 0,
     "msgCount": 0,
     "fansCount": 0,
     "referCount": 0
     */
    public static class Notice {
        //未读@我 的数量
        private int referCount;
        //未读评论数
        private int replyCount;
        //未读私信数
        private int msgCount;
        //新增粉丝数
        private int fansCount;

        public Notice(JSONObject notice) {
            this.setReferCount(notice.optInt("referCount"));
            this.setReplyCount(notice.optInt("replyCount"));
            this.setMsgCount(notice.optInt("msgCount"));
            this.setFansCount(notice.optInt("fansCount"));
        }

        public int getReferCount() {
            return referCount;
        }

        public void setReferCount(int referCount) {
            this.referCount = referCount;
        }

        public int getReplyCount() {
            return replyCount;
        }

        public void setReplyCount(int replyCount) {
            this.replyCount = replyCount;
        }

        public int getMsgCount() {
            return msgCount;
        }

        public void setMsgCount(int msgCount) {
            this.msgCount = msgCount;
        }

        public int getFansCount() {
            return fansCount;
        }

        public void setFansCount(int fansCount) {
            this.fansCount = fansCount;
        }

        @Override
        public String toString() {
            return "Notice{" +
                    "referCount=" + referCount +
                    ", replyCount=" + replyCount +
                    ", msgCount=" + msgCount +
                    ", fansCount=" + fansCount +
                    '}';
        }
    }


    private String mDate = null;
    /**
     *     {
     "id": 26754,
     "author": "test33",
     "pubDate": "2013-09-17 16:49:50.0",
     "title": "asdfa",
     "authorid": 253469,
     "commentCount": 0,
     "type": 4
     }
     */
    public class News {
        private String author;
        private String id;
        private String title;
        private int type;
        private String authorid;
        private String pubDate;
        private int commentCount;
        private boolean isToday;



        public News(JSONObject jObj) {
            this.setAuthor(jObj.optString("author"));
            this.setId(jObj.optString("id"));
            this.setType(jObj.optInt("type"));
            this.setTitle(jObj.optString("title"));
            this.setAuthorid(jObj.optString("authorid"));
            this.setPubDate(jObj.optString("pubDate"));
            this.setCommentCount(jObj.optInt("commentCount"));

            String date = this.getPubDate().split(" ")[0];
            if (date.equalsIgnoreCase(TimeUtils.getStringDateShort())) {
                this.setToday(true);
            } else {
                this.setToday(false);
            }

        }

        public boolean isToday() {
            return isToday;
        }

        public void setToday(boolean today) {
            isToday = today;
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

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }


        public String getPubDate() {
            return pubDate;
        }

        public void setPubDate(String pubDate) {
            this.pubDate = pubDate;
        }

        public int getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(int commentCount) {
            this.commentCount = commentCount;
        }

        @Override
        public String toString() {
            return "News{" +
                    "author='" + author + '\'' +
                    ", id='" + id + '\'' +
                    ", title='" + title + '\'' +
                    ", type=" + type +
                    ", authorid='" + authorid + '\'' +
                    ", pubDate='" + pubDate + '\'' +
                    ", commentCount=" + commentCount +
                    '}';
        }
    }
}
