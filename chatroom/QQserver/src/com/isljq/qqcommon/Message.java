package com.isljq.qqcommon;

import java.io.Serializable;

/**
 * ClassName: Message
 * Package: com.isljq.qqcommon
 * Description: 表示客户端和服务端通信时的消息对象
 *
 * @author fuchen
 * @version 1.0
 * @createTime 2024/9/12
 */
public class Message implements Serializable {
    private static final long serialVersionUID = 13423432423L;
    private String sender; // 发送者
    private String getter; // 接受者

    private String content; // 消息内容
    private String sendTime; //发送时间
    private String messageType; // 消息类型

    private byte[] fileBytes; // 文件字节数组
    private int fileLen; // 文件长度
    private String src; // 文件路径
    private String dest; // 接收文件路径

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }


    public byte[] getFileBytes() {
        return fileBytes;
    }

    public void setFileBytes(byte[] fileBytes) {
        this.fileBytes = fileBytes;
    }

    public int getFileLen() {
        return fileLen;
    }

    public void setFileLen(int fileLen) {
        this.fileLen = fileLen;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public Message(String sender, String getter, String content, String sendTime, String messageType) {
        this.sender = sender;
        this.getter = getter;
        this.content = content;
        this.sendTime = sendTime;
        this.messageType = messageType;
    }

    public Message(String sender, String getter, String content, String sendTime) {
        this.sender = sender;
        this.getter = getter;
        this.content = content;
        this.sendTime = sendTime;
    }

    public Message() {
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getGetter() {
        return getter;
    }

    public void setGetter(String getter) {
        this.getter = getter;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }
}
