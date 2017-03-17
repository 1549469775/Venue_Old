package com.example.jhon.venue.Model;

import android.util.Log;

import com.example.jhon.venue.Bean.User;
import com.example.jhon.venue.Bean.VenueAPI;
import com.example.jhon.venue.Http.HttpPost;
import com.example.jhon.venue.Interface.HttpJudgement;
import com.example.jhon.venue.Interface.JudgeInterface;

/**
 * Created by John on 2017/3/14.
 */

public class LoginOperation {

    public void startLogin(final User user, final JudgeInterface listener){
        String url = VenueAPI.loginUrl;
        HttpPost.sendResponse(url, user, new HttpJudgement() {
            @Override
            public void onSuccess(String response) {
                //解析
                listener.onSuccess();

            }
            @Override
            public void onFailed(Exception e) {
                //失败
                listener.onError(e);
            }
        });
    }

    public void startRegister(User user, final JudgeInterface listener){

        //判断密码
        String url = VenueAPI.registerUrl;
        HttpPost.sendResponse(url, user, new HttpJudgement() {
            @Override
            public void onSuccess(String response) {
                //解析
                listener.onSuccess();
            }
            @Override
            public void onFailed(Exception e) {
                //失败
                listener.onError(e);
            }
        });
    }

}
