package com.qianfeng.util;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.InputStream;

public class CleanUtil {


    //todo 读取文件内容
    public static void readFile(String filePath) throws Exception{
        Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS","hdfs://192.168.243.130:9000");
        FileSystem fs = FileSystem.get(configuration);
        Path srcPath = new Path(filePath);
        InputStream in = null;
        try {
            in = fs.open(srcPath);
            IOUtils.copyBytes(in ,System.out,4096,false);//复制到标准输出流
        }finally {
            IOUtils.closeStream(in);
        }

    }
    public static void main(String[] args) {





    }
}
