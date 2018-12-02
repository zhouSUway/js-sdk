package com.qianfeng.util;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

import java.io.IOException;
import java.util.StringTokenizer;

public class WordCount {

    public static class Map extends  Mapper<Object,Text,Text,IntWritable>{

        private final static IntWritable one = new IntWritable(1);
        private Text word = new Text();

        @Override
        protected void map(Object key, Text values, Context context) throws IOException, InterruptedException {

            String lines = values.toString();
            String[] splited = lines.split(" ");
            String ip = splited[0];
            String times = splited[3]+" "+splited[4];

            ip = IpUtil.getCity(ip);
            times=DateUtil.getTime(times);

            StringTokenizer itr = new StringTokenizer(ip+" "+times);
            while (itr.hasMoreTokens()){
                word.set(itr.nextToken());
                context.write(word,one);
            }

        }
    }




    public static class Reduce extends  Reducer<Text,IntWritable,Text,IntWritable>{


        private IntWritable result = new IntWritable();

        @Override
        protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {

            for (IntWritable val :values){
                context.write(key,val);
            }
        }
    }


    public static void main(String[] args) throws Exception {

        args = new String[]{"hdfs://192.168.243.130:9000/logs/12/01/BC-23.1543679512738.log",
                "hdfs://192.168.243.130:9000/logs/output"};
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS",  "hdfs://192.168.243.130:9000");

        String[] otherArgs = new GenericOptionsParser(conf,args).getRemainingArgs();
        if(otherArgs.length<2){
            System.out.println("USage:wordCount<in><out>");
            System.exit(2);
        }

        //创新job并且名字
        Job job = Job.getInstance(conf,"wordcount");
        //1.设置job运行的类
        job.setJarByClass(WordCount.class);

        job.setMapperClass(Map.class);
        job.setReducerClass(Reduce.class);

        FileInputFormat.addInputPath(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        boolean isSuccess = job.waitForCompletion(true);

        System.out.println(isSuccess?0:1);

    }
}
