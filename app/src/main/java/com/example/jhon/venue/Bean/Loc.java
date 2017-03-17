package com.example.jhon.venue.Bean;

import java.util.List;

/**
 * Created by John on 2017/3/15.
 */

public class Loc {

    private String isError;
    private List<Results> resultses;

    public String getIsError() {
        return isError;
    }

    public void setIsError(String isError) {
        this.isError = isError;
    }

    public List<Results> getResultses() {
        return resultses;
    }

    public void setResultses(List<Results> resultses) {
        this.resultses = resultses;
    }

    public static class Results{
        private double latitude;
        private double longtitude;
        private String title;

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

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
