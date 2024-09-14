package com.isljq.service;

import com.isljq.qqcommon.Message;
import com.isljq.qqcommon.MessageType;
import com.isljq.view.QQView;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * ClassName: ClientConnectServerThread
 * Package: com.isljq.service
 * Description:
 *
 * @author fuchen
 * @version 1.0
 * @createTime 2024/9/13
 */
public class ClientConnectServerThread extends Thread{

    private FileClientService fileClientService;
    // 该线程需要持有一个Socket
    private Socket socket;
    public ClientConnectServerThread(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
    @Override
    public void run() {
        // 因为Thread需要在后台和服务器通信，因此我们用while循环
        while(true){
            System.out.println("客户端线程，等待读取从服务端发送来的消息");
            try {
                // 如果服务器没有发送Message对象，线程会阻塞在这里，直到从服务端读取到消息
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                Message message = (Message) objectInputStream.readObject();

                if(message.getMessageType().equals(MessageType.MESSAGE_RET_ONLINE_FRIEND)){
                    String[] s = message.getContent().split(" ");
                    System.out.println("\n当前在线用户列表：");
                    for (int i = 0; i < s.length; i++) {
                        System.out.println("用户：" + s[i]);
                    }
                }else if(message.getMessageType().equals(MessageType.MESSAGE_COMM_MES)){
                    // 接受私聊消息
                    System.out.println("\n" + message.getSender() + " 对你说：" + message.getContent());
                }else if(message.getMessageType().equals(MessageType.MESSAGE_TO_ALL_MES)){
                    // 接受群聊消息
                    System.out.println("\n" + message.getSender() + " 对大家说：" + message.getContent());
                }else if(message.getMessageType().equals(MessageType.MESSAGE_FILE_MES)){


                    System.out.println("\n" + message.getSender() + " 给你发送了一个文件");

                    synchronized (System.in){ // 提示用户是否接收该文件

                        System.out.println("\n" + message.getSender() + " 给你发送了一个文件");
                        System.out.println("是否接收该文件？y/n");

                        char choice = ManageClientConnectServerThread.getScanner().next().charAt(0);
                        if (choice == 'y') {
                            // 接收文件
                            // 提示用户输入保存路径
                            System.out.println("请输入保存路径（例如：D:\\）");
                            String dest = ManageClientConnectServerThread.getScanner().next();


                            FileOutputStream fileOutputStream = new FileOutputStream(dest);
                            fileOutputStream.write(message.getFileBytes(), 0, message.getFileLen());
                            fileOutputStream.close();
                            System.out.println("文件保存成功！");

                            // 发送同意通知
                            Message message1 = new Message();
                            message1.setMessageType(MessageType.MESSAGE_AGREE_FILE_MES);
                            message1.setGetter(message.getSender());
                            message1.setSender(message.getGetter());
                            new ObjectOutputStream(ManageClientConnectServerThread.getClientConnectServerThread(message1.getSender()).getSocket().getOutputStream()).writeObject(message1);
                        } else {
                            Message message1 = new Message();
                            message1.setMessageType(MessageType.MESSAGE_DISAGREE_FILE_MES);
                            message1.setGetter(message.getSender());
                            message1.setSender(message.getGetter());

                            new ObjectOutputStream(ManageClientConnectServerThread.getClientConnectServerThread(message1.getSender()).getSocket().getOutputStream()).writeObject(message1);
                        }
                    }
                }else if(message.getMessageType().equals(MessageType.MESSAGE_AGREE_FILE_MES)){
                    System.out.println(message.getSender()+"同意接受此文件");
                }else if(message.getMessageType().equals(MessageType.MESSAGE_DISAGREE_FILE_MES)){
                    System.out.println(message.getSender()+"拒绝接受此文件");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
