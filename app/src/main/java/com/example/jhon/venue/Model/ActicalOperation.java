package com.example.jhon.venue.Model;

import android.util.Log;

import com.example.jhon.venue.Bean.Actical;

import java.util.List;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by John on 2017/3/19.
 */

public class ActicalOperation {

    public static void getActical(){
        BmobQuery<Actical> bmobQuery=new BmobQuery<>();
        bmobQuery.setLimit(10);
        bmobQuery.findObjects(new FindListener<Actical>() {
            @Override
            public void done(List<Actical> list, BmobException e) {
                if (e==null){
                    Log.d("xyxxxxxxx","querysuccess");
                    for (int i=0;i<list.size();i++){
                        Log.d("xyxxxxxxx",list.get(i).getOne_nickname());
                    }
                }else {
                    Log.d("xyxxxxxxx","querysfailed");
                    Log.d("xyxxxxxxx",e.getMessage());
                }
            }
        });
    }
}
