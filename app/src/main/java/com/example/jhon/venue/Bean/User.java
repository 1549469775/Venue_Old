package com.example.jhon.venue.Bean;

import cn.bmob.v3.BmobUser;

/**
 * Created by John on 2017/3/14.
 */

public class User extends BmobUser{

    private String nickname;
    public boolean isLogin=false;
    private String imagePath="defult";

//    public User(String nickname, String username, String password) {
//        this.nickname = nickname;
//        this.username = username;
//        this.password = password;
//    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
