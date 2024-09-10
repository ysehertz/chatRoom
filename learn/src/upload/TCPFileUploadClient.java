package upload;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * ClassName: TCPFileUploadClient
 * Package: upload
 * Description:
 *
 * @author fuchen
 * @version 1.0
 * @createTime 2024/9/9
 */
public class TCPFileUploadClient {
    public static void main(String[] args) throws  Exception {
        // 1. 在本机监听8888端口
        Socket socket = new Socket(InetAddress.getLocalHost(), 8888);

        // 2.创建读取磁盘文件的输入流
        String filePath = "E:\\666.jpg";
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(filePath));

        // 获取该文件对应的字节数组
        byte[] bytes = StreamUtils.streamToByteArray(bufferedInputStream);

        // 3.通过socket获取输出流，将bytes数据发送到服务器
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(socket.getOutputStream());
        bufferedOutputStream.write(bytes); // 将文件的字节数组写入到输出流中


        socket.shutdownOutput(); // 写入数据的结束标记

        // 用字节流接受服务端相应的消息
        InputStream inputStream = socket.getInputStream();
        System.out.println(StreamUtils.streamToString(inputStream));

        inputStream.close();
        bufferedInputStream.close();
        bufferedOutputStream.close();
        socket.close();


    }
}
