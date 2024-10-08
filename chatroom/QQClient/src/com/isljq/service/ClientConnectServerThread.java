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
     boolean lop = true;

    public boolean isLop() {
        return lop;
    }

    public void setLoop(boolean loop) {
        this.lop = loop;
    }

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
            try {
                /**
                 * 读取从服务器发送的消息
                 */
                // 如果服务器没有发送Message对象，线程会阻塞在这里，直到从服务端读取到消息
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                Message message = (Message) objectInputStream.readObject();

                if(message.getMessageType().equals(MessageType.MESSAGE_RET_ONLINE_FRIEND)){
                    String[] s = message.getContent().split(" ");
                    System.out.println("\n当前在线用户列表：");
                    for (int i = 0; i < s.length; i++) {
                        if(s[i].charAt(s[i].length() - 1)!= ':')
                            System.out.println("用户：" + s[i]);
                    }
                }else if(message.getMessageType().equals(MessageType.MESSAGE_COMM_MES)){
                    /**
                     * 接受私聊请求，并在此做出回应
                     */
                    System.out.println("\n" + message.getSender() + "邀请进入私聊频道，是否同意(请先按0后输入y/n以响应)" );
                    synchronized (System.in){
                        char choice = ManageClientConnectServerThread.getScanner().next().charAt(0);
                        if(choice == 'y'){
                            /**
                             * 同意私聊请求，开启私聊
                             */
                            System.out.println("已进入与"+message.getSender()+"的聊天频道");
                            // 发送同意消息
                            Message message1 = new Message();
                            message1.setMessageType(MessageType.MESSAGE_COMM_MES_AGREE);
                            message1.setGetter(message.getSender());
                            message1.setSender(message.getGetter());
                            new ObjectOutputStream(socket.getOutputStream()).writeObject(message1);
                            lop = true;
                            while (lop) {
                                Message message2 = new Message();
                                message2.setMessageType(MessageType.MESSAGE_COMM_MES_ONE);
                                String content = ManageClientConnectServerThread.getScanner().next();
                                if("exit".equals(content))
                                {
                                    lop = false;
                                    message2.setContent(message.getGetter()+"退出私聊");
                                    message2.setMessageType(MessageType.MESSAGE_COMM_EXIT_ONE);
                                }else {
                                    message2.setMessageType(MessageType.MESSAGE_COMM_MES_ONE);
                                    message2.setContent(content);
                                }
                                message2.setSender(message.getGetter()+":");
                                message2.setGetter(message.getSender()+":");

                                new ObjectOutputStream(socket.getOutputStream()).writeObject(message2);
                            }
                        }else{
                            /**
                             * 拒绝私聊请求
                             */
                            Message message1 = new Message();
                            message1.setMessageType(MessageType.MESSAGE_COMM_MES_REFUSE);
                            message1.setGetter(message.getSender());
                            message1.setSender(message.getGetter());
                            new ObjectOutputStream(socket.getOutputStream()).writeObject(message1);
                        }
                    }
                }else if(message.getMessageType().equals(MessageType.MESSAGE_COMM_MES_ONE)){
                    /**
                     * 子线程接受私聊消息
                     */
                    System.out.println(message.getSender()+message.getContent());
                }else if(message.getMessageType().equals(MessageType.MESSAGE_COMM_MES_AGREE)){
                    /**
                     * 被邀请方同意开启私聊
                     */
                    System.out.println("\n" + message.getSender() + " 同意了你的私聊请求，现在可以私聊了,按0进入私聊");
                    synchronized (System.in){
                        lop = true;
                        while (lop) {
                            Message message2 = new Message();
                            message2.setMessageType(MessageType.MESSAGE_COMM_MES_ONE);
                            String content = ManageClientConnectServerThread.getScanner().next();
                            if("exit".equals(content))
                            {
                                lop = false;
                                message2.setContent(message.getGetter()+"退出私聊");
                                message2.setMessageType(MessageType.MESSAGE_COMM_EXIT_ONE);
                            }else {
                                message2.setMessageType(MessageType.MESSAGE_COMM_MES_ONE);
                                message2.setContent(content);
                            }
                            message2.setSender(message.getGetter()+":");
                            message2.setGetter(message.getSender()+":");
                                new ObjectOutputStream(socket.getOutputStream()).writeObject(message2);
                        }

                    }
                }else if(message.getMessageType().equals(MessageType.MESSAGE_COMM_MES_REFUSE)){
                    /**
                     * 被邀请方拒绝进入私聊
                     */
                    System.out.println("\n" + message.getSender() + " 拒绝了你的私聊请求");
                }else if(message.getMessageType().equals(MessageType.MESSAGE_COMM_EXIT_ONE)){
                    /**
                     * 另一方主动关闭了私聊
                     */
                    String userId = message.getGetter();
                    userId = userId.substring(0,userId.length()-1);
                    ManageClientConnectServerThread.getClientConnectServerThread(userId).setLoop(false);
                    System.out.println("\n" + message.getSender().substring(0,message.getSender().length()-1) + " 关闭了私聊通道(请输入0以回到首页)");
                } else if(message.getMessageType().equals(MessageType.MESSAGE_TO_ALL_MES)){
                    /**
                     * 接受群聊消息
                     */
                    System.out.println("\n" + message.getSender() + " 对大家说：" + message.getContent());
                }else if(message.getMessageType().equals(MessageType.MESSAGE_FILE_MES)){
                    /**
                     * 收到文件请求
                     */
                    System.out.println("\n" + message.getSender() + " 给你发送了一个文件,请按0刷新以响应");

                    synchronized (System.in){ // 提示用户是否接收该文件

                        System.out.println("\n" + message.getSender() + " 给你发送了一个文件："+message.getContent());
                        System.out.println("是否接收该文件?");

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
                    /**
                     * 接受方同意接受文件
                     */
                    System.out.println();
                    System.out.println(message.getSender()+"同意接受此文件");
                }else if(message.getMessageType().equals(MessageType.MESSAGE_DISAGREE_FILE_MES)){
                    /**
                     * 接受方拒绝接受文件
                     */
                    System.out.println();
                    System.out.println(message.getSender()+"拒绝接受此文件");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
