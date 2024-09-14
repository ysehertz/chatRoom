package com.isljq.service;

import javax.swing.*;
import java.util.HashMap;

/**
 * ClassName: ManageClientThreads
 * Package: qqserver.service
 * Description: 管理与客户端通信的线程
 *
 * @author fuchen
 * @version 1.0
 * @createTime 2024/9/13
 */
public class ManageClientThreads {
    private static HashMap<String, ServerConnectClientThread> hm = new HashMap<>();

    public static HashMap<String, ServerConnectClientThread> getHm() {
        return hm;
    }

    public static void setHm(HashMap<String, ServerConnectClientThread> hm) {
        ManageClientThreads.hm = hm;
    }

    public static void addClientThread(String userId, ServerConnectClientThread serverConnectClientThread) {
        hm.put(userId, serverConnectClientThread);
    }

    public static ServerConnectClientThread getServerConnectClientThread(String userId) {
        return hm.get(userId);
    }

    // 返回在线用户列表
    public static String getOnlineUserList() {
        String s = "";
        for (String userId : hm.keySet()) {
            s += userId + " ";
        }
        return s;
    }

    public static void removeClientThread(String userId) {
        hm.remove(userId);
    }
}
