package com.example.jhon.venue.Preference;

import android.content.Context;
import android.util.Log;

import com.example.jhon.venue.Activity.Activity_Up;
import com.example.jhon.venue.Bean.ActicalListUtil;
import com.example.jhon.venue.Bean.ArticleDao;
import com.example.jhon.venue.Bean.DaoSession;
import com.example.jhon.venue.DBUtil.DB_Session;
import com.example.jhon.venue.Interface.JudgeInterface;
import com.example.jhon.venue.Interface.UpListener;
import com.example.jhon.venue.Model.UpOperation;
import com.example.jhon.venue.UI.UIProgressDialog;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

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

    private ArticleDao articleDao;;

    public void submit(final JudgeInterface lis){
        Log.d("xyx","hit");
        UIProgressDialog.showProgress(context,"上传中");
        listener.getArticle().save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e==null){
                    if (articleDao==null){
                        articleDao=DB_Session.getSession(context);;
                    }
                    Log.d("xyx","herreasdasdad");
                    articleDao.insert(listener.getArticle());
                    for (int i=0;i<articleDao.queryBuilder().build().list().size();i++){
                        Log.d("xyx",articleDao.queryBuilder().build().list().get(i).getTitle());
                    }
                    ActicalListUtil.addList(listener.getArticle());

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
