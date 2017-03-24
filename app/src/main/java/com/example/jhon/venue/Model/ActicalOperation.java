package com.example.jhon.venue.Model;

import android.util.Log;

import com.example.jhon.venue.Bean.ActicalListUtil;
import com.example.jhon.venue.Bean.Article;
import com.example.jhon.venue.Interface.JudgeInterface;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobDate;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by John on 2017/3/19.
 */

public class ActicalOperation {

    public static List<Article> list;

    public static void getActical(final JudgeInterface listener){
        BmobQuery<Article> bmobQuery=new BmobQuery<>();
        bmobQuery.setLimit(10);//限制10条作为一页
//        bmobQuery.setSkip(10);//忽略前10条数据
        bmobQuery.findObjects(new FindListener<Article>() {
            @Override
            public void done(List<Article> list, BmobException e) {
                if (e==null){
                    if (list!=null){
                        Log.d("xyyy",list.get(0).getObjectId());
                        ActicalOperation.list=list;
                    }
                    listener.onSuccess();
                    Log.d("xyxxxxxxx","success");
                }else {
                    listener.onError(e);
                }
            }
        });
    }

    public static void getActicalPage(int page,final JudgeInterface listener){
//        Date data=null;
//        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        try {
//            data=sdf.parse("2017-03-24 15:31:00");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        BmobQuery<Article> bmobQuery=new BmobQuery<>();
        bmobQuery.order("-createdAt");
//        bmobQuery.addWhereLessThanOrEqualTo("createAt",new BmobDate(data));
        bmobQuery.setLimit(10);//限制10条作为一页
//        bmobQuery.setSkip(10*(page-1)+1);//忽略前10条数据
        bmobQuery.findObjects(new FindListener<Article>() {
            @Override
            public void done(List<Article> list, BmobException e) {
                if (e==null){
                    if (list!=null){
                        for (int i=0;i<list.size();i++){
                            Log.d("xyyy",list.get(i).getTitle());
                        }
                        ActicalOperation.list=list;
                    }
                    listener.onSuccess();
                    Log.d("xyxxxxxxx","success");
                }else {
                    listener.onError(e);
                }
            }
        });
    }
}
