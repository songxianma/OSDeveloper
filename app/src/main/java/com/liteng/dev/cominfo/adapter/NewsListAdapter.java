package com.liteng.dev.cominfo.adapter;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liteng.dev.R;
import com.liteng.dev.cominfo.entry.NewsList;
import com.liteng.dev.utils.TimeUtils;

import java.util.List;

/**
 * Created by liteng on 16/7/13.
 */
public class NewsListAdapter extends RecyclerView.Adapter {

    private final int TYPE_FOOTER = 0x1001;

    private String mDate;

    private Context mContext;


    private LayoutInflater mInflater;

    private List<NewsList.News> mNewsList;
    private boolean hasFooter = true;

    private OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener listener){
        this.mListener = listener;
    }


    public NewsListAdapter(Context context, List<NewsList.News> newsList) {
        this.mNewsList = newsList;
        this.mContext = context;
        mInflater = LayoutInflater.from(mContext);

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case TYPE_FOOTER:
                view = mInflater.inflate(R.layout.include_loading_footer, null);
                holder = new FooterViewHolder(view);
                break;
            default:
                view = mInflater.inflate(R.layout.item_news_list, null);
                holder = new NewsListViewHolder(view);
                break;
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        switch (viewType) {
            case TYPE_FOOTER:
                FooterViewHolder viewHolder = (FooterViewHolder) holder;
                if (hasFooter) {
                    viewHolder.loadingFooterContainer.setVisibility(View.VISIBLE);
                } else {
                    viewHolder.loadingFooterContainer.setVisibility(View.GONE);
                }
                break;
            default:
                if (null == mNewsList) {
                    return;
                }

                NewsList.News news = mNewsList.get(position);

                NewsListViewHolder newsHolder = (NewsListViewHolder) holder;

                if (news.isToday()) {
                    newsHolder.ivIconToday.setVisibility(View.VISIBLE);
                } else {
                    newsHolder.ivIconToday.setVisibility(View.GONE);
                }
                newsHolder.tvTitle.setText(news.getTitle());
                newsHolder.tvPubTime.setText(news.getPubDate());
                newsHolder.tvComments.setText(news.getCommentCount() + "");
                break;
        }


    }

    @Override
    public int getItemCount() {
        return mNewsList.size();
    }

    private class NewsListViewHolder extends RecyclerView.ViewHolder {
        ImageView ivIconToday;
        TextView tvTitle;
        TextView tvPubTime;
        TextView tvComments;

        public NewsListViewHolder(final View itemView) {
            super(itemView);
            ivIconToday = (ImageView) itemView.findViewById(R.id.ivIconToday);
            tvTitle = (TextView) itemView.findViewById(R.id.tvNewsTitle);
            tvPubTime = (TextView) itemView.findViewById(R.id.tvNewsPubTime);
            tvComments = (TextView) itemView.findViewById(R.id.tvNewsComments);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener!=null){
                        mListener.onItemClick(itemView,getLayoutPosition());
                    }
                }
            });
        }
    }


    private static class FooterViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout loadingFooterContainer;

        public FooterViewHolder(View itemView) {
            super(itemView);
            loadingFooterContainer = (LinearLayout) itemView.findViewById(R.id.loadingFooterContainer);
        }
    }


    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() + 1 && hasFooter) {
            return TYPE_FOOTER;
        }
        return super.getItemViewType(position);
    }


    public interface  OnItemClickListener{
        void onItemClick(View itemView,int position);
    }
}
