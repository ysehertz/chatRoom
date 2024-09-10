package Socket;

import java.io.*;
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
public class SocketTCP03Client {
    public static void main(String[] args) throws Exception{
        // 1. 连接服务器
        Socket socket = new Socket(InetAddress.getLocalHost(),  9999);// 可以输入域名或者ip地址

        // 2. 连接成功后，生成Socket  拿到相关的输出流对象
        // 这种字符流的方式中，服务端要用readLine()
        OutputStream outputStream = socket.getOutputStream();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        bufferedWriter.write("hi, server 我是字符流");
        bufferedWriter.newLine(); // 用换行符代表写入结束
        bufferedWriter.flush();
//        outputStream.write("to Socket server".getBytes());

        // 关闭输出流 
        socket.shutdownOutput();

//        // 3. 获取和服务器连接的输入流，并读取显示数据
//        InputStream inputStream = socket.getInputStream();
//        byte[] buf = new byte[1024];
//        int readLen = 0;
//        while( (readLen = inputStream.read(buf) )!= -1){
//            System.out.println(new String(buf,0,readLen));
//        }
        InputStream inputStream = socket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String s = bufferedReader.readLine();
        System.out.println(s);

        // 4. 关闭流和socket
        bufferedWriter.close();
        bufferedReader.close();
        socket.close();
        System.out.println("客户端退出");

    }
}
