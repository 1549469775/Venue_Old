package com.example.jhon.venue.Activity;

import android.content.Intent;
import android.icu.util.MeasureUnit;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.example.jhon.venue.R;

/**
 * Created by John on 2017/3/19.
 */

public class ShowActivity extends AppCompatActivity{

    private ImageView img_show;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.show_activity);
        /*
        * 为了让dialog宽度铺满
        * */
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        Intent intent=getIntent();
        String path=intent.getStringExtra("showImg");
        img_show=(ImageView)findViewById(R.id.img_show);
        Glide.with(this).load(path).centerCrop().into(img_show);
    }
}
