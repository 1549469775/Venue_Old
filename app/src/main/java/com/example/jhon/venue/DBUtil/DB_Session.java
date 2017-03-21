package com.example.jhon.venue.DBUtil;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.example.jhon.venue.Bean.Article;
import com.example.jhon.venue.Bean.ArticleDao;
import com.example.jhon.venue.Bean.DaoSession;
import com.example.jhon.venue.MyApplication;

import org.greenrobot.greendao.query.Query;

/**
 * Created by John on 2017/3/21.
 */

public class DB_Session {

    private static DaoSession daoSession;

    private static ArticleDao articleDao;
    private static Query<Article> articleQuery;


    public static ArticleDao getSession(Context context){
        if (daoSession==null) {
            daoSession = ((MyApplication) context.getApplicationContext()).getDaoSession();//数据库会话
        }
        articleDao = daoSession.getArticleDao();//获取一个note的数据
        //然後活動和片段可以調用getDaoSession（）來訪問所有實體DAO，如上所述，當插入和刪除註釋時。
        return articleDao;

    }

    public static Query<Article> getDbArticleQuery(){
        // query all notes, sorted a-z by their text查詢所有筆記，按其文本對a-z排序
        articleQuery = articleDao.queryBuilder().orderAsc(ArticleDao.Properties.Id).build();
        return articleQuery;
    }
}
