package homework;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * ClassName: HW01Server
 * Package: homework
 * Description:
 *
 * @author fuchen
 * @version 1.0
 * @createTime 2024/9/10
 */
public class HW01Server {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(9999);

        Socket socket = serverSocket.accept();

        System.out.println(socket.getInetAddress().getHostAddress() + "上线了");

        InputStream inputStream = socket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String s = bufferedReader.readLine();
        socket.shutdownInput();
        if("name".equals(s)){
            HW01Server.send(socket,"我是泥叠");
        }else if("hobby".equals(s)){
            HW01Server.send(socket,"我洗翻你");
        }else {
            HW01Server.send(socket,"what can i say");
        }

        socket.close();
        serverSocket.close();

    }

    public static void send (Socket socket,String s)throws Exception{
        OutputStream outputStream = socket.getOutputStream();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        bufferedWriter.write(s);
        bufferedWriter.newLine();
        bufferedWriter.flush();

        bufferedWriter.close();
    }
}
