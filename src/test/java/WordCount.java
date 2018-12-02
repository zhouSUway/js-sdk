import com.qianfeng.util.DateUtil;
import com.qianfeng.util.IpUtil;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hdfs.DistributedFileSystem;
import org.apache.hadoop.hdfs.protocol.DatanodeInfo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class WordCount {

    private static Configuration conf = new Configuration();
    private static final String HADOOP_URL="hdfs://192.168.243.130:9000";

    private static FileSystem fs;

    private static DistributedFileSystem hdfs;

    static {
        try {

            FileSystem.setDefaultUri(conf,HADOOP_URL);
            fs = FileSystem.get(conf);
            hdfs = (DistributedFileSystem) fs;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //列出所有DataNode的名字信息


   public void listDataNodeInfo(){
        try {
            DatanodeInfo[] dataNodeStats = hdfs.getDataNodeStats();
            String[] names = new String[dataNodeStats.length];

            System.out.println("list of all the datanode in the hdfs cluster");

            for(int i=0;i<names.length;i++){
                names[i] = dataNodeStats[i].getHostName();
                System.out.println(names[i]);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
   }

    /**
     * 读取hadf中的文件内容
     *
     * @param
     */

    public void readFileFormHdfs() throws Exception{
        try {

            Path path = new Path("/logs/12/01/BC-20.1543666457643.log");

            FSDataInputStream dis = fs.open(path);

            InputStreamReader isr = new InputStreamReader(dis,"utf-8");

            BufferedReader br = new BufferedReader(isr);

            String str = "";

            while ((str=br.readLine())!=null){

//                System.out.println(str);
                String lines = str.toString();
                String[] splited = lines.split(" ");
                String ip = splited[0];
                String times=splited[3]+" "+splited[4];
                String urlPath=splited[6];
                String traffic = splited[8];
                String pv=splited[9];
                String url = splited[10];

                DateUtil dateUtil = new DateUtil();
                IpUtil ipUtil = new IpUtil();
                ip = ipUtil.getCity(ip);
                times= dateUtil.getTime(times);


                System.out.println(ip+" "+times+" "+traffic+" "+pv+" "+url+" "+urlPath);

            }

            br.close();
            isr.close();
            dis.close();

//

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {

        WordCount a = new WordCount();
        a.listDataNodeInfo();

        try {
            a.readFileFormHdfs();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
