package com.liteng.dev.cominfo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.liteng.dev.R;
import com.liteng.dev.cominfo.entry.Blog;

import java.util.List;

/**
 * Created by liteng on 16/7/17.
 */
public class BlogAdatper extends BaseAdapter {

    private List<Blog> mBlogList;
    private Context mContext;
    private LayoutInflater mInflater;

    public BlogAdatper(Context context,List<Blog> blogList){
        this.mBlogList = blogList;
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mBlogList.size();
    }

    @Override
    public Object getItem(int position) {
        return mBlogList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.item_blog_list,null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        Blog blog = mBlogList.get(position);

//        long type = blog.getType();
        if(blog.isYuan()){
            holder.ivYuan.setImageResource(R.drawable.icon_widget_original);
        }else{
            holder.ivYuan.setImageResource(R.drawable.icon_widget_repaste);
        }

        holder.tvTitle.setText(blog.getTitle());
        holder.tvAuthor.setText(blog.getAuthor());
        holder.tvTime.setText(blog.getPubDate());
        holder.tvComment.setText(blog.getCommentCount());

        return convertView;
    }

    private static class ViewHolder{
        ImageView ivYuan;
        TextView tvTitle;
        TextView tvAuthor;
        TextView tvTime;
        TextView tvComment;

        public ViewHolder(View convertView){
            ivYuan = (ImageView) convertView.findViewById(R.id.ivBlogYuan);
            tvTitle = (TextView) convertView.findViewById(R.id.tvBlogTitle);
            tvAuthor= (TextView) convertView.findViewById(R.id.tvBlogAuthor);
            tvTime = (TextView) convertView.findViewById(R.id.tvBlogTime);
            tvComment = (TextView) convertView.findViewById(R.id.tvBlogComment);
        }

    }




}
