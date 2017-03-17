package com.example.jhon.venue.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jhon.venue.R;

import java.util.List;

/**
 * Created by John on 2017/3/10.
 */

public class Message_RV_Adapter extends RecyclerView.Adapter<Message_RV_Adapter.MessageAdapter> {

    private Context context;
    private List<String> list;

    public Message_RV_Adapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MessageAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.message_item,parent,false);
        MessageAdapter messageAdapter=new MessageAdapter(view);
        return messageAdapter;
    }

    @Override
    public void onBindViewHolder(MessageAdapter holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MessageAdapter extends RecyclerView.ViewHolder {

        public MessageAdapter(View itemView) {
            super(itemView);
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
