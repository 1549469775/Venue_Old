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
    private LoginListener listener;
    private LoginOperation operation;

    private ViewGroup view;
    private LinearLayout aaa;
    private TextView bbb,ccc;


    public LoginAction(Context context, LoginListener listener) {
        this.context = context;
        this.listener = listener;
        this.operation = new LoginOperation();

        this.view=listener.getLoadView();
        aaa= (LinearLayout) view.findViewById(R.id.aaa);
        bbb= (TextView) view.findViewById(R.id.bbb);
        ccc= (TextView) view.findViewById(R.id.ccc);
    }

    public void login(final JudgeInterface listener){

//        TransitionManager.beginDelayedTransition(view);
        aaa.setVisibility(View.VISIBLE);
        ccc.setVisibility(View.GONE);
        bbb.setVisibility(View.GONE);

        operation.startLogin(this.listener.getUser(), new JudgeInterface() {
            @Override
            public void onSuccess() {
                aaa.setVisibility(View.GONE);
                ccc.setVisibility(View.GONE);
                bbb.setVisibility(View.VISIBLE);
                bbb.setWidth(view.getWidth());
                listener.onSuccess();
            }

            @Override
            public void onError(Exception e) {
                aaa.setVisibility(View.GONE);
                bbb.setVisibility(View.GONE);
                ccc.setVisibility(View.VISIBLE);
                ccc.setWidth(view.getWidth());
                listener.onError(e);
            }
        });
    }

    public void register(final JudgeInterface listener){

        aaa.setVisibility(View.VISIBLE);
        ccc.setVisibility(View.GONE);
        bbb.setVisibility(View.GONE);

        operation.startRegister(this.listener.getUser(), new JudgeInterface() {
            @Override
            public void onSuccess() {

                aaa.setVisibility(View.GONE);
                ccc.setVisibility(View.GONE);
                bbb.setVisibility(View.VISIBLE);
                bbb.setWidth(view.getWidth());
                listener.onSuccess();
            }

            @Override
            public void onError(Exception e) {
//                TransitionManager.beginDelayedTransition(view);
                aaa.setVisibility(View.GONE);
                bbb.setVisibility(View.GONE);
                ccc.setVisibility(View.VISIBLE);
                ccc.setWidth(view.getWidth());
                listener.onError(e);
            }
        });
    }
}
