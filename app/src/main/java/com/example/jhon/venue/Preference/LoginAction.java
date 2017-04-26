package com.example.jhon.venue.Preference;

import android.content.Context;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.jhon.venue.Bean.User;
import com.example.jhon.venue.Interface.JudgeInterface;
import com.example.jhon.venue.Interface.LoginListener;
import com.example.jhon.venue.Model.LoginOperation;
import com.example.jhon.venue.R;
import com.example.jhon.venue.UI.UIProgressDialog;

/**
 * Created by John on 2017/3/14.
 */

public class LoginAction {

    private Context context;
    private LoginListener listeners;
    private LoginOperation operation;

    public LoginAction(Context context, LoginListener listener) {
        this.context = context;
        this.listeners = listener;
        this.operation = new LoginOperation();

    }

    public void login(final JudgeInterface listener){
        UIProgressDialog.showProgress(context,"登陆中。。。");
        operation.bmobLogin(this.listeners.getUsername(),this.listeners.getPassword(), new JudgeInterface() {
            @Override
            public void onSuccess() {
                UIProgressDialog.closeProgress();
                listener.onSuccess();
                listeners.startToActivity();
            }

            @Override
            public void onError(Exception e) {
                UIProgressDialog.closeProgress();
                listener.onError(e);
            }
        });
//        TransitionManager.beginDelayedTransition(view);
    }

    public void register(final JudgeInterface listener){
        UIProgressDialog.showProgress(context,"注册中。。。");
        operation.bmobRegister(this.listeners.getUsername(),this.listeners.getPassword(), this.listeners.getNickname(), new JudgeInterface() {
            @Override
            public void onSuccess() {
                UIProgressDialog.closeProgress();
                listener.onSuccess();
            }

            @Override
            public void onError(Exception e) {
                UIProgressDialog.closeProgress();
                listener.onError(e);
            }
        });
////                TransitionManager.beginDelayedTransition(view);
    }
}
