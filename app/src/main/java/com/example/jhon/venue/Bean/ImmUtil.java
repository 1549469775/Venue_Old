package com.example.jhon.venue.Bean;

/**
 * Created by John on 2017/3/13.
 */

public class ImmUtil {

    private static Imm imm= null ;

    public static Imm getImm() {
        if (imm==null){
            imm=new Imm();
        }
        return imm;
    }

    public static void setImm(Imm imm) {
        ImmUtil.imm = imm;
    }

}
