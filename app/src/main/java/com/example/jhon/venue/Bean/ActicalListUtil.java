package com.example.jhon.venue.Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by John on 2017/3/20.
 */

public class ActicalListUtil {

    private static List<Article> list = new ArrayList<>();

    public static List<Article> getList() {

        return list;
    }

    public static void addList(Article article) {
        list.add(article);
    }
}
