package com.example.jhon.venue.Interface;

/**
 * Created by John on 2017/3/14.
 */

public interface HttpJudgement {

    void onSuccess(String response);
    void onFailed(Exception e);
}
