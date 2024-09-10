package homework;

import upload.StreamUtils;

import javax.swing.event.SwingPropertyChangeSupport;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * ClassName: Hw03Client
 * Package: homework
 * Description:
 *
 * @author fuchen
 * @version 1.0
 * @createTime 2024/9/10
 */
public class Hw03Client {
    public static void main(String[] args) throws Exception{
        // 连接服务器
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);

        // 生成输出流，用于传递文件名
        String fileName = new Scanner(System.in).nextLine();
        OutputStream outputStream = socket.getOutputStream();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        bufferedWriter.write(fileName);
        bufferedWriter.flush();

        socket.shutdownOutput();

        // 生成输入流，用于接收服务器返回的信息
        BufferedInputStream bufferedInputStream = new BufferedInputStream(socket.getInputStream());
        byte[] bytes = StreamUtils.streamToByteArray(bufferedInputStream);

        String fileName1 = "E:\\java\\code\\chatRoom\\learn\\src\\homework\\" + getFileName(fileName);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(fileName1));
        bufferedOutputStream.write(bytes);

        // 关闭资源
        bufferedOutputStream.close();
        bufferedInputStream.close();
        bufferedWriter.close();
        socket.close();

        System.out.println("文件下载成功");
    }
    public static String getFileName(String s) {
        String directory = "E:\\java\\code\\chatRoom\\learn\\src";
        File file = new File(directory);
        for (File f : file.listFiles()) {
            if (f.getName().equals(s)) {
                return f.getName();
            }
        }
        return "888.jpg" ;
    }
}
