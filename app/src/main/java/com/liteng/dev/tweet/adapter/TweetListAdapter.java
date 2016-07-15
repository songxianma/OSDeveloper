package com.liteng.dev.tweet.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.liteng.dev.R;
import com.liteng.dev.tweet.entry.Tweet;
import com.liteng.dev.widget.CircleImageView;

import java.util.List;

/**
 * Created by liteng on 16/7/14.
 */
public class TweetListAdapter extends BaseAdapter {

    private Context mContext;
    private List<Tweet> mTweets;
    private LayoutInflater mInflater;

    public TweetListAdapter(List<Tweet> tweets, Context context) {
        mTweets = tweets;
        mContext = context;

        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mTweets.size();
    }

    @Override
    public Object getItem(int position) {
        return mTweets.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.item_tweet_list,null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }


        Tweet tweet = mTweets.get(position);
        Glide.with(mContext).load(tweet.getPortrait()).into(holder.civHeader);
        holder.tvAuthorName.setText(tweet.getAuthor());
        holder.tvContent.setText(Html.fromHtml(tweet.getBody()));
        holder.tvTime.setText(tweet.getPubDate());
        holder.tvCommentCount.setText(tweet.getCommentCount());

        return convertView;
    }


    private static class ViewHolder {
        CircleImageView civHeader;
        TextView tvAuthorName;
        TextView tvContent;
        TextView tvTime;
        TextView tvCommentCount;

        public ViewHolder(View convertView) {
        civHeader = (CircleImageView) convertView.findViewById(R.id.civTweetHeader);
            tvAuthorName = (TextView) convertView.findViewById(R.id.tvTweetAuthor);
            tvContent = (TextView) convertView.findViewById(R.id.tvTweetContent);
            tvTime = (TextView) convertView.findViewById(R.id.tvTweetTime);
            tvCommentCount = (TextView) convertView.findViewById(R.id.tvTweetCommentCount);

        }
    }


}
