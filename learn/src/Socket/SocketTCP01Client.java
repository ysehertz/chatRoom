package Socket;

import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * ClassName: SocketTCP01Client
 * Package: Socket
 * Description:
 *
 * @author fuchen
 * @version 1.0
 * @createTime 2024/9/8
 */
public class SocketTCP01Client {
    public static void main(String[] args) throws Exception{
        // 1. 连接服务器
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);// 可以输入域名或者ip地址

        // 2. 连接成功后，生成Socket  拿到相关的输出流对象
        OutputStream outputStream = socket.getOutputStream();

        outputStream.write("to Socket server".getBytes());

        // 3. 关闭流和socket
        outputStream.close();
        socket.close();
        System.out.println("客户端退出");

    }
}
