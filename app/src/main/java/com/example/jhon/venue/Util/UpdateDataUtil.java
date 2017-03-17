package com.example.jhon.venue.Util;

import com.example.jhon.venue.Bean.Imm;
import com.example.jhon.venue.Bean.ImmUtil;
import com.example.jhon.venue.Http.HttpGet;
import com.example.jhon.venue.Interface.JudgeInterface;
import com.example.jhon.venue.Interface.ParseJson;

/**
 * Created by John on 2017/3/13.
 */

public class UpdateDataUtil{


    public static void initData(String url, final JudgeInterface listener){
        HttpGet.getResponse(url, new ParseJson() {
            @Override
            public void error(Exception e) {
                listener.onError(e);
            }

            @Override
            public void parseJson(String response) {
                Imm imm = JsonUtil.parseJsonWithGson(response, Imm.class);

                if (imm!=null&!imm.isError()){
                    ImmUtil.setImm(imm);
                    listener.onSuccess();
                }else {
                    listener.onError(null);
                }
            }
        });
    }

    public static void loadMoreData(String url, final JudgeInterface listener){
        HttpGet.getResponse(url, new ParseJson() {
            @Override
            public void error(Exception e) {
                listener.onError(e);
            }

            @Override
            public void parseJson(String response) {
                Imm imm = JsonUtil.parseJsonWithGson(response, Imm.class);

                if (imm!=null&!imm.isError()){
                    ImmUtil.setImm(imm);
                    listener.onSuccess();
                }else {
                    listener.onError(null);
                }
            }
        });
    }
    public static void loadRefreshData(String url, final JudgeInterface listener){
        HttpGet.getResponse(url, new ParseJson() {
            @Override
            public void error(Exception e) {
                listener.onError(e);
            }

            @Override
            public void parseJson(String response) {
                Imm imm = JsonUtil.parseJsonWithGson(response, Imm.class);

                if (imm!=null&!imm.isError()){
                    ImmUtil.setImm(imm);
                    listener.onSuccess();
                }else {
                    listener.onError(null);
                }
            }
        });
    }
}
