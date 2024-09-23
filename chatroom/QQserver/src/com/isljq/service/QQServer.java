package com.isljq.service;

import com.isljq.qqcommon.Message;
import com.isljq.qqcommon.MessageType;
import com.isljq.qqcommon.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import static java.lang.Thread.sleep;

/**
 * ClassName: QQServer
 * Package: qqserver.service
 * Description:服务端，监听9999端口等待客户端的连接，并保持通信
 *
 * @author fuchen
 * @version 1.0
 * @createTime 2024/9/13
 */
public class QQServer {
    private ServerSocket ss = null;
    private static HashMap<String, User> validUser = new HashMap<>();
    static{
        // 在静态代码块中初始化用户信息
        validUser.put("1", new User("1","1"));
        validUser.put("2", new User("2","2"));
        validUser.put("3", new User("3","3"));
        validUser.put("4", new User("4","4"));
    }
    private ServerSocket serverSocket = null;
    public QQServer() {
        System.out.println("服务端在9999端口进行监听");
        try {
            serverSocket = new ServerSocket(9999);

            while(true){
                Socket accept = serverSocket.accept();
                ObjectInputStream objectInputStream = new ObjectInputStream(accept.getInputStream());
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(accept.getOutputStream());
                User user = (User) objectInputStream.readObject();

                Message message = new Message(); // 用于回复给客户端
                if(checkUser(user.getUserId(), user.getPassword())){
                    message.setMessageType(MessageType.MESSAGE_LOGIN_SUCCEED);

                    ServerConnectClientThread serverConnectClientThread = new ServerConnectClientThread(accept, user.getUserId());
                    serverConnectClientThread.start();

                    objectOutputStream.writeObject(message);
                    ManageClientThreads.addClientThread(user.getUserId(), serverConnectClientThread);

                    // 建立第二个socket用于建立私聊通道
                    Socket socket = serverSocket.accept();
                    User user1 = (User) new ObjectInputStream(socket.getInputStream()).readObject();
                    ServerConnectClientThread serverConnectClientThread1 = new ServerConnectClientThread(socket, user1.getUserId());
                    serverConnectClientThread1.start();
                    ManageClientThreads.addClientThread(user1.getUserId(), serverConnectClientThread1);


                }else{
                    message.setMessageType(MessageType.MESSAGE_LOGIN_FAIL);
                    objectOutputStream.writeObject(message);
                    accept.close();
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private boolean checkUser(String userId,String pwd){
        User user = validUser.get(userId);
        if(user == null){
            return false;
        }else{
            return user.getPassword().equals(pwd);
        }
    }
}
