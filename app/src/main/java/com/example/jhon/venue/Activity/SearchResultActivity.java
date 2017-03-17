package com.example.jhon.venue.Activity;

import android.app.SearchManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.jhon.venue.R;

/**
 * Created by John on 2017/3/10.
 */

public class SearchResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seach);
        String SearchContent=getIntent().getStringExtra(SearchManager.QUERY);
    }
}
