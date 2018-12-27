package com.imooc.thread;

//军队线程 模拟作战双方的行为
public class ArmRunnable implements Runnable{
    //volatile保证了线程可以正确读取其他线程写入的值
    volatile boolean keepRunning = true;
    @Override
    public void run() {
        while (keepRunning) {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "进攻对方：" + i);
                //让出了处理器时间，下次进攻方不确定
                Thread.yield();
            }
        }
        System.out.println(Thread.currentThread().getName() + "结束了战斗！");
    }
}
