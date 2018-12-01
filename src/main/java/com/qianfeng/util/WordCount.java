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

import java.io.IOException;
import java.util.StringTokenizer;

public class WordCount {

    public static class Map extends  Mapper<Object,Text,Text,IntWritable>{

        private final static IntWritable one = new IntWritable(1);
        private Text word = new Text();

        @Override
        protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {

            String lines = value.toString();

            String splited[] = lines.split(" ");

            String line = splited[0];

            StringTokenizer itr = new StringTokenizer(line);
            while (itr.hasMoreTokens()){
               word.set(itr.nextToken());
               context.write(word,one);
               System.out.println(word);
               System.out.println(itr.nextToken());
            }
        }
    }


    public static class Reduce extends  Reducer<Text,IntWritable,Text,IntWritable>{


        private IntWritable result = new IntWritable();

        @Override
        protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {

            int sum = 0;
            for(IntWritable val :values){
                sum+=val.get();
            }

            result.set(sum);

            context.write(key,result);

        }
    }


    public static void main(String[] args) throws Exception {

        Configuration conf = new Configuration();

        conf.set("fs.default.name","hdfs://192.168.243.130:9000");

        Job job =Job.getInstance(conf);

        job.setJarByClass(WordCount.class);
        job.setJar("WordCount.jar");
        job.setJobName("WordCount");

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        job.setMapperClass(Map.class);
        job.setReducerClass(Reduce.class);
        job.setCombinerClass(Reduce.class);

        String inPath ="/logs/12/01/*.log";
        String outPath = "/logs/wordcount";

        FileInputFormat.addInputPath(job, new Path(inPath));
        FileOutputFormat.setOutputPath(job, new Path(outPath));


        System.out.println("success");

        System.exit(job.waitForCompletion(true)?0:1);

    }
}
