package Socket;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * ClassName: SockerTCP01Server
 * Package: Socket
 * Description:
 *
 * @author fuchen
 * @version 1.0
 * @createTime 2024/9/8
 */
public class SocketTCP02Server {
    public static void main(String[] args) throws Exception {
        // 1. 创建一个服务器端Socket，绑定9999端口
        // 注意：：要求在本机没有其他服务在监听9999
        ServerSocket serverSocket = new ServerSocket(9999);

        // 2. 当没有客户端连接9999端口时，程序会阻塞，等待连接
        // 如果有客户端连接，则会返回Socket对象，程序继续
        Socket socket = serverSocket.accept();

        System.out.println("连接成功，socket==" + socket.getClass());

        // 3. 通过socket.getInputStream()读取客户端写入到数据通道的数据，并显示
        InputStream inputStream = socket.getInputStream();
        byte[] buf = new byte[1024];
        int readLen = 0;
        while((readLen = inputStream.read(buf)) != -1 ) {
            System.out.println(new String(buf, 0, readLen)); // 根据读取到的长度显示数据
        }

        socket.shutdownInput();

        // 4.拿到输出流，给客户端回写数据
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("hello, client".getBytes());

        // 5. 关闭流和socket
        outputStream.close();
        inputStream.close();
        socket.close();
        serverSocket.close();
    }
}
