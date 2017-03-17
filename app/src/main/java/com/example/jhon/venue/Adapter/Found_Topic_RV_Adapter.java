package com.example.jhon.venue.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jhon.venue.R;

import java.util.List;

/**
 * Created by John on 2017/3/10.
 */

public class Found_Topic_RV_Adapter extends RecyclerView.Adapter<Found_Topic_RV_Adapter.TopicAdapter> {

    private Context context;
    private List<String> list;

    private boolean isFavoriteClicked=false;
    private AlphaAnimation alphaAnimationShowIcon;

    public Found_Topic_RV_Adapter(Context context,List<String> list) {
        this.context = context;
        this.list=list;

        alphaAnimationShowIcon = new AlphaAnimation(0.2f, 1.0f);
        alphaAnimationShowIcon.setDuration(500);
    }

    @Override
    public TopicAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.found_topic_hot_item,parent,false);
        TopicAdapter topicAdapter=new TopicAdapter(view);
        return topicAdapter;
    }

    @Override
    public void onBindViewHolder(final TopicAdapter holder, final int position) {
        holder.tv_found_topic_hot_title.setText("Title");
        Glide.with(context).load(R.drawable.material_design_1).fitCenter().into(holder.img_found_topic_hot_title);
        holder.img_found_topic_hot_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isFavoriteClicked) {
                    holder.img_found_topic_hot_favorite.setImageResource(R.mipmap.ic_favorite_black_24dp);
                    holder.img_found_topic_hot_favorite.startAnimation(alphaAnimationShowIcon);
                    isFavoriteClicked = true;
                    v.setTag(true);
                    onItemClickListener.onItemClick(v,position);
                } else {
                    holder.img_found_topic_hot_favorite.setImageResource(R.mipmap.ic_favorite_border_black_24dp);
                    holder.img_found_topic_hot_favorite.startAnimation(alphaAnimationShowIcon);
                    isFavoriteClicked = false;
                    v.setTag(false);
                    onItemClickListener.onItemClick(v,position);
                }
            }
        });
        holder.img_found_topic_hot_bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isFavoriteClicked) {
                    holder.img_found_topic_hot_bookmark.setImageResource(R.mipmap.ic_bookmark_black_24dp);
                    holder.img_found_topic_hot_bookmark.startAnimation(alphaAnimationShowIcon);
                    isFavoriteClicked = true;
                    v.setTag(true);
                    onItemClickListener.onItemClick(v,position);
                } else {
                    holder.img_found_topic_hot_bookmark.setImageResource(R.mipmap.ic_bookmark_border_black_24dp);
                    holder.img_found_topic_hot_bookmark.startAnimation(alphaAnimationShowIcon);
                    isFavoriteClicked = false;
                    v.setTag(false);
                    onItemClickListener.onItemClick(v,position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class TopicAdapter extends RecyclerView.ViewHolder {

        private CardView found_topic_hot_item;
        private ImageView img_found_topic_hot_title,img_found_topic_hot_favorite,img_found_topic_hot_bookmark,img_found_topic_hot_share;
        private TextView tv_found_topic_hot_title;

        public TopicAdapter(View itemView) {
            super(itemView);
            found_topic_hot_item= (CardView) itemView.findViewById(R.id.found_topic_hot_item);
            img_found_topic_hot_title = (ImageView) itemView.findViewById(R.id.img_found_topic_hot_title);
            tv_found_topic_hot_title = (TextView) itemView.findViewById(R.id.tv_found_topic_hot_title);
            img_found_topic_hot_favorite= (ImageView) itemView.findViewById(R.id.img_found_topic_hot_favorite);
            img_found_topic_hot_bookmark= (ImageView) itemView.findViewById(R.id.img_found_topic_hot_bookmark);
            img_found_topic_hot_share= (ImageView) itemView.findViewById(R.id.img_found_topic_hot_share);
        }
    }

    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }
}
