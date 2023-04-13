package com.itheima.utilities;

import java.util.HashMap;
import java.util.Map;

public class TimeUtils {
    //获取对应月份的天数
    public static String getTime(String time){
        String[] split = time.split("-");
        if(split[0].equals("2")){
            return time + "-28";
        }
        switch (split[0]){
            case "1":
            case "3":
            case "5":
            case "7":
            case "8":
            case "10":
            case "12":
                return time + "-31";
            default:
                return time + "-30";

        }
    }
}
