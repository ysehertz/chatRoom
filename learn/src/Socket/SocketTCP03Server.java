package Socket;

import java.io.*;
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
public class SocketTCP03Server {
    public static void main(String[] args) throws Exception {
        // 1. 创建一个服务器端Socket，绑定9999端口
        // 注意：：要求在本机没有其他服务在监听9999
        ServerSocket serverSocket = new ServerSocket(9999);

        // 2. 当没有客户端连接9999端口时，程序会阻塞，等待连接
        // 如果有客户端连接，则会返回Socket对象，程序继续
        Socket socket = serverSocket.accept();

        System.out.println("连接成功，socket==" + socket.getClass());
        /* 改为字符流读取数据 */
        InputStream inputStream = socket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String s = bufferedReader.readLine();
        System.out.println(s);

//        // 3. 通过socket.getInputStream()读取客户端写入到数据通道的数据，并显示
//        InputStream inputStream = socket.getInputStream();
//        byte[] buf = new byte[1024];
//        int readLen = 0;
//        while((readLen = inputStream.read(buf)) != -1 ) {
//            System.out.println(new String(buf, 0, readLen)); // 根据读取到的长度显示数据
//        }

        socket.shutdownInput();

//        // 4.拿到输出流，给客户端回写数据
//        OutputStream outputStream = socket.getOutputStream();
//        outputStream.write("hello, client".getBytes());
        OutputStream outputStream = socket.getOutputStream();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        bufferedWriter.write("hello, client,我是字符流");
        bufferedWriter.newLine();
        bufferedWriter.flush();

        // 5. 关闭流和socket
        bufferedWriter.close();
        bufferedReader.close();
        socket.close();
        serverSocket.close();
    }
}
