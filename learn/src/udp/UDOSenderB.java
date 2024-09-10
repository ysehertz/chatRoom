package udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * ClassName: UDOSenderB
 * Package: udp
 * Description:
 *
 * @author fuchen
 * @version 1.0
 * @createTime 2024/9/9
 */
public class UDOSenderB {
    public static void main(String[] args) throws Exception{
        // 创建 DatagramSocket 对象，准备发送和接受数据
        DatagramSocket datagramSocket = new DatagramSocket(9998);

        // 将需要发送的数据，封装到 DatagramPacket 对象中去
        byte[] data = "hello，我喜欢你".getBytes();
        DatagramPacket datagramPacket = new DatagramPacket(data, data.length, InetAddress.getByName("127.0.0.1"), 9999);
        datagramSocket.send(datagramPacket);

        // 接收回复的消息
        byte[] data2 = new byte[1024];
        DatagramPacket datagramPacket2 = new DatagramPacket(data2, data2.length);
        datagramSocket.receive(datagramPacket2);

        int length = datagramPacket2.getLength();
        byte[] data3 = datagramPacket2.getData();

        String s = new String(data3,0,length);
        System.out.println(s);


        datagramSocket.close();
    }
}
