package com.isljq;

/**
 * ClassName: Thread02
 * Package: com.isljq
 * Description:
 *
 * @author fuchen
 * @version 1.0
 * @createTime 2024/9/22
 */
public class Thread02 {
    public static void main(String[] args) {
        Dog dog = new Dog();
        Thread thread = new Thread(dog);
        thread.start();
        // 底层使用了代理模式
    }
}

class Dog implements Runnable{
    public void run(){
        while(true){
            System.out.println("小狗汪汪汪");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
