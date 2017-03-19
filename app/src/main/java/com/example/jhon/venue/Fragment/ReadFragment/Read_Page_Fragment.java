package com.example.jhon.venue.Fragment.ReadFragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jhon.venue.Adapter.RV_Swripe_Adapter;
import com.example.jhon.venue.Bean.Imm;
import com.example.jhon.venue.Bean.ImmUtil;
import com.example.jhon.venue.Interface.JudgeInterface;
import com.example.jhon.venue.Interface.LoadMoreDataListener;
import com.example.jhon.venue.R;
import com.example.jhon.venue.Util.UpdateDataUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by John on 2017/2/27.
 */

public class Read_Page_Fragment extends Fragment implements RV_Swripe_Adapter.AddViewAdapterListener, RV_Swripe_Adapter.AddHolderListener {
    private static final String TAG= "Read_Page_Fragment";

    private View view;
    private RecyclerView rv_read_page;
    private RV_Swripe_Adapter rv_swripe_adapter;

    private SwipeRefreshLayout srl_read_page;

    private List<Imm.ResultsBean> list=new ArrayList<Imm.ResultsBean>();
    private List<Imm.ResultsBean> moreData=new ArrayList<Imm.ResultsBean>();
    private List<Imm.ResultsBean> refreshData=new ArrayList<Imm.ResultsBean>();

    private int page;
    private int firstpage;
    private boolean isFirst=true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.read_page_main,container,false);
        Log.d(TAG,"Read_Page_Fragment");

        initSWL();
        initRV();

        initData();

        initListener();

        return view;
    }

    private void initData() {
        srl_read_page.setEnabled(false);
        page=1;
        firstpage=1;
        srl_read_page.setRefreshing(true);
        UpdateDataUtil.initData("http://gank.io/api/data/福利/10/" + page, new JudgeInterface() {
            @Override
            public void onSuccess() {
                srl_read_page.setRefreshing(false);
                list.clear();
                list.addAll(ImmUtil.getImm().getResults());
                rv_swripe_adapter.notifyDataSetChanged();

                isFirst=false;
                srl_read_page.setEnabled(true);
                rv_swripe_adapter.setLoaded(false);
            }

            @Override
            public void onError(Exception e) {
                srl_read_page.setRefreshing(false);
            }
        });
    }

    //初始化加载更多数据,数据最好超过临界值5
    private void loadMoreDataFromNet() {
        page++;
        if (page>0){
            UpdateDataUtil.loadMoreData("http://gank.io/api/data/福利/10/" + page, new JudgeInterface() {
                @Override
                public void onSuccess() {
//                    srl_read_page.setRefreshing(false);
                    moreData.clear();
                    moreData.addAll(ImmUtil.getImm().getResults());
                    list.remove(list.size() - 1);
                    rv_swripe_adapter.notifyDataSetChanged();
                    list.addAll(moreData);

                    rv_swripe_adapter.notifyDataSetChanged();
                    rv_swripe_adapter.setLoaded(false);

                }

                @Override
                public void onError(Exception e) {
                    list.remove(list.size() - 1);
                    rv_swripe_adapter.notifyDataSetChanged();
                    rv_swripe_adapter.setLoaded(false);
//                    srl_read_page.setRefreshing(false);
                }
            });
        }else {
            Snackbar.make(view,"没有上一页了",Snackbar.LENGTH_SHORT).show();
        }

    }

    //初始化下拉刷新数据
    private void loadRefreshDataFromNet() {
        firstpage--;
        if (firstpage>0){
            UpdateDataUtil.loadRefreshData("http://gank.io/api/data/福利/10/" + page, new JudgeInterface() {
                @Override
                public void onSuccess() {
                    srl_read_page.setRefreshing(false);
                    //移除刷新的progressBar
                    refreshData.clear();
                    refreshData.addAll(ImmUtil.getImm().getResults());
                    list.addAll(0, refreshData);
                    rv_swripe_adapter.notifyDataSetChanged();
                    srl_read_page.setRefreshing(false);
                }

                @Override
                public void onError(Exception e) {
                    firstpage++;
                    srl_read_page.setRefreshing(false);
                }
            });
        }else {
            firstpage++;
            srl_read_page.setRefreshing(false);
            Snackbar.make(view,"没有下一页了",Snackbar.LENGTH_SHORT).show();
        }

    }

    private void initRV() {
        rv_read_page= (RecyclerView) view.findViewById(R.id.rv_read_page);
        rv_read_page.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_swripe_adapter=new RV_Swripe_Adapter(getContext(),rv_read_page);

        rv_swripe_adapter.setData(list);

        rv_swripe_adapter.setAddViewAdapterListener(this);
        rv_swripe_adapter.setAddHolderListener(this);

        rv_swripe_adapter.setAddNullListener(new RV_Swripe_Adapter.AddNullListener() {
            @Override
            public void nullLayoutListener(View view) {
                page=1;
                initData();
            }
        });

        rv_read_page.setAdapter(rv_swripe_adapter);
    }

    private void initSWL() {
        srl_read_page= (SwipeRefreshLayout) view.findViewById(R.id.srl_read_page);
        srl_read_page.setColorSchemeColors(Color.parseColor("#FF4081"));
    }

    //下拉事件的监听
    private Handler handler=new Handler();
    //初始化监听
    private void initListener() {
        srl_read_page.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //模拟下拉刷新数据操作
                if (!isFirst){
                    srl_read_page.setRefreshing(true);
                    loadRefreshDataFromNet();
                }
            }
        });

        //加载更多回调监听
        rv_swripe_adapter.setOnMoreDataLoadListener(new LoadMoreDataListener() {
            @Override
            public void loadMoreData() {

                if (!isFirst){
                    //加入null值此时adapter会判断item的type
                    list.add(null);
                    rv_swripe_adapter.notifyDataSetChanged();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            loadMoreDataFromNet();
                        }
                    }, 2000);
                }
            }
        });
    }

    /*----------------------------------------*/
    @Override
    public void addListener(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof TextAdapter){//
            ((TextAdapter)holder).text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(v,position+"",Snackbar.LENGTH_SHORT).show();
                }
            });
        }else if (holder instanceof PersonAdapter){
            ((PersonAdapter) holder).tv_detail_title.setText(list.get(position).getDesc());
            ((PersonAdapter) holder).tv_detail_subtitle.setText(list.get(position).getCreatedAt()+"");
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
            if (list.get(position).getUrl()!=null){
//                DisplayMetrics dm=new DisplayMetrics();
//                getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
//                String url=list.get(position).getUrl();
//                url=url+"?imageView2/0/w/"+dm.widthPixels;
                Glide.with(getContext()).load(list.get(position).getUrl())
                        .override(view.getMeasuredWidth(),view.getMeasuredWidth())
                        .centerCrop()
                        .into(((PersonAdapter)holder).img_detail_bg);
            }
        }
//                Snackbar.make(v,position+"",Snackbar.LENGTH_SHORT).show();
    }

    /*----------------------------------------*/
    @Override
    public RecyclerView.ViewHolder addFirstView(LayoutInflater inflater,ViewGroup viewGroup,View view) {
        view= inflater.inflate(R.layout.detail,viewGroup,false);
        return new PersonAdapter(view);
    }

    @Override
    public RecyclerView.ViewHolder addSecondView(LayoutInflater inflater,ViewGroup viewGroup,View view) {
        view=inflater.inflate(R.layout.person_top,viewGroup,false);
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
            text= (ImageView) itemView.findViewById(R.id.img_person);
        }
    }

    public class NullAdapter extends RecyclerView.ViewHolder {

        public NullAdapter(View itemView) {
            super(itemView);
        }
    }

    public class PersonAdapter extends RecyclerView.ViewHolder {

        private Button btn_card_main1_action1, btn_card_main1_action2;
        private ImageView img_detail_bg;
        private TextView tv_detail_title,tv_detail_subtitle;

        public PersonAdapter(View itemView) {
            super(itemView);
//            btn_card_main1_action1 = (Button) itemView.findViewById(R.id.btn_card_main1_action1);
//            btn_card_main1_action2 = (Button) itemView.findViewById(R.id.btn_card_main1_action2);
            img_detail_bg = (ImageView) itemView.findViewById(R.id.img_detail_bg);
            tv_detail_title= (TextView) itemView.findViewById(R.id.tv_detail_title);
            tv_detail_subtitle= (TextView) itemView.findViewById(R.id.tv_detail_subtitle);
        }
    }

    public static Read_Page_Fragment newInstance() {
        
        Bundle args = new Bundle();
        
        Read_Page_Fragment fragment = new Read_Page_Fragment();
        fragment.setArguments(args);
        return fragment;
    }
}
