package com.example.jhon.venue.Model;

import android.support.design.widget.Snackbar;
import android.util.Log;
import android.widget.TableRow;

import com.example.jhon.venue.Bean.Loc;
import com.example.jhon.venue.Bean.User;
import com.example.jhon.venue.Bean.UserUtil;
import com.example.jhon.venue.Bean.VenueAPI;
import com.example.jhon.venue.Http.HttpPost;
import com.example.jhon.venue.Interface.HttpJudgement;
import com.example.jhon.venue.Interface.JudgeInterface;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

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

    public void bmobLogin(String username, String password, final JudgeInterface listener) {
        BmobUser user = new BmobUser();
        user.setUsername(username);
        user.setPassword(password);
        user.login(new SaveListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if (e == null) {
                    if (user!=null){
                        Log.d("xyx",user.getImagePath()+"sdasd");
                        Log.d("xyx",user.getNickname()+"sdasd");
                        user.isLogin= true;
                        UserUtil.setUser(user);
                    }
                    listener.onSuccess();
                } else {
                    listener.onError(e);
                }
            }
        });
    }
    public void bmobRegister(String username,String password,String nickname, final JudgeInterface listener) {
        User user=new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setNickname(nickname);
        user.signUp(new SaveListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if (e==null){
                   listener.onSuccess();
                }else {
                    listener.onError(e);
                }
            }
        });
    }

}
