package com.qianfeng.util;

import com.ggstar.util.ip.IpHelper;

public class IpUtil {


    public static String getCity(String ip){

        return IpHelper.findRegionByIp(ip);

    }

    public static void main(String[] args) {

        System.out.println(getCity("58.0.3029.110"));

    }



}
