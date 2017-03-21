package com.example.jhon.venue.Model;

import android.util.Log;

import com.example.jhon.venue.Bean.Article;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by John on 2017/3/19.
 */

public class ActicalOperation {

    public static void getActical(){
        BmobQuery<Article> bmobQuery=new BmobQuery<>();
        bmobQuery.setLimit(10);//限制10条作为一页
        bmobQuery.setSkip(10);//忽略前10条数据
        bmobQuery.findObjects(new FindListener<Article>() {
            @Override
            public void done(List<Article> list, BmobException e) {
                if (e==null){
                    Log.d("xyxxxxxxx","querysuccess");
                    for (int i=0;i<list.size();i++){
                        Log.d("xyxxxxxxx",list.get(i).getObjectId());
                    }
                }else {
                    Log.d("xyxxxxxxx","querysfailed");
                    Log.d("xyxxxxxxx",e.getMessage());
                }
            }
        });
    }
}
