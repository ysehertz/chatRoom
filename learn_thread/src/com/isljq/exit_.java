package com.isljq;

/**
 * ClassName: exit_
 * Package: com.isljq
 * Description:
 *
 * @author fuchen
 * @version 1.0
 * @createTime 2024/9/22
 */
public class exit_ {
    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread();
        myThread.start();

        Thread.sleep(1000 * 10);
        myThread.setLoop(false);
        // 如果希望在main中终止子线程，需要要控制loop的权限
    }
}

class MyThread extends Thread{
    private boolean loop = true;

    public boolean isLoop() {
        return loop;
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }

    @Override
    public void run() {
        // 设置一个控制变量

        while (loop) {
            System.out.println("hello world");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
