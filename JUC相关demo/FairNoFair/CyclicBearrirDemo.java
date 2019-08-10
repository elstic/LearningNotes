package 锁.FairNoFair;

import java.util.concurrent.CyclicBarrier;

public class CyclicBearrirDemo {

    public static void main(String[] args) {
        CyclicBarrier  cyclicBarrier = new CyclicBarrier(7,()->{
            System.out.println(" 召唤神龙 ");
        });

        for (int i = 1; i <=7 ; i++) {
            final int trmp  =  i  ;
            new Thread(()->{
                System.out.println("收集到第"+ trmp +"颗龙珠");
                try {
                    cyclicBarrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            },trmp+"").start();
        }
    }
}
