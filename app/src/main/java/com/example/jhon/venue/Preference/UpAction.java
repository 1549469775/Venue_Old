package com.example.jhon.venue.Preference;

import android.content.Context;
import android.util.Log;

import com.example.jhon.venue.Interface.JudgeInterface;
import com.example.jhon.venue.Interface.UpListener;
import com.example.jhon.venue.Model.UpOperation;
import com.example.jhon.venue.UI.UIProgressDialog;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by John on 2017/3/19.
 */

public class UpAction {

    private Context context;
    private UpListener listener;
    private UpOperation upOperation;

    public UpAction(Context context, UpListener listener) {
        this.context = context;
        this.listener = listener;
        upOperation=new UpOperation();
    }

    public void submit(final JudgeInterface lis){
        UIProgressDialog.showProgress(context,"上传中");
        listener.getActical().save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e==null){
                    UIProgressDialog.closeProgress();
                    lis.onSuccess();
                }else {
                    UIProgressDialog.closeProgress();
                    lis.onError(e);
                }
            }
        });
    }
}
