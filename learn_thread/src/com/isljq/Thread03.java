package com.isljq;

import java.util.Scanner;

/**
 * ClassName: Thread03
 * Package: com.isljq
 * Description:
 *
 * @author fuchen
 * @version 1.0
 * @createTime 2024/9/22
 */
public class Thread03 {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws InterruptedException {
        try {
            Thread t = new Thread(new T());
            t.start();
            System.out.println("请在主线程中输入");
            int a = 2;
            a = new Scanner(System.in).nextInt();
            t.join();
            System.out.println("主线程输入为：" + a);
        }catch (Exception e){
            System.out.println("1111111");
        }
    }
}

class T implements Runnable{

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("请在子线程中输入");
        int a;
        Thread03.sc.close();
        Thread03.sc = new Scanner(System.in);
        a = Thread03.sc.nextInt();
        System.out.println("子线程输入为："+a);
    }
}
