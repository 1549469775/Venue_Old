package com.example.jhon.venue.Fragment.PersonFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jhon.venue.Adapter.Person_RV_Adapter;
import com.example.jhon.venue.Bean.Icon;
import com.example.jhon.venue.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by John on 2017/3/7.
 */

public class PersonFragment extends Fragment {

    private View view;

    private RecyclerView rv_person;
    private List<Integer> list;
    private List<Icon> list_icon;
    private List<Icon> list_icon_bottom;

    public static PersonFragment newInstance() {

        Bundle args = new Bundle();

        PersonFragment fragment = new PersonFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.person_main,container,false);

        initView();

        return view;
    }

    private void initView() {

        rv_person= (RecyclerView) view.findViewById(R.id.rv_person);
        rv_person.setLayoutManager(new LinearLayoutManager(getContext()));
        list=new ArrayList<>();
        list.add(0);
        list.add(1);
        list.add(2);
        list_icon=new ArrayList<>();
        list_icon.add(new Icon(R.drawable.ic_found,"我的关注"));
        list_icon.add(new Icon(R.drawable.ic_found,"我的收藏"));
        list_icon.add(new Icon("我的草稿"));
        list_icon.add(new Icon(R.drawable.ic_found,"遇见的人"));
        list_icon.add(new Icon(R.drawable.ic_found,"曾经的风雨"));
        list_icon_bottom=new ArrayList<>();
        list_icon_bottom.add(new Icon("我的关注"));
        list_icon_bottom.add(new Icon("我的收藏"));
        rv_person.setAdapter(new Person_RV_Adapter(getContext(),list,list_icon,list_icon_bottom));
    }

}
