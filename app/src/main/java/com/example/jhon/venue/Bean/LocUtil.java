package com.example.jhon.venue.Bean;

import java.util.List;

/**
 * Created by John on 2017/3/15.
 */

public class LocUtil {

    private static Loc loc= null ;

    public static Loc getLoc() {
        if (loc==null){
            loc=new Loc();
        }
        return loc;
    }

    public static void setLoc(Loc loc) {
        LocUtil.loc = loc;
    }
}
