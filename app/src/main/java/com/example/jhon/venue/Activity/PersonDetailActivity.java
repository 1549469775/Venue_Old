package com.example.jhon.venue.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jhon.venue.Adapter.PersonDetail_RV_Adapter;
import com.example.jhon.venue.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by John on 2017/3/13.
 */

public class PersonDetailActivity extends AppCompatActivity implements PersonDetail_RV_Adapter.AddViewAdapterListener, PersonDetail_RV_Adapter.AddHolderListener {

    private RecyclerView rvPersondetail;
    private PersonDetail_RV_Adapter personDetail_rv_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.persondetail_main);

        Toolbar toolbar_up= (Toolbar) findViewById(R.id.toolbar_persondetail);
        setSupportActionBar(toolbar_up);
        getSupportActionBar().setHomeButtonEnabled(true);//设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar_up.setTitle("个人");

        rvPersondetail = (RecyclerView) findViewById(R.id.rv_persondetail);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        rvPersondetail.setLayoutManager(linearLayoutManager);
        rvPersondetail.setHasFixedSize(true);

        List<String> a=new ArrayList<>();
        a.add("sda");
        a.add("sda");
        a.add("sda");
        List<String> b=new ArrayList<>();

        Log.d("sas","sasdasds");/*数据包括日期，封面，标题*/
        personDetail_rv_adapter=new PersonDetail_RV_Adapter(this,b,a);
        personDetail_rv_adapter.setAddViewAdapterListener(this);
        personDetail_rv_adapter.setAddHolderListener(this);

        rvPersondetail.setAdapter(personDetail_rv_adapter);
    }

    @Override
    public RecyclerView.ViewHolder addTimeView(ViewGroup viewGroup, View view) {
        view= LayoutInflater.from(this).inflate(R.layout.persondetail_time,viewGroup,false);
        return new TimeAdapter(view);
    }

    @Override
    public void addListener(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TimeAdapter){

        }
    }

    public class TimeAdapter extends RecyclerView.ViewHolder{

        public TimeAdapter(View itemView) {
            super(itemView);
        }
    }
}
