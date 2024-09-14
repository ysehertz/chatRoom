package com.isljq.service;

import com.isljq.qqcommon.Message;
import com.isljq.qqcommon.MessageType;

import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;

/**
 * ClassName: ServerConnectClientThread
 * Package: qqserver.service
 * Description: 用于与一个客户端保持连接的线程
 *
 * @author fuchen
 * @version 1.0
 * @createTime 2024/9/13
 */
public class ServerConnectClientThread extends Thread{


    private Socket socket;
    private String userId;

    public Socket getSocket() {
        return socket;
    }
    public ServerConnectClientThread(Socket socket , String userId) {
        this.socket = socket;
        this.userId = userId;
    }

    @Override
    public void run() {
        System.out.println("线程" + userId + "开始运行");
        while(true){
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                Message message = (Message) objectInputStream.readObject();

                // 获取用户列表
                if(MessageType.MESSAGE_GET_ONLINE_FRIEND.equals(message.getMessageType())){
                    System.out.println(message.getSender() + "请求在线用户列表");
                    String onlineUserList = ManageClientThreads.getOnlineUserList();
                    Message message1 = new Message();
                    message1.setMessageType(MessageType.MESSAGE_RET_ONLINE_FRIEND);
                    message1.setContent(onlineUserList);
                    message1.setGetter(message.getSender());

                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                    objectOutputStream.writeObject(message1);
                }else if(MessageType.MESSAGE_CLIENT_EXIT.equals(message.getMessageType())) {
                    // 客户端退出
                    System.out.println(message.getSender() + "退出");
                    ManageClientThreads.removeClientThread(userId);
                    socket.close();
                    break;
                }else if(MessageType.MESSAGE_COMM_MES.equals(message.getMessageType())){
                    // 消息私聊
                    Socket socket1 = ManageClientThreads.getServerConnectClientThread(message.getGetter()).getSocket();
                    new ObjectOutputStream(socket1.getOutputStream()).writeObject(message);
                }else if(MessageType.MESSAGE_TO_ALL_MES.equals(message.getMessageType())){
                    // 消息群发
                    HashMap<String, ServerConnectClientThread> hm = ManageClientThreads.getHm();

                    for(String userId : hm.keySet()){
                        if(!message.getSender().equals(userId)){
                            try {
                                new ObjectOutputStream(hm.get(userId).getSocket().getOutputStream()).writeObject(message);
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                }else if(MessageType.MESSAGE_FILE_MES.equals(message.getMessageType())){
                    Socket socket1 = ManageClientThreads.getServerConnectClientThread(message.getGetter()).getSocket();
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket1.getOutputStream());
                    objectOutputStream.writeObject(message);

                    ObjectInputStream objectInputStream1 = new ObjectInputStream(socket.getInputStream());
                    Message message1 = (Message) objectInputStream1.readObject();
                    Socket socket2 = ManageClientThreads.getServerConnectClientThread(message.getGetter()).getSocket();
                    new ObjectOutputStream(socket2.getOutputStream()).writeObject(message1);

                    // 关闭资源
                    objectInputStream1.close();
                    objectOutputStream.close();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

}
