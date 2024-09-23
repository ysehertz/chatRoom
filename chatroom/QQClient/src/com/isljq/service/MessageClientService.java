package com.isljq.service;

import com.isljq.qqcommon.Message;
import com.isljq.qqcommon.MessageType;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;

/**
 * ClassName: MessageClientService
 * Package: com.isljq.service
 * Description:
 *
 * @author fuchen
 * @version 1.0
 * @createTime 2024/9/14
 */
public class MessageClientService
{
    public void sendMessageToOne(String senderID,String getterID) {
        Message message = new Message();
        message.setMessageType(MessageType.MESSAGE_COMM_MES);
        message.setSender(senderID);
        message.setGetter(getterID);
        message.setSendTime(new Date().toString());

        ClientConnectServerThread clientConnectServerThread = ManageClientConnectServerThread.getClientConnectServerThread(senderID);
        Socket socket = clientConnectServerThread.getSocket();
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void sendMessageToAll(String next, String userId) {
        Message message = new Message();
        message.setContent(next);
        message.setMessageType(MessageType.MESSAGE_TO_ALL_MES);
        message.setSender(userId);
        message.setSendTime(new Date().toString());

        ClientConnectServerThread clientConnectServerThread = ManageClientConnectServerThread.getClientConnectServerThread(userId);
        Socket socket = clientConnectServerThread.getSocket();
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
