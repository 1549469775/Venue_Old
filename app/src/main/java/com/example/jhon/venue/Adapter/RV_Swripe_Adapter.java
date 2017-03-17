package com.example.jhon.venue.Adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.jhon.venue.Bean.Imm;
import com.example.jhon.venue.Interface.LoadMoreDataListener;
import com.example.jhon.venue.R;

import java.util.List;

/**
 * Created by John on 2017/3/10.
 */

public class RV_Swripe_Adapter extends RecyclerView.Adapter {

    private Context context;
    private static final int VIEW_NULL=-1;
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
    private RecyclerView.ViewHolder NullHolder;

    //当前滚动的position下面最小的items的临界值
    private int visibleThreshold = 5;

    private boolean isLoading;
    private int totalItemCount;
    private int lastVisibleItemPosition;

    public RV_Swripe_Adapter(Context context, RecyclerView recyclerView) {
        this.context = context;
        inflater=LayoutInflater.from(context);

        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            //mRecyclerView添加滑动事件监听
            recyclerView.setHasFixedSize(true);
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

    public void setData(List list){
        this.list_data=list;
    }

    @Override
    public int getItemViewType(int position) {
        int type;//
        if (!list_data.isEmpty()){
            type=list_data.get(position)!=null?1:VIEW_FOOTER;
        }else {
            type=VIEW_NULL;
        }

        return type;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=null;
        RecyclerView.ViewHolder holder;
        switch (viewType){
            case VIEW_TOP:
                topHolder=addViewAdapterListener.addTopView(inflater,parent,view);
                holder=topHolder;
                break;
            case VIEW_ITEM1:
                firstHolder=addViewAdapterListener.addFirstView(inflater,parent,view);
                holder=firstHolder;
                break;
            case VIEW_ITEM2:
                secondHolder=addViewAdapterListener.addSecondView(inflater,parent,view);
                holder=secondHolder;
                break;
            case VIEW_FOOTER:
                footerHolder = new MyProgressViewHolder(inflater.inflate(R.layout.item_footer, parent, false));
                holder=footerHolder;
                break;
            default:
                NullHolder = new NullViewHolder(inflater.inflate(R.layout.null_layout, parent, false));
                holder=NullHolder;
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
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
                if (((MyProgressViewHolder) holder).pb != null)
                    ((MyProgressViewHolder) holder).pb.setIndeterminate(true);
                break;
            case VIEW_NULL:
                ((NullViewHolder)holder).btn_null.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addNullListener.nullLayoutListener(v);
                    }
                });
                break;
        }
    }

    public void setLoaded(boolean loaded) {
        isLoading = loaded;
    }

    @Override
    public int getItemCount() {
        return list_data.isEmpty()?1:list_data.size();
    }

    public class MyProgressViewHolder extends RecyclerView.ViewHolder {

        private final ProgressBar pb;

        public MyProgressViewHolder(View itemView) {
            super(itemView);
            pb = (ProgressBar) itemView.findViewById(R.id.pb);
        }

    }

    public class NullViewHolder extends RecyclerView.ViewHolder {

        private Button btn_null;

        public NullViewHolder(View itemView) {
            super(itemView);
            btn_null= (Button) itemView.findViewById(R.id.btn_null);
        }

    }

    private AddViewAdapterListener addViewAdapterListener;

    public interface AddViewAdapterListener{
        RecyclerView.ViewHolder addFirstView(LayoutInflater inflater,ViewGroup parent,View view);
        RecyclerView.ViewHolder addSecondView(LayoutInflater inflater,ViewGroup parent,View view);
        RecyclerView.ViewHolder addTopView(LayoutInflater inflater,ViewGroup parent,View view);
    }

    public void setAddViewAdapterListener(AddViewAdapterListener addViewAdapterListener) {
        this.addViewAdapterListener = addViewAdapterListener;
    }

    private AddNullListener addNullListener;
    public interface AddNullListener {
        void nullLayoutListener(View view);
    }
    public void setAddNullListener(AddNullListener addNullListener) {
        this.addNullListener = addNullListener;
    }

    private AddHolderListener addHolderListener;

    public interface AddHolderListener{
        void addListener(RecyclerView.ViewHolder holder, int position);
    }

    public void setAddHolderListener(AddHolderListener addHolderListener) {
        this.addHolderListener = addHolderListener;
    }

    private LoadMoreDataListener mMoreDataListener;

    //加载更多监听方法
    public void setOnMoreDataLoadListener(LoadMoreDataListener onMoreDataLoadListener) {
        mMoreDataListener = onMoreDataLoadListener;
    }
}
