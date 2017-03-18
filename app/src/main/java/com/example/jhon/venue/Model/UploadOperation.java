package com.example.jhon.venue.Model;

import android.util.Log;

import com.example.jhon.venue.Interface.JudgeInterface;

import java.io.File;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UploadFileListener;

/**
 * Created by John on 2017/3/14.
 */

public class UploadOperation {

    public static String imagePath;

    public static void uploadFile(String path, final JudgeInterface listener){
        File file=new File(path);
        if (file.exists()){
            final BmobFile update=new BmobFile(file);
            update.setUrl(path);
            update.upload(new UploadFileListener() {
                @Override
                public void done(BmobException e) {
                    if (e==null){
                        imagePath=update.getFileUrl();
                        Log.d("xyxyyyy",imagePath);
                        listener.onSuccess();
                    }else {
                        listener.onError(e);
                    }
                }
            });
        }else {
            listener.onError(new Exception("不存在此文件"));
        }
    }

}
