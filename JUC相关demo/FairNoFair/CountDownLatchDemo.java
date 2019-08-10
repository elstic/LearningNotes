package 锁.FairNoFair;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    public static void main(String[] args)  throws Exception{
        CountDownLatch   countDownLatch = new CountDownLatch(6);
            for (int i = 1; i <=6 ; i++) {
                new Thread(()->{
                    System.out.println(Thread.currentThread().getName()+"\t 国被灭");
                    countDownLatch.countDown();
                    },CountryEnum.forEachEnum(i).getRetMessage()).start();
            }
            countDownLatch.await();
        System.out.println(" 秦国实现大一统 ");
    }

    public  static  void  move() throws Exception{
        CountDownLatch   countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <=6 ; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t 上完自习  离开教室");
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println("班长锁门，最后离开教室");
    }
}
