package com.example.jhon.venue.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jhon.venue.Activity.LoginActivity;
import com.example.jhon.venue.Activity.PersonDetailActivity;
import com.example.jhon.venue.Bean.Icon;
import com.example.jhon.venue.R;
import com.example.jhon.venue.Util.GlideCircleTransformUtil;

import java.util.List;

/**
 * Created by John on 2017/2/27.
 */

public class Person_RV_Adapter extends RecyclerView.Adapter<Person_RV_Adapter.PersonAdapter> {

    private Context context;

    private boolean isFirst=true,isSecond=true;

    private String nickname;
    private String imagePath;
    private List<Integer> list_layout;
    private List<Icon> list_icon;
    private List<Icon> list_icon_bottom;

    public Person_RV_Adapter(Context context, List<Integer> list,List<Icon> list_icon, List<Icon> list_icon_bottom) {
        this.list_layout=list;
        this.context=context;
        this.list_icon=list_icon;
        this.list_icon_bottom=list_icon_bottom;
    }

    @Override
    public int getItemViewType(int position) {
        return list_layout.get(position);
    }

    @Override
    public PersonAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=null;
        PersonAdapter personAdapter=null;
        switch (viewType){
            case 0:
                view= LayoutInflater.from(parent.getContext()).inflate(R.layout.person_top,parent,false);
                personAdapter=new PersonAdapter(view);
                break;
            case 1:
                view= LayoutInflater.from(parent.getContext()).inflate(R.layout.person_center_btn,parent,false);
                personAdapter=new PersonAdapter(view);
                break;
            case 2:
                view= LayoutInflater.from(parent.getContext()).inflate(R.layout.person_center_btn,parent,false);
                personAdapter=new PersonAdapter(view);
                break;
        }

        return personAdapter;
    }

    @Override
    public void onBindViewHolder(final PersonAdapter holder, final int position) {
        switch (getItemViewType(position)){
            case 0:
                if (!TextUtils.isEmpty(nickname)){
                    holder.tv_person_name.setText(nickname);
                    Glide.with(context).load(imagePath).transform(new GlideCircleTransformUtil(context)).into(holder.img_person);
                }
                holder.card_person_top.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onImgClickListener.onImgClick(v);
//                        context.startActivity(new Intent(context, LoginActivity.class));
                    }
                });
                holder.img_person.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onImgClickListener.onImgClick(v);
                    }
                });
                holder.img_person.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        onLongClickListener.onLongClick(v);
                        return false;
                    }
                });
                break;
            case 1:
                if (isFirst){
                    //动态添加
                    for (int i=0;i<list_icon.size();i++){
                        View view=LayoutInflater.from(context).inflate(R.layout.person_center_item,holder.ll_person_center,false);
                        view.setTag(i);//为了给他们编序号
                        ((ImageView)view.findViewById(R.id.img_person_item)).setImageResource(list_icon.get(i).getiId());
                        ((TextView)view.findViewById(R.id.tv_person_item)).setText(list_icon.get(i).getiName());
                        if (list_icon.get(i).getiId()==0){//当没有图片时
                            ((TextView)view.findViewById(R.id.tv_person_item)).setTextSize(13);
                            ((TextView)view.findViewById(R.id.tv_person_item)).setTextColor(Color.BLACK);
                        }
                        view.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Snackbar.make(v,""+v.getTag()+list_icon.get((int)v.getTag()).getiName(),Snackbar.LENGTH_SHORT).show();
                            }
                        });
                        holder.ll_person_center.addView(view);
                        isFirst=false;
                }
                }
                break;
            case 2:
                if (isSecond){
                    //动态添加
                    for (int i=0;i<list_icon_bottom.size();i++){
                        View view=LayoutInflater.from(context).inflate(R.layout.person_center_item,holder.ll_person_center,false);
                        view.setTag(i);//为了给他们编序号
                        ((ImageView)view.findViewById(R.id.img_person_item)).setImageResource(list_icon_bottom.get(i).getiId());
                        ((TextView)view.findViewById(R.id.tv_person_item)).setText(list_icon_bottom.get(i).getiName());
//                    ((LinearLayout)view.getRootView()).removeViewAt(2);//去除按钮中的>图片
                        view.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Snackbar.make(v,""+v.getTag(),Snackbar.LENGTH_SHORT).show();
                            }
                        });
                        holder.ll_person_center.addView(view);
                    }
                    isSecond=false;
                }

                break;
        }

    }

    @Override
    public int getItemCount() {
        return list_layout.size();
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public class PersonAdapter extends RecyclerView.ViewHolder {

        private LinearLayout ll_person_center;
        private CardView card_person_top;
        private ImageView img_person;
        private TextView tv_person_name;
//        private TextView tv_person_name;

        public PersonAdapter(View itemView) {
            super(itemView);
//            tv_person_name=(TextView) itemView.findViewById(R.id.tv_person_name);
            img_person=(ImageView) itemView.findViewById(R.id.img_person);
            ll_person_center= (LinearLayout) itemView.findViewById(R.id.ll_person_center);
            card_person_top= (CardView) itemView.findViewById(R.id.card_person_top);
            tv_person_name= (TextView) itemView.findViewById(R.id.tv_person_name);
        }
    }

    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }

    private OnImgClickListener onImgClickListener;

    public interface OnImgClickListener{
        void onImgClick(View view);
    }

    public void setOnImgClickListener(OnImgClickListener onImgClickListener){
        this.onImgClickListener=onImgClickListener;
    }

    private OnLongClickListener onLongClickListener;

    public interface OnLongClickListener{
        void onLongClick(View view);
    }

    public void setOnLongClickListener(OnLongClickListener onLongClickListener) {
        this.onLongClickListener = onLongClickListener;
    }
}
