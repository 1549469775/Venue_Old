package com.example.jhon.venue.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LayoutAnimationController;
import android.widget.EditText;

import com.example.jhon.venue.Bean.User;
import com.example.jhon.venue.Interface.JudgeInterface;
import com.example.jhon.venue.Interface.LoginListener;
import com.example.jhon.venue.Preference.LoginAction;
import com.example.jhon.venue.R;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by John on 2017/3/14.
 */

public class LoginActivity extends AppCompatActivity implements LoginListener{

    private EditText register_nickname, register_confrom,login_email,login_password;
    private boolean isLogin = true, isRegister = false;
    private LoginAction action;

    private User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        action=new LoginAction(this,this);

        init();

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void init(){
        register_nickname = (EditText) findViewById(R.id.register_nickname);
        register_confrom = (EditText) findViewById(R.id.register_confrom);
        login_email= (EditText) findViewById(R.id.login_email);
        login_password= (EditText) findViewById(R.id.login_password);
    }

    private void initText(){
        register_nickname.setText("");
        register_confrom.setText("");
//        login_email.setText("");
//        login_password.setText("");
    }

    public boolean checkIsEmpty(int type){
        if (type==0){
            if (TextUtils.isEmpty(register_nickname.getText().toString())||
                    TextUtils.isEmpty(register_confrom.getText().toString())||
                    TextUtils.isEmpty(login_email.getText().toString())||
                    TextUtils.isEmpty(login_password.getText().toString())){
                return false;
            }else
                return true;
        }else {
            if (TextUtils.isEmpty(login_email.getText().toString())||
                    TextUtils.isEmpty(login_password.getText().toString())){
                return false;
            }else
                return true;
        }
    }

    public void loginOperation(final View view) {
        Log.d("xyx",login_email.getText().toString());
        final View vie=view;
        isRegister = false;
        etSetVisibility(1);
        if (isLogin) {
            if (checkIsEmpty(1)){
                action.login(new JudgeInterface() {
                    @Override
                    public void onSuccess() {
                        Snackbar.make(vie,"Success",Snackbar.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Exception e) {
                        Log.d("xyx",e.getMessage());
                        Snackbar.make(vie,e.getMessage(),Snackbar.LENGTH_SHORT).show();
                    }
                });
            }else
                Snackbar.make(view,"请全部填写信息",Snackbar.LENGTH_SHORT).show();
        }else {
            initText();
        }
        isLogin = true;
    }

    private void etSetVisibility(int type){
        TransitionManager.beginDelayedTransition((ViewGroup) findViewById(R.id.login_root));
        if (type==0){
            register_nickname.setVisibility(View.VISIBLE);
            register_confrom.setVisibility(View.VISIBLE);
        }else {
            register_nickname.setVisibility(View.GONE);
            register_confrom.setVisibility(View.GONE);
        }
    }

    public void registerOperation(View view) {
        final View vie=view;
        isLogin = false;
        etSetVisibility(0);
        if (isRegister) {
            if (checkIsEmpty(0)){
                if (TextUtils.equals(login_password.getText().toString(),register_confrom.getText().toString())) {
                        action.register(new JudgeInterface() {
                            @Override
                            public void onSuccess() {
                                Snackbar.make(vie, "Success", Snackbar.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onError(Exception e) {
                                Snackbar.make(vie, e.getMessage(), Snackbar.LENGTH_SHORT).show();
                            }
                        });
                }else {
                    Snackbar.make(view,"两次输入的密码不同",Snackbar.LENGTH_SHORT).show();
                }
            }else {
                Snackbar.make(view,"请全部填写信息",Snackbar.LENGTH_SHORT).show();
            }
        }else {
            initText();
        }
        isRegister = true;
    }


    @Override
    public String getUsername() {
        return login_email.getText().toString();
    }

    @Override
    public String getPassword() {
        return login_password.getText().toString();
    }

    @Override
    public String getNickname() {
        return register_nickname.getText().toString();
    }

    @Override
    public void startToActivity() {
        finish();
    }
}
