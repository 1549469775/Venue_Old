package com.example.jhon.venue.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.jhon.venue.Adapter.RV_Swripe_Adapter;
import com.example.jhon.venue.Bean.Imm;
import com.example.jhon.venue.Interface.LoadMoreDataListener;
import com.example.jhon.venue.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by John on 2017/3/6.
 */

public class FragmentText extends Fragment implements RV_Swripe_Adapter.AddHolderListener,RV_Swripe_Adapter.AddViewAdapterListener {

    private View view;
    private RecyclerView rv_text;
    private RV_Swripe_Adapter rv_adapter;
    private SwipeRefreshLayout srl_text;

    private List<Imm.ResultsBean> list;
    private List<Imm.ResultsBean> moreData = new ArrayList<>();
    private List<Imm.ResultsBean> refreshData = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.text,container,false);

        initData();
        initRV();
        initSWL();
        initListener();

        return view;
    }

    private void initData() {
        list=new ArrayList<>();
        for (int i=1;i<3;i++){
//            DataTest dataTest=new DataTest();
//            dataTest.setTextType(i%2);
//            list.add(dataTest);
        }
        initRefreshData();
        initMoreData();
    }

    //初始化加载更多数据,数据最好超过临界值5
    private void initMoreData() {
        for (int i=1;i<7;i++){
//            DataTest dataTest=new DataTest();
//            dataTest.setTextType(1);
//            moreData.add(dataTest);
        }
    }

    //初始化下拉刷新数据
    private void initRefreshData() {
        for (int i = 0; i < 2; i++) {
//            DataTest dataTest=new DataTest();
//            dataTest.setTextType(1);
//            refreshData.add(dataTest);
        }
    }

    private void initRV() {
        rv_text= (RecyclerView) view.findViewById(R.id.rv_text);
        rv_text.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_adapter=new RV_Swripe_Adapter(getContext(),rv_text);
        rv_adapter.setData(list);
        rv_adapter.setAddViewAdapterListener(this);
        rv_adapter.setAddHolderListener(this);

        rv_text.setAdapter(rv_adapter);
    }

    private void initSWL() {
        srl_text= (SwipeRefreshLayout) view.findViewById(R.id.srl_text);
        srl_text.setColorSchemeColors(Color.parseColor("#FF4081"));
    }

    //下拉事件的监听
    private Handler handler=new Handler();
    //初始化监听
    private void initListener() {
        srl_text.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //模拟下拉刷新数据操作
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        list.addAll(0, refreshData);
                        rv_adapter.notifyDataSetChanged();
                        srl_text.setRefreshing(false);
                    }
                }, 1000);
            }
        });

        //加载更多回调监听
        rv_adapter.setOnMoreDataLoadListener(new LoadMoreDataListener() {
            @Override
            public void loadMoreData() {
                //加入null值此时adapter会判断item的type
                list.add(null);
                rv_adapter.notifyDataSetChanged();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //移除刷新的progressBar
                        list.remove(list.size() - 1);
                        rv_adapter.notifyDataSetChanged();
                        list.addAll(moreData);
                        rv_adapter.notifyDataSetChanged();
                        rv_adapter.setLoaded(false);
                    }
                }, 2000);

            }
        });
    }

    /*----------------------------------------*/
    @Override
    public void addListener(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof TextAdapter){
            ((TextAdapter)holder).text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(v,position+"",Snackbar.LENGTH_SHORT).show();
                }
            });
        }else if (holder instanceof PersonAdapter){
//            ((PersonAdapter)holder).btn_card_main1_action1.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Snackbar.make(v,position+"",Snackbar.LENGTH_SHORT).show();
//                }
//            });
//            ((PersonAdapter)holder).btn_card_main1_action2.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Snackbar.make(v,position+"",Snackbar.LENGTH_SHORT).show();
//                }
//            });
            Glide.with(getContext()).load(R.drawable.material_design_2).fitCenter().into(((PersonAdapter)holder).img_detail_bg);
        }
//                Snackbar.make(v,position+"",Snackbar.LENGTH_SHORT).show();
    }

    /*----------------------------------------*/
    @Override
    public RecyclerView.ViewHolder addFirstView(LayoutInflater inflater,ViewGroup viewGroup,View view) {
        view= inflater.inflate(R.layout.detail,viewGroup,false);
//        view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return new PersonAdapter(view);
    }

    @Override
    public RecyclerView.ViewHolder addSecondView(LayoutInflater inflater,ViewGroup viewGroup,View view) {
        view=inflater.inflate(R.layout.person_top,viewGroup,false);
//        view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return new TextAdapter(view);
    }

    @Override
    public RecyclerView.ViewHolder addTopView(LayoutInflater inflater,ViewGroup viewGroup,View view) {
        view=new Button(getContext());
        return new NullAdapter(view);
    }

    /*----------------------------------------*/
    public class TextAdapter extends RecyclerView.ViewHolder {

        private ImageView text;

        public TextAdapter(View itemView) {
            super(itemView);
            text= (ImageView) itemView.findViewById(R.id.text1111);
        }
    }

    public class NullAdapter extends RecyclerView.ViewHolder {

        public NullAdapter(View itemView) {
            super(itemView);
        }
    }

    public class PersonAdapter extends RecyclerView.ViewHolder {

//        private Button btn_card_main1_action1, btn_card_main1_action2;
        private ImageView img_detail_bg;

        public PersonAdapter(View itemView) {
            super(itemView);
//                btn_card_main1_action1 = (Button) itemView.findViewById(R.id.btn_card_main1_action1);
//                btn_card_main1_action2 = (Button) itemView.findViewById(R.id.btn_card_main1_action2);
            img_detail_bg = (ImageView) itemView.findViewById(R.id.img_detail_bg);
        }
    }

    public static FragmentText newInstance() {
        
        Bundle args = new Bundle();
        
        FragmentText fragment = new FragmentText();
        fragment.setArguments(args);
        return fragment;
    }
}
