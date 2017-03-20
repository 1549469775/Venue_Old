package com.example.jhon.venue.Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by John on 2017/3/20.
 */

public class ActicalListUtil {

    private static List<Actical> list = new ArrayList<>();

    public static List<Actical> getList() {

        return list;
    }

    public static void addList(Actical actical) {
        list.add(actical);
    }
}
