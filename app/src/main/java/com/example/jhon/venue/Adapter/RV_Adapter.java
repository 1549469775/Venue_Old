package com.example.jhon.venue.Adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jhon.venue.Bean.Imm;
import com.example.jhon.venue.Interface.LoadMoreDataListener;
import com.example.jhon.venue.Interface.RecyclerOnItemClickListener;

import java.util.List;

/**
 * Created by John on 2017/3/10.
 */

public class RV_Adapter extends RecyclerView.Adapter {

    private Context context;
    private static final int VIEW_TOP=0;
    private static final int VIEW_ITEM1=1;
    private static final int VIEW_ITEM2=2;
    private static final int VIEW_FOOTER=3;

    private LayoutInflater inflater;
    private List<Imm.ResultsBean> list_data;

    private RecyclerView.ViewHolder topHolder;
    private RecyclerView.ViewHolder firstHolder;
    private RecyclerView.ViewHolder secondHolder;
    private RecyclerView.ViewHolder footerHolder;

    //当前滚动的position下面最小的items的临界值
    private int visibleThreshold = 5;

    private boolean isLoading;
    private int totalItemCount;
    private int lastVisibleItemPosition;

    public RV_Adapter(Context context,RecyclerView recyclerView) {
        this.context = context;
        inflater=LayoutInflater.from(context);

        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            //mRecyclerView添加滑动事件监听
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    totalItemCount = linearLayoutManager.getItemCount();
                    lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
                    Log.d("test", "totalItemCount =" + totalItemCount + "-----" + "lastVisibleItemPosition =" + lastVisibleItemPosition);
                    if (!isLoading && totalItemCount <= (lastVisibleItemPosition + visibleThreshold)) {
                        //此时是刷新状态
                        if (mMoreDataListener != null)
                            mMoreDataListener.loadMoreData();
                        isLoading = true;
                    }
                }
            });
        }
    }

    public void setData(List<Imm.ResultsBean> list){
        this.list_data=list;
    }

    @Override
    public int getItemViewType(int position) {
        int type;//
        type=list_data.get(position)!=null?0:VIEW_FOOTER;/*-------------------------*/
        return type;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=null;
        RecyclerView.ViewHolder holder;
        switch (viewType){
            case VIEW_TOP:
                topHolder=addViewAdapterListener.addTopView(view);
                holder=topHolder;
                break;
            case VIEW_ITEM1:
                firstHolder=addViewAdapterListener.addFirstView(view);
                holder=firstHolder;
                break;
            case VIEW_ITEM2:
                secondHolder=addViewAdapterListener.addSecondView(view);
                holder=secondHolder;
                break;
            default:
                footerHolder=addViewAdapterListener.addFooterView(view);
                holder=footerHolder;
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)){
            case VIEW_TOP:
                addHolderListener.addListener(holder,position);
                break;
            case VIEW_ITEM1:
                addHolderListener.addListener(holder,position);
                break;
            case VIEW_ITEM2:
                addHolderListener.addListener(holder,position);
                break;
            case VIEW_FOOTER:
                addHolderListener.addListener(holder,position);
        }
    }

    @Override
    public int getItemCount() {
        return list_data.size();
    }

    private AddViewAdapterListener addViewAdapterListener;

    public interface AddViewAdapterListener{
        RecyclerView.ViewHolder addFirstView(View view);
        RecyclerView.ViewHolder addSecondView(View view);
        RecyclerView.ViewHolder addTopView(View view);
        RecyclerView.ViewHolder addFooterView(View view);
    }

    public void setAddViewAdapterListener(AddViewAdapterListener addViewAdapterListener) {
        this.addViewAdapterListener = addViewAdapterListener;
    }

    private OnItemClickListener onItemClickListener;


    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }

    private AddHolderListener addHolderListener;

    public interface AddHolderListener{
        void addListener(RecyclerView.ViewHolder holder,int position);
    }

    public void setAddHolderListener(AddHolderListener addHolderListener) {
        this.addHolderListener = addHolderListener;
    }

    private LoadMoreDataListener mMoreDataListener;

    //加载更多监听方法
    public void setOnMoreDataLoadListener(LoadMoreDataListener onMoreDataLoadListener) {
        mMoreDataListener = onMoreDataLoadListener;
    }

    private RecyclerOnItemClickListener mOnitemClickListener;

    //点击事件监听方法
    public void setOnItemClickListener(RecyclerOnItemClickListener onItemClickListener) {
        mOnitemClickListener = onItemClickListener;
    }
}
