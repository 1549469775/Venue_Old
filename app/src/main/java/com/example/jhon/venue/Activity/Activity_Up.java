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
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.example.jhon.venue.Bean.Actical;
import com.example.jhon.venue.Bean.ActicalListUtil;
import com.example.jhon.venue.Bean.LocationData;
import com.example.jhon.venue.Bean.User;
import com.example.jhon.venue.Bean.UserUtil;
import com.example.jhon.venue.Interface.JudgeInterface;
import com.example.jhon.venue.Interface.UpListener;
import com.example.jhon.venue.Map.Location;
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
    private Actical actical=null;

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
        if (actical==null){
            actical=new Actical();
            actical.setTitle(et_up_title.getText().toString());
            actical.setOne_imagePath(UserUtil.getUser().getImagePath());
            actical.setOne_nickname(UserUtil.getUser().getNickname());
            actical.setOne_objectId(UserUtil.getUser().getObjectId());
            actical.setImagePath(imagePath);
            actical.setNote(et_up_assent.getText().toString());
            actical.setLatitude(LocationData.Latitude);
            actical.setLongtitude(LocationData.longtitude);
            actical.setAddress(LocationData.address);
        }
        action.submit(new JudgeInterface() {
            @Override
            public void onSuccess() {
                Snackbar.make(view,"Success",Snackbar.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Exception e) {
                Snackbar.make(view,e.getMessage(),Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    public void switch_text(View view){
        if (actical==null){
            actical=new Actical();
        }
        if (((Switch) view).isChecked()){
            actical.setTitle(et_up_title.getText().toString());
            actical.setOne_imagePath(UserUtil.getUser().getImagePath());
            actical.setOne_nickname(UserUtil.getUser().getNickname());
            actical.setOne_objectId(UserUtil.getUser().getObjectId());
            actical.setImagePath(imagePath);
            actical.setNote(et_up_assent.getText().toString());
            actical.setLatitude(LocationData.Latitude);
            actical.setLongtitude(LocationData.longtitude);
            actical.setAddress(LocationData.address);
        }else {
            actical.setTitle(et_up_title.getText().toString());
            actical.setOne_imagePath(UserUtil.getUser().getImagePath());
            actical.setOne_nickname(UserUtil.getUser().getNickname());
            actical.setOne_objectId(UserUtil.getUser().getObjectId());
            actical.setImagePath(imagePath);
            actical.setNote(et_up_assent.getText().toString());
            actical.setLatitude(0);
            actical.setLongtitude(0);
            actical.setAddress("default");
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

    @Override
    public Actical getActical() {
        return actical;
    }
}
