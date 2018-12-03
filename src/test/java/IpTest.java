import com.github.jarod.qqwry.IPZone;
import com.github.jarod.qqwry.QQWry;
import org.alidata.odps.udf.Ip2Region;

public class IpTest {

    public static void main(String[] args) {

        try {

            QQWry qqWry = new QQWry();
            IPZone zone = qqWry.findIP("218.75.35.226");
            System.out.println(zone.getMainInfo());
            System.out.println(zone.getSubInfo());
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
