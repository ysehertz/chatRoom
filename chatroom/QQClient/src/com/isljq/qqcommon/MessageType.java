package com.isljq.qqcommon;

/**
 * ClassName: MessageType
 * Package: com.isljq.qqcommon
 * Description: 表示消息类型
 *
 * @author fuchen
 * @version 1.0
 * @createTime 2024/9/12
 */
public interface MessageType {
    String MESSAGE_LOGIN_SUCCEED = "1"; // 表示登录成功
    String MESSAGE_LOGIN_FAIL = "2"; // 表示登录失败

    String MESSAGE_COMM_MES = "3"; // 普通消息

    String MESSAGE_GET_ONLINE_FRIEND = "4"; // 要求返回在线用户列表
    String MESSAGE_RET_ONLINE_FRIEND = "5"; // 返回在线用户列表
    String MESSAGE_CLIENT_EXIT = "6"; // 表示客户端请求退出
    String MESSAGE_TO_ALL_MES = "7"; // 群发消息

    String MESSAGE_FILE_MES = "8"; // 发送文件
    // 同意接受文件
    String MESSAGE_AGREE_FILE_MES = "9";
    String MESSAGE_DISAGREE_FILE_MES = "10"; // 拒绝接受文件

}
