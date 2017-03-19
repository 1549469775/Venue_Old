package com.example.jhon.venue.Bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by John on 2017/3/19.
 */

public class Actical extends BmobObject {

    private String title="default";
    private String imagePath="default";
    private String note="default";
    private double latitude=0;
    private double longtitude=0;
    private String address="default";
    private String one_objectId="default";
    private String one_nickname="default";
    private String one_imagePath="default";

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOne_objectId() {
        return one_objectId;
    }

    public void setOne_objectId(String one_objectId) {
        this.one_objectId = one_objectId;
    }

    public String getOne_nickname() {
        return one_nickname;
    }

    public void setOne_nickname(String one_nickname) {
        this.one_nickname = one_nickname;
    }

    public String getOne_imagePath() {
        return one_imagePath;
    }

    public void setOne_imagePath(String one_imagePath) {
        this.one_imagePath = one_imagePath;
    }
}
