package com.isljq.service;

import com.isljq.qqcommon.Message;
import com.isljq.qqcommon.MessageType;
import com.isljq.qqcommon.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * ClassName: UserClientService
 * Package: com.isljq.service
 * Description: 该类完成用户登录验证和注册等待等功能
 *
 * @author fuchen
 * @version 1.0
 * @createTime 2024/9/12
 */
public class UserClientService {

    private User user = new User();

    Socket socket;
    // 登录检测
    public boolean checkUser(String userId,String pwd){
        boolean result = false;
        user.setUserId(userId);
        user.setPassword(pwd);

        // 连接本地服务器，发送user对象
        try {
            socket = new Socket(InetAddress.getLocalHost(), 9999);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(user);
//            socket.shutdownOutput();

            // 读取服务器返回的对象
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            Message message = (Message) objectInputStream.readObject();
            if(message.getMessageType().equals(MessageType.MESSAGE_LOGIN_SUCCEED)){
                ClientConnectServerThread clientConnectServerThread = new ClientConnectServerThread(socket);

                // 将线程对象放入集合中进行管理
                ManageClientConnectServerThread.addClientConnectServerThread(userId, clientConnectServerThread);
                // 启动客户端线程
                clientConnectServerThread.start();
                result = true;
            }else {
                // 如果登录失败，关闭socket
                socket.close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    /**
     * 获取在线用户列表
     */
    public void onlineFriendList() {
        Message message = new Message();
        message.setMessageType(MessageType.MESSAGE_GET_ONLINE_FRIEND);
        message.setSender(user.getUserId());
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void exitClient()  {
        Message message = new Message();
        message.setMessageType(MessageType.MESSAGE_CLIENT_EXIT);
        message.setSender(user.getUserId());
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(message);
            System.exit(0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
