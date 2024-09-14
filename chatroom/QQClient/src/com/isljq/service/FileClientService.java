package com.isljq.service;

import com.isljq.qqcommon.Message;
import com.isljq.qqcommon.MessageType;

import java.io.*;
import java.net.Socket;

/**
 * ClassName: FileClientService
 * Package: com.isljq.service
 * Description: 用于文件传输服务
 *
 * @author fuchen
 * @version 1.0
 * @createTime 2024/9/14
 */
public class FileClientService {
    public void sendFileToOne(String src,String setterId, String getterId,String content){

        Message message = new Message();
        message.setMessageType(MessageType.MESSAGE_FILE_MES);
        message.setSrc(src);
        message.setGetter(getterId);
        message.setSender(setterId);
        message.setContent(content);

        FileInputStream fis = null;
        byte[] fileBytes = new byte[(int) new File(src).length()];
        message.setFileLen((int) new File(src).length());

        try {
            fis = new FileInputStream(src);
            fis.read(fileBytes);

            message.setFileBytes(fileBytes);
            Socket socket = ManageClientConnectServerThread.getClientConnectServerThread(setterId).getSocket();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if(fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        System.out.println("申请成功，开始发送文件");

    }
    // 申请发送文件
    public void applySentFile(String src,String command ,String setterId, String getterId ){
        Message message = new Message();
        message.setMessageType(MessageType.MESSAGE_FILE_MES);
        message.setContent(command);
        message.setSrc(src);
        message.setSender(setterId);
        message.setGetter(getterId);
        System.out.println("申请向"+getterId+"发送文件");

        try {
            Socket socket = ManageClientConnectServerThread.getClientConnectServerThread(getterId).getSocket();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
