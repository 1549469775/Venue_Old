package com.example.jhon.venue.Fragment.FoundFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jhon.venue.Adapter.Found_Topic_RV_Adapter;
import com.example.jhon.venue.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by John on 2017/3/10.
 */

public class FoundFragment extends Fragment {

    private View view;

    private TextView tv_found_topic_more;
    private RecyclerView rv_found_topic_hot;
    private Found_Topic_RV_Adapter adapter;

    private List<String> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.found_main,container,false);
        initView();
        return view;
    }

    private void initView() {
        tv_found_topic_more= (TextView) view.findViewById(R.id.tv_found_topic_more);
        tv_found_topic_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,""+v.getId(),Snackbar.LENGTH_SHORT).show();
            }
        });

        rv_found_topic_hot = (RecyclerView) view.findViewById(R.id.rv_found_topic_hot);
        rv_found_topic_hot.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));//此处true为recycleview将倒着滑
        list=new ArrayList<>();
        list=new ArrayList<>();
        for (int i=1;i<11;i++){
            list.add(""+i);
        }
        adapter=new Found_Topic_RV_Adapter(getContext(),list);
        rv_found_topic_hot.setAdapter(adapter);
        adapter.setOnItemClickListener(new Found_Topic_RV_Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if ((boolean)view.getTag()){
                    Snackbar.make(view,""+view.getTag(),Snackbar.LENGTH_SHORT).show();
                }else {
                    Snackbar.make(view,""+view.getTag(),Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }

    public static FoundFragment newInstance() {
        
        Bundle args = new Bundle();
        
        FoundFragment fragment = new FoundFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
