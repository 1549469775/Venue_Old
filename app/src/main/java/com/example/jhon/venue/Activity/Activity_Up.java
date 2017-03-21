package com.example.jhon.venue.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.example.jhon.venue.Bean.Article;
import com.example.jhon.venue.Bean.LocationData;
import com.example.jhon.venue.Bean.UserUtil;
import com.example.jhon.venue.DBUtil.DB_Session;
import com.example.jhon.venue.Interface.JudgeInterface;
import com.example.jhon.venue.Interface.UpListener;
import com.example.jhon.venue.Preference.UpAction;
import com.example.jhon.venue.R;

/**
 * Created by John on 2017/3/14.
 */

public class Activity_Up extends AppCompatActivity implements UpListener {

    private static int RESULT_LOAD_IMAGE=1;

    private Switch switch_private;
    private EditText et_up_title,et_up_assent;
    private TextView tv_up_location,tv_up_location_data;
    private ImageView img_up_booktop;

    private String imagePath="default";
    private Article article =null;

    private UpAction action=new UpAction(this,this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up);

        Toolbar toolbar_up= (Toolbar) findViewById(R.id.toolbar_up);
        setSupportActionBar(toolbar_up);
        getSupportActionBar().setHomeButtonEnabled(true);//设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar_up.setTitle("发布");

        et_up_title= (EditText) findViewById(R.id.et_up_title);
        et_up_assent= (EditText) findViewById(R.id.et_up_assent);
        switch_private= (Switch) findViewById(R.id.switch_private);

        tv_up_location= (TextView) findViewById(R.id.tv_up_location);
        tv_up_location.setText("纬度："+ LocationData.Latitude+"  经度："+LocationData.longtitude);
        tv_up_location_data= (TextView) findViewById(R.id.tv_up_location_data);
        tv_up_location_data.setText(LocationData.address);

        img_up_booktop= (ImageView) findViewById(R.id.img_up_booktop);
        img_up_booktop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i,RESULT_LOAD_IMAGE);
            }
        });


    }

    //提交
    public void submit(final View view){
        if (UserUtil.getUser().isLogin){
            if (article ==null){
                article =new Article();
                article.setTitle(et_up_title.getText().toString());
                article.setOne_imagePath(UserUtil.getUser().getImagePath());
                article.setOne_nickname(UserUtil.getUser().getNickname());
                article.setOne_objectId(UserUtil.getUser().getObjectId());
                article.setImagePath(imagePath);
                article.setNote(et_up_assent.getText().toString());
                article.setLatitude(LocationData.Latitude);
                article.setLongtitude(LocationData.longtitude);
                article.setAddress(LocationData.address);
            }
            action.submit(new JudgeInterface() {
                @Override
                public void onSuccess() {Log.d("xyx","hssit");
                    article=null;//为了重新创建一个article
                    Snackbar.make(view,"yiaSuccess",Snackbar.LENGTH_SHORT).show();
                }

                @Override
                public void onError(Exception e) {
                    Log.d("xyx","herre");
                    Log.d("xyx",e.getMessage());
                    Snackbar.make(view,e.getMessage(),Snackbar.LENGTH_SHORT).show();
                }
            });
        }else {
            startActivity(new Intent(Activity_Up.this,LoginActivity.class));
            finish();
        }

    }

    public void switch_text(View view){
        if (article ==null){
            article =new Article();
        }
        if (((Switch) view).isChecked()){
            article.setTitle(et_up_title.getText().toString());
            article.setOne_imagePath(UserUtil.getUser().getImagePath());
            article.setOne_nickname(UserUtil.getUser().getNickname());
            article.setOne_objectId(UserUtil.getUser().getObjectId());
            article.setImagePath(imagePath);
            article.setNote(et_up_assent.getText().toString());
            article.setLatitude(LocationData.Latitude);
            article.setLongtitude(LocationData.longtitude);
            article.setAddress(LocationData.address);
        }else {
            article.setTitle(et_up_title.getText().toString());
            article.setOne_imagePath(UserUtil.getUser().getImagePath());
            article.setOne_nickname(UserUtil.getUser().getNickname());
            article.setOne_objectId(UserUtil.getUser().getObjectId());
            article.setImagePath(imagePath);
            article.setNote(et_up_assent.getText().toString());
            article.setLatitude(0);
            article.setLongtitude(0);
            article.setAddress("default");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home){//返回键可用
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==RESULT_LOAD_IMAGE&&resultCode==RESULT_OK&&null!=data){
            Uri selectImage=data.getData();
            String[] filePathColumn={ MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectImage,filePathColumn,null,null,null);

            cursor.moveToFirst();
            int columnIndex=cursor.getColumnIndex(filePathColumn[0]);
            String picturePath=cursor.getString(columnIndex);
            cursor.close();

            imagePath=picturePath;
            Bitmap bitmap=BitmapFactory.decodeFile(picturePath);

            img_up_booktop.setImageBitmap(bitmap);
            img_up_booktop.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,bitmap.getHeight()));
        }
    }

    public Article getArticle() {
        return article;
    }
}
