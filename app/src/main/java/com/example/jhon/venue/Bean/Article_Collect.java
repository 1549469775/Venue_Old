package com.example.jhon.venue.Bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

/**
 * Created by John on 2017/3/21.
 */
@Entity
public class Article_Collect {

    @Id
    public Long id;
    @NotNull//不为空
    private String title="default";
    private String imagePath="default";
    @NotNull//不为空
    private String note="default";
    private int praise;
    @NotNull
    private String type;
    private double latitude=0;
    private double longtitude=0;
    private String address="default";
    @NotNull//不为空
    private String one_objectId="default";
    @NotNull//不为空
    private String one_nickname="default";
    @NotNull//不为空
    private String one_imagePath="default";
    @Generated(hash = 93273998)
    public Article_Collect(Long id, @NotNull String title, String imagePath,
            @NotNull String note, int praise, @NotNull String type, double latitude,
            double longtitude, String address, @NotNull String one_objectId,
            @NotNull String one_nickname, @NotNull String one_imagePath) {
        this.id = id;
        this.title = title;
        this.imagePath = imagePath;
        this.note = note;
        this.praise = praise;
        this.type = type;
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.address = address;
        this.one_objectId = one_objectId;
        this.one_nickname = one_nickname;
        this.one_imagePath = one_imagePath;
    }
    @Generated(hash = 1100654203)
    public Article_Collect() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getImagePath() {
        return this.imagePath;
    }
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    public String getNote() {
        return this.note;
    }
    public void setNote(String note) {
        this.note = note;
    }
    public int getPraise() {
        return this.praise;
    }
    public void setPraise(int praise) {
        this.praise = praise;
    }
    public String getType() {
        return this.type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public double getLatitude() {
        return this.latitude;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    public double getLongtitude() {
        return this.longtitude;
    }
    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }
    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getOne_objectId() {
        return this.one_objectId;
    }
    public void setOne_objectId(String one_objectId) {
        this.one_objectId = one_objectId;
    }
    public String getOne_nickname() {
        return this.one_nickname;
    }
    public void setOne_nickname(String one_nickname) {
        this.one_nickname = one_nickname;
    }
    public String getOne_imagePath() {
        return this.one_imagePath;
    }
    public void setOne_imagePath(String one_imagePath) {
        this.one_imagePath = one_imagePath;
    }
}
