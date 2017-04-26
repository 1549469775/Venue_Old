package com.example.jhon.venue.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jhon.venue.Bean.UserUtil;
import com.example.jhon.venue.R;

import java.util.List;

/**
 * Created by John on 2017/3/17.
 */

public class PersonDetail_RV_Adapter extends RecyclerView.Adapter {

    private static final int VIEW_TOP=0;
    private static final int VIEW_BOOTM=1;
    private Context context;

    private List<String> list_top;
    private List<String> list_time;

    private RecyclerView.ViewHolder timeHolder;

    public PersonDetail_RV_Adapter(Context context,List<String> list_top, List<String> list_time) {
        Log.d("sas","ssssss");
        this.context=context;
        this.list_top = list_top;
        this.list_time = list_time;
    }

    @Override
    public int getItemViewType(int position) {
        Log.d("sas",position+"");
        return position==0?VIEW_TOP:VIEW_BOOTM;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=null;
        RecyclerView.ViewHolder viewHolder=null;
        switch (viewType){
            case VIEW_TOP:
                view= LayoutInflater.from(context).inflate(R.layout.persondetail_item,parent,false);
                viewHolder=new PersonDetailAdapter(view);
                break;
            default:
                timeHolder=addViewAdapterListener.addTimeView(parent,view);
                viewHolder=timeHolder;
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof PersonDetailAdapter){

            ((PersonDetailAdapter) holder).persondetail_person_nickname.setText(UserUtil.getUser().getNickname());

        }else if (getItemViewType(position)==VIEW_BOOTM){
            addHolderListener.addListener(holder,position);
        }
    }

    private class PersonDetailAdapter extends RecyclerView.ViewHolder{

        private TextView persondetail_person_nickname;

        public PersonDetailAdapter(View itemView) {
            super(itemView);
            persondetail_person_nickname= (TextView) itemView.findViewById(R.id.persondetail_person_nickname);
        }
    }

    @Override
    public int getItemCount() {
        return list_time.isEmpty()?1:list_time.size()+1;
    }

    private AddViewAdapterListener addViewAdapterListener;

    public interface AddViewAdapterListener{
        RecyclerView.ViewHolder addTimeView(ViewGroup parent,View view);
    }

    public void setAddViewAdapterListener(AddViewAdapterListener addViewAdapterListener) {
        this.addViewAdapterListener = addViewAdapterListener;
    }

    private AddHolderListener addHolderListener;

    public interface AddHolderListener{
        void addListener(RecyclerView.ViewHolder holder,int position);
    }

    public void setAddHolderListener(AddHolderListener addHolderListener) {
        this.addHolderListener = addHolderListener;
    }
}
