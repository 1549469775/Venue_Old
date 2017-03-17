package com.example.jhon.venue.Fragment.Message;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jhon.venue.Adapter.Message_RV_Adapter;
import com.example.jhon.venue.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by John on 2017/3/10.
 */

public class MessageFragment extends Fragment {

    private View view;

    private RecyclerView rv_message;
    private Message_RV_Adapter adapter;

    private List<String> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.message_main,container,false);
        initView();
        return view;
    }

    private void initView() {
        rv_message= (RecyclerView) view.findViewById(R.id.rv_message);
        rv_message.setLayoutManager(new LinearLayoutManager(getContext()));
        list=new ArrayList<>();
        list=new ArrayList<>();
        for (int i=1;i<11;i++){
            list.add(""+i);
        }
        adapter=new Message_RV_Adapter(getContext(),list);
        rv_message.setAdapter(adapter);
    }

    public static MessageFragment newInstance() {
        
        Bundle args = new Bundle();
        
        MessageFragment fragment = new MessageFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
