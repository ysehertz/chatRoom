package com.isljq.view;

import com.isljq.qqcommon.User;
import com.isljq.service.*;
import org.junit.Test;

import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Scanner;

import static java.lang.Thread.sleep;

/**
 * ClassName: QQView
 * Package: com.isljq.view
 * Description:
 *
 * @author fuchen
 * @version 1.0
 * @createTime 2024/9/12
 */
public class QQView {

    public boolean loop = true; // 是否显示菜单
    private int key = 0;
    private UserClientService userClientService = new UserClientService();
    private MessageClientService messageClientService = new MessageClientService();
    private FileClientService fileClientService = new FileClientService();

    String userId;


    public void mainMenu(){
        System.out.println("==========欢迎登录网络通信系统==========");
        System.out.println("\t\t\t1 登录系统");
        System.out.println("\t\t\t9 退出系统");
        System.out.print("请输入你的选择：");

//        Scanner scanner = new Scanner(System.in);


        key = new Scanner(System.in).nextInt();


        switch(key){
            case 1:

                while (loop){
                    System.out.println("请输入账号：");
                     userId = new Scanner(System.in).next();
                    System.out.println("请输入密码：");
                    String password = new Scanner(System.in).next();
                    if(userClientService.checkUser(userId,password)){
                        ManageClientConnectServerThread.setQqView(this);
                        System.out.println("==========欢迎回来：" + userId + "==========");
                        while(loop){
                            chatMenu();
                        }
                    }else {
                        System.out.println("登录失败请重新登陆");
                    }

                }
                break;
            case 9:
                loop = false;
                break;
            default:
                System.out.println("输入有误，请重新输入");
        }

    }

    public void mainSleep(){
        synchronized (this) {
            try {
                // 确保在同步块内调用 wait()
                this.wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // 恢复中断状态
                System.out.println("Thread was interrupted");
            }
        }
    }


    // 唤醒线程
    public void mainNotify(){
        synchronized (this) {
            // 在需要唤醒其他线程的地方调用 notify() 或 notifyAll()
            this.notify(); // 或者使用 lock.notifyAll();
        }
    }

    private void chatMenu() {
        System.out.println("============网络通信系统二级菜单============");
        System.out.println("\t\t\t1 在线用户列表");
        System.out.println("\t\t\t2 群聊");
        System.out.println("\t\t\t3 私聊");
        System.out.println("\t\t\t4 发送文件");
        System.out.println("\t\t\t9 退出系统");
        System.out.print("请输入你的选择：");
        key = new Scanner(System.in).nextInt();
        switch(key){
            case 1:
                userClientService.onlineFriendList();
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                break;
            case 2:
                System.out.print("请输入想对大家说的话：");
                messageClientService.sendMessageToAll(new Scanner(System.in).next(),userId);
                break;
            case 3:
                System.out.print("请输入想聊天的在线用户的用户号：");
                String userId1 = new Scanner(System.in).next();
                System.out.print("请输入想说的话：");
                String content = new Scanner(System.in).next();
                messageClientService.sendMessageToOne(content,userId,userId1);
                break;
            case 4:
                System.out.print("请输入想发送的文件路径：");
                String src = new Scanner(System.in).next();
                System.out.print("请输入文件描述信息：");
                String content1 = new Scanner(System.in).next();
                System.out.println("请输入文件接受者");
                String userid1 = new Scanner(System.in).next();
                fileClientService.sendFileToOne(src,userId,userid1,content1);
                break;
            case 9:
                loop = false;
                userClientService.exitClient();

                System.out.println("客户端退出系统。。。");
                break;
            default:
                System.out.println("输入有误，请重新输入");
        }
    }
}
