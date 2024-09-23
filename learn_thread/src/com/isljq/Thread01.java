package com.isljq;

/**
 * ClassName: Thread01
 * Package: com.isljq
 * Description:
 *
 * @author fuchen
 * @version 1.0
 * @createTime 2024/9/22
 */
public class Thread01 implements Runnable{
    public static void main(String[] args) {
        XiaoQi xiaoQi = new XiaoQi();
        xiaoQi.start(); // 启动线程
        // 如果直接调用run（），不会创建新的线程，main中的代码不会直接往下执行。
        // 真正实现多线程的是start0()方法。而不是run();
    }

    @Override
    public void run() {

    }
}

//
class XiaoQi extends Thread{
    @Override
    public void run() {
        System.out.println("你好，我是小琦");
        String i = "喜欢你";
        while(true){
            try {
                System.out.println("我"+i);
                i = "真的" + i;
                Thread.sleep(1000);  // 线程休眠1秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}