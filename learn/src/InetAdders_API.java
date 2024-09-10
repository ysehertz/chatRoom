import java.net.InetAddress;

/**
 * ClassName: API
 * Package: PACKAGE_NAME
 * Description:
 *
 * @author fuchen
 * @version 1.0
 * @createTime 2024/9/8
 */
public class InetAdders_API {
    public static void main(String[] args) throws Exception {
        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println(localHost); //fuchen/192.168.210.226

        InetAddress localHost2 = InetAddress.getByName("www.bilibili.com");
        System.out.println(localHost2); //www.bilibili.com/61.240.206.11

        // 通过InetAddress对象获取对应的主机名和域名
        String hostName = localHost2.getHostName();
        System.out.println(hostName);
        String hostAddress = localHost2.getHostAddress();
        System.out.println(hostAddress);


    }
}
