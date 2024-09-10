package udp;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * ClassName: UDOReceiverA
 * Package: udp
 * Description:
 *
 * @author fuchen
 * @version 1.0
 * @createTime 2024/9/9
 */
public class UDOReceiverA {
    public static void main(String[] args) throws Exception {
        // 1.创建一个DatagramSocket对象，并指定端口号9999
        DatagramSocket datagramSocket = new DatagramSocket(9999);

        // 2. 构建一个DatagramPacket对象，用于接受数据
        byte[] bytes = new byte[1024];
        DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length);

        // 3. 调用 接收方法
        // 在9999端口等待数据，没有数据时阻塞等待
        System.out.println("接受A 等待接受数据。。。");
        datagramSocket.receive(datagramPacket);

        // 4.把packet拆包，取出数据同时显示
        int length = datagramPacket.getLength();
        byte[] data = datagramPacket.getData();

        String s = new String(data, 0, length);
        System.out.println(s);

        byte[] bytes1 = "泥嚎啊，我也喜欢你".getBytes();
        DatagramPacket datagramPacket1 = new DatagramPacket(bytes1, bytes1.length, InetAddress.getByName("127.0.0.1"), 9998);
        datagramSocket.send(datagramPacket1);

        datagramSocket.close();
    }
}
