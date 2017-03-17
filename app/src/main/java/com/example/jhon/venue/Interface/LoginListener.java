package com.example.jhon.venue.Interface;

import android.view.ViewGroup;

import com.example.jhon.venue.Bean.User;

/**
 * Created by John on 2017/3/14.
 */

public interface LoginListener {

    ViewGroup getLoadView();
    User getUser();
    void startToActivity(Class c);

}
