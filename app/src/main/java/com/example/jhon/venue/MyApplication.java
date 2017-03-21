package com.example.jhon.venue;

import android.app.Application;

import com.example.jhon.venue.Bean.DaoMaster;
import com.example.jhon.venue.Bean.DaoSession;
import com.zhy.http.okhttp.OkHttpUtils;

import org.greenrobot.greendao.database.Database;
import com.example.jhon.venue.Bean.DaoMaster.DevOpenHelper;

import java.util.concurrent.TimeUnit;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobConfig;
import okhttp3.OkHttpClient;

/**
 * Created by John on 2017/3/11.
 */

public class MyApplication extends Application
{

    /** A flag to show how easily you can switch from standard SQLite to the encrypted SQLCipher. */
    public static final boolean ENCRYPTED = true;

    private DaoSession daoSession;

    @Override
    public void onCreate()
    {
        super.onCreate();

        //第二：自v3.4.7版本开始,设置BmobConfig,允许设置请求超时时间、文件分片上传时每片的大小、文件的过期时间(单位为秒)，
        BmobConfig config =new BmobConfig.Builder(this)
        ////设置appkey
        .setApplicationId("45a32fa48530d44ad453dc951c8c639f")
        ////请求超时时间（单位为秒）：默认15s
        .setConnectTimeout(5)
        ////文件分片上传时每片的大小（单位字节），默认512*1024
        .setUploadBlockSize(1024*1024)
        ////文件的过期时间(单位为秒)：默认1800s
        .setFileExpiration(2500)
        .build();
        Bmob.initialize(config);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);

        //数据库的配置
        DevOpenHelper helper = new DevOpenHelper(this, ENCRYPTED ? "venue-notes-db-encrypted" : "venue-notes-db");//前者为加密的，后者为不加密的
        Database db = ENCRYPTED ? helper.getEncryptedWritableDb("super-secret") : helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();

    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
