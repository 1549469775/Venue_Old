package com.example.jhon.venue.Bean;

/**
 * Created by John on 2017/3/7.
 */

public class Icon {

    private int iId;
    private String iName;

    public Icon(int iId, String iName) {
        this.iId = iId;
        this.iName = iName;
    }
    public Icon(String iName) {
        this.iName = iName;
    }

    public int getiId() {
        return iId;
    }

    public void setiId(int iId) {
        this.iId = iId;
    }

    public String getiName() {
        return iName;
    }

    public void setiName(String iName) {
        this.iName = iName;
    }
}
