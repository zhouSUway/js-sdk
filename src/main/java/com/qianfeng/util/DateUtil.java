package com.qianfeng.util;

import javafx.scene.chart.PieChart;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {


    public static  String getTime(String time){

        //输入日志[01/Dec/2018:20:14:17 +0800]
        SimpleDateFormat YYYYMMDDHHMM_TIME_FORMAT =new SimpleDateFormat("dd/MMM/yyyy:HH:mm:SS Z",Locale.ENGLISH);

        //目标日期格式
        SimpleDateFormat TARGET_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date dateTrans=null;

        try {
            dateTrans = YYYYMMDDHHMM_TIME_FORMAT.parse(time.substring(time.indexOf("[")+1,time.lastIndexOf("]")));
            return TARGET_FORMAT.format(dateTrans);
        }catch (Exception e){
            e.printStackTrace();
        }

        return time;
    }


    public static void main(String[] args) {
       String test = getTime("[01/Dec/2018:20:14:17 +0800]");
       System.out.println(test);
    }
}
