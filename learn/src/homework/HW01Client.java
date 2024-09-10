package homework;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * ClassName: HW01Client
 * Package: homework
 * Description:
 *
 * @author fuchen
 * @version 1.0
 * @createTime 2024/9/10
 */
public class HW01Client {
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);

        String s = new Scanner(System.in).nextLine();

        OutputStream outputStream = socket.getOutputStream();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        bufferedWriter.write(s);
        bufferedWriter.newLine();
        bufferedWriter.flush();
        // 关闭输出流，开始接受返回消息
        socket.shutdownOutput();


        InputStream inputStream = socket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String s1 = bufferedReader.readLine();
        System.out.println(s1);

        // 关闭流和资源
        bufferedReader.close();
        bufferedWriter.close();
        socket.close();
    }
}
