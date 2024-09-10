package homework;

import upload.StreamUtils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * ClassName: Hw03Server
 * Package: homework
 * Description:
 *
 * @author fuchen
 * @version 1.0
 * @createTime 2024/9/10
 */
public class Hw03Server {
    public static void main(String[] args) throws Exception{
        // 监听9999端口
        ServerSocket serverSocket = new ServerSocket(9999);
        // 等待客户端连接
        Socket socket = serverSocket.accept();
        // 读取数据
        InputStream inputStream = socket.getInputStream();
        byte[] bytes = new byte[1024];
        String s = "";
        int len = 0;
        while ((len = inputStream.read(bytes)) != -1) {
            s += new String(bytes, 0, len);
        }

        System.out.println("已接收要下载的文件名+++++" + s);

        String resFileName = Hw03Server.getFileName(s);

        //使用StreamUtils工具类读取文件到一个字节数组中
        byte[] bytes1 = StreamUtils.streamToByteArray(new FileInputStream(resFileName));

        // 将拿到的字节数组写入到客户端
        OutputStream outputStream = socket.getOutputStream();
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(socket.getOutputStream());
        bufferedOutputStream.write(bytes1);
        bufferedOutputStream.flush();

        socket.shutdownOutput();

        // 关闭资源
        bufferedOutputStream.close();
        socket.close();
        serverSocket.close();

        System.out.println("传输完成，服务端退出");
    }

    public static String getFileName(String s) {
        String directory = "E:\\java\\code\\chatRoom\\learn\\src";
        File file = new File(directory);
        for (File f : file.listFiles()) {
            if (f.getName().equals(s)) {
                return f.getAbsolutePath();
            }
        }
        return directory+"\\888.jpg" ;
    }
}
