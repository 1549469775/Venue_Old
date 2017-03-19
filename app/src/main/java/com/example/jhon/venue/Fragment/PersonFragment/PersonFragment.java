package com.example.jhon.venue.Fragment.PersonFragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jhon.venue.Activity.LoginActivity;
import com.example.jhon.venue.Activity.ShowActivity;
import com.example.jhon.venue.Adapter.Person_RV_Adapter;
import com.example.jhon.venue.Bean.Icon;
import com.example.jhon.venue.Bean.User;
import com.example.jhon.venue.Bean.UserUtil;
import com.example.jhon.venue.Interface.JudgeInterface;
import com.example.jhon.venue.Model.UploadOperation;
import com.example.jhon.venue.R;
import com.example.jhon.venue.UI.UIProgressDialog;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

import static android.R.attr.bitmap;
import static android.app.Activity.RESULT_OK;

/**
 * Created by John on 2017/3/7.
 */

public class PersonFragment extends Fragment {

    private View view;

    private static int RESULT_LOAD_IMAGE=1;

    private RecyclerView rv_person;
    private List<Integer> list;
    private List<Icon> list_icon;
    private List<Icon> list_icon_bottom;

    private ImageView img_person;
    private TextView tv_person_name;

    public static PersonFragment newInstance() {

        Bundle args = new Bundle();

        PersonFragment fragment = new PersonFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.person_main,container,false);

        initView();

        return view;
    }

    private void initView() {

        rv_person= (RecyclerView) view.findViewById(R.id.rv_person);
        rv_person.setLayoutManager(new LinearLayoutManager(getContext()));
        list=new ArrayList<>();
        list.add(0);
        list.add(1);
        list.add(2);
        list_icon=new ArrayList<>();
        list_icon.add(new Icon(R.drawable.ic_found,"我的关注"));
        list_icon.add(new Icon(R.drawable.ic_found,"我的收藏"));
        list_icon.add(new Icon("我的草稿"));
        list_icon.add(new Icon(R.drawable.ic_found,"遇见的人"));
        list_icon.add(new Icon(R.drawable.ic_found,"曾经的风雨"));
        list_icon_bottom=new ArrayList<>();
        list_icon_bottom.add(new Icon("我的关注"));
        list_icon_bottom.add(new Icon("我的收藏"));
        Person_RV_Adapter person_rv_adapter=new Person_RV_Adapter(getContext(),list,list_icon,list_icon_bottom);
        person_rv_adapter.setOnImgClickListener(new Person_RV_Adapter.OnImgClickListener() {
            @Override
            public void onImgClick(View view) {
                if (img_person==null&&tv_person_name==null){
                    img_person= (ImageView) view.findViewById(R.id.img_person);
                    tv_person_name= (TextView) view.findViewById(R.id.tv_person_name);
                }
                    switch (view.getId()){
                        case R.id.card_person_top:
                            if (!UserUtil.getUser().isLogin){
                                startActivity(new Intent(getContext(), LoginActivity.class));
                            }else {
                                Snackbar.make(view,"GoodJob"+UserUtil.getUser().getNickname(),Snackbar.LENGTH_SHORT).show();
                            }
                            break;
                        case R.id.img_person:
                            if (UserUtil.getUser().isLogin){
//                                showDialogForImg();
                                Intent i=new Intent(getContext(), ShowActivity.class);
                                i.putExtra("showImg",UserUtil.getUser().getImagePath());
                                startActivity(i);
                            }else {
                                Snackbar.make(view,"请先登录吧",Snackbar.LENGTH_SHORT).show();
                            }
                            break;
                    }
            }
        });
        person_rv_adapter.setOnLongClickListener(new Person_RV_Adapter.OnLongClickListener() {
            @Override
            public void onLongClick(View view) {
                if (UserUtil.getUser().isLogin){
                    showDialogForImg();
                }else {
                    Snackbar.make(view,"请先登录吧",Snackbar.LENGTH_SHORT).show();
                }
            }
        });
        rv_person.setAdapter(person_rv_adapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==RESULT_LOAD_IMAGE&&resultCode==RESULT_OK&&null!=data){
            Uri selectImage=data.getData();
            String[] filePathColumn={ MediaStore.Images.Media.DATA};

            Cursor cursor = getActivity().getContentResolver().query(selectImage,filePathColumn,null,null,null);

            cursor.moveToFirst();
            int columnIndex=cursor.getColumnIndex(filePathColumn[0]);
            final String picturePath=cursor.getString(columnIndex);
            cursor.close();

//            Bitmap bitmap= toRoundBitmap(BitmapFactory.decodeFile(picturePath));

            Snackbar.make(view,picturePath,Snackbar.LENGTH_SHORT).show();
//            img_person.setImageBitmap(bitmap);
            Log.d("xyxxx",picturePath);

            UIProgressDialog.showProgress(getContext(),"上传中。。。");
            UploadOperation.uploadFile(picturePath, new JudgeInterface() {
                @Override
                public void onSuccess() {
                    UserUtil.getUser().setImagePath(UploadOperation.imagePath);
                    UserUtil.getUser().update(UserUtil.getUser().getObjectId(), new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if (e==null){
//                                img_person.setImageBitmap(bitmap);
                                Glide.with(getContext()).load(picturePath).centerCrop().into(img_person);
                                UIProgressDialog.closeProgress();
                                Snackbar.make(view,"goodJob",Snackbar.LENGTH_SHORT).show();
                            }else {
                                UIProgressDialog.closeProgress();
                                Snackbar.make(view,e.getMessage(),Snackbar.LENGTH_SHORT).show();
                            }
                        }
                    });
                    Snackbar.make(view,"Success",Snackbar.LENGTH_SHORT).show();
                }

                @Override
                public void onError(Exception e) {
                    UIProgressDialog.closeProgress();
                    Snackbar.make(view,e.getMessage(),Snackbar.LENGTH_SHORT).show();
                }
            });
//            img_person.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,bitmap.getHeight()));
        }
    }

    private void showDialogForImg(){
        new AlertDialog.Builder(getContext())
                .setTitle("你要改变头像吗？")
                .setPositiveButton("是的", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(i,RESULT_LOAD_IMAGE);
                    }
                })
                .setNegativeButton("取消",null)
                .create()
                .show();
    }

    @Override
    public void onResume() {
        super.onResume();
        User user=UserUtil.getUser();
        if (user.isLogin){
            tv_person_name.setText(user.getNickname()+"");
            Glide.with(getContext()).load(user.getImagePath()).centerCrop().into(img_person);
        }
    }

    /**
     * 把bitmap转成圆形
     * */
    public Bitmap toRoundBitmap(Bitmap bitmap){
        int width=bitmap.getWidth();
        int height=bitmap.getHeight();
        int r=0;
        //取最短边做边长
        if(width<height){
            r=width;
        }else{
            r=height;
        }
        //构建一个bitmap
        Bitmap backgroundBm=Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
        //new一个Canvas，在backgroundBmp上画图
        Canvas canvas=new Canvas(backgroundBm);

        Paint p=new Paint();
        //设置边缘光滑，去掉锯齿
        p.setAntiAlias(true);
        RectF rect=new RectF(0, 0, r, r);
        //通过制定的rect画一个圆角矩形，当圆角X轴方向的半径等于Y轴方向的半径时，
        //且都等于r/2时，画出来的圆角矩形就是圆形
        canvas.drawRoundRect(rect, r/2, r/2, p);
        //设置当两个图形相交时的模式，SRC_IN为取SRC图形相交的部分，多余的将被去掉
        p.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        //canvas将bitmap画在backgroundBmp上
        canvas.drawBitmap(bitmap, null, rect, p);
        return backgroundBm;
    }
}
