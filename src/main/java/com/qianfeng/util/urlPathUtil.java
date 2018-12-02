package com.qianfeng.util;

import java.util.HashMap;
import java.util.Map;

public class urlPathUtil {

    public static Map<String, String> getUrlPath(String url){
        /**
         * /index.html?en=e_pv&p_url=http%3A%2F%2Flocalhost%3A8080%2Fdemo3.jsp&p_ref=http%3A%2F%2Flocalhost%3A8080%2Fdemo3.jsp&tt=%E6%B5%8B%E8%AF%95%E9%A1%B5%E9%9D%A23&ver=1&pl=website&sdk=js&u_ud=08611FC4-A431-47CE-9CE3-485F7E743A0D&u_mid=liyadong&u_sd=B428B9C9-8FC2-4F5C-B776-9DD9DB823DD4&c_time=1543666508122&l=zh-CN&b_iev=Mozilla%2F5.0%20(Windows%20NT%206.1%3B%20WOW64)%20AppleWebKit%2F537.36%20(KHTML%2C%20like%20Gecko)%20Chrome%2F58.0.3029.110%20Safari%2F537.36%20SE%202.X%20MetaSr%201.0&b_rst=1366*768
         *
         */

        Map<String,String> maps = new HashMap<String,String>();

        String [] urls=url.split("&");
        for (int j=0;j<urls.length;j++){
            maps.put(urls[0],urls[1]);
        }

        return maps;
    }

    public static void main(String[] args) {
        String url = "/index.html?en=e_pv&p_url=http%3A%2F%2Flocalhost%3A8080%2Fdemo3.jsp&p_ref=http%3A%2F%2Flocalhost%3A8080%2Fdemo3.jsp&tt=%E6%B5%8B%E8%AF%95%E9%A1%B5%E9%9D%A23&ver=1&pl=website&sdk=js&u_ud=08611FC4-A431-47CE-9CE3-485F7E743A0D&u_mid=liyadong&u_sd=B428B9C9-8FC2-4F5C-B776-9DD9DB823DD4&c_time=1543666508122&l=zh-CN&b_iev=Mozilla%2F5.0%20(Windows%20NT%206.1%3B%20WOW64)%20AppleWebKit%2F537.36%20(KHTML%2C%20like%20Gecko)%20Chrome%2F58.0.3029.110%20Safari%2F537.36%20SE%202.X%20MetaSr%201.0&b_rst=1366*768\n";

        try {
          System.out.print(getUrlPath(url));
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
