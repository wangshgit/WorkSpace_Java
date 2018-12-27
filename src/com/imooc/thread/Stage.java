package com.imooc.thread;

public class Stage extends Thread{
    @Override
    public void run() {
        System.out.println("欢迎观看隋唐演义!");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("大幕徐徐拉开");

        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("话说隋朝末年，隋军与农民起义军杀得天昏地暗...");

        ArmRunnable armyTaskOfSuiDynasty = new ArmRunnable();
        ArmRunnable armyTaskOfSuiRevolt = new ArmRunnable();

        //使用Runnable接口创建线程
        Thread armyOfSuiDynasty = new Thread(armyTaskOfSuiDynasty, "隋军");
        Thread armyOfRevolt = new Thread(armyTaskOfSuiRevolt, "农民军起义");

        //启动线程，让军队开始作战
        armyOfSuiDynasty.start();
        armyOfRevolt.start();

        //舞台线程休眠
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("半路杀出个程咬金！");

        Thread mrCheng = new KeyPersonThread();
        mrCheng.setName("程咬金");

        System.out.println("程咬金的理想就是结束战争，使百姓安居乐业！");

        //停止线程的方法
        armyTaskOfSuiDynasty.keepRunning = false;
        armyTaskOfSuiRevolt.keepRunning = false;

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /*
            历史大戏留给关键人物
         */
        mrCheng.start();

        //所有线程等待程咬金完成历史使命
        try {
            mrCheng.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("战争结束！！！");
        System.out.println("再见！");
    }

    public static void main(String[] args)
    {
        new Stage().start();
    }
}