package upload;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * ClassName: TCPFileUploadServer
 * Package: upload
 * Description:
 *
 * @author fuchen
 * @version 1.0
 * @createTime 2024/9/9
 */
public class TCPFileUploadServer {
    public static void main(String[] args) throws  Exception {
        // 1. 在本机监听8888端口
        ServerSocket serverSocket = new ServerSocket(8888);
        System.out.println("客户端启动成功,开始监听");

        // 2. 等待连接
        Socket socket = serverSocket.accept();

        BufferedInputStream bufferedInputStream = new BufferedInputStream(socket.getInputStream());
        byte[] bytes = StreamUtils.streamToByteArray(bufferedInputStream);

        String fileName = "E:\\java\\code\\chatRoom\\learn\\src\\upload\\666.jpg";
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(fileName));
        bufferedOutputStream.write(bytes);

        // 向客户端回复上传成功
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        bufferedWriter.write("上传成功");
        bufferedWriter.flush();
        socket.shutdownOutput();


        bufferedInputStream.close();
        bufferedOutputStream.close();
        socket.close();
        serverSocket.close();


    }
}
