package com.isljq.service;

import com.isljq.view.QQView;

import java.util.HashMap;
import java.util.Scanner;

/**
 * ClassName: ManageClientConnectServerThread
 * Package: com.isljq.service
 * Description: 用于管理所有和客户端通信的线程
 *
 * @author fuchen
 * @version 1.0
 * @createTime 2024/9/13
 */
public class ManageClientConnectServerThread {
    private static Scanner scanner = new Scanner(System.in);

    public static Scanner getScanner() {
        return scanner;
    }

    public static void setScanner(Scanner scanner) {
        ManageClientConnectServerThread.scanner = scanner;
    }

    public static HashMap<String, ClientConnectServerThread> getHm() {
        return hm;
    }

    public static void setHm(HashMap<String, ClientConnectServerThread> hm) {
        ManageClientConnectServerThread.hm = hm;
    }

    // 把多个线程放入一个HashMap中，key为用户id，value为线程
    private static HashMap<String, ClientConnectServerThread> hm = new HashMap<>();

    // 添加线程到集合
    public static void addClientConnectServerThread(String userId, ClientConnectServerThread clientConnectServerThread)
    {
        hm.put(userId, clientConnectServerThread);
    }

    // 通过userID 获取线程
    public static ClientConnectServerThread getClientConnectServerThread(String userId)
    {
        return hm.get(userId);
    }
}
