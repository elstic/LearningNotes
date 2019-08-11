package 多线程.demoB;

import java.util.concurrent.TimeUnit;
/**      这是一个手写的死锁程序dmeo        */
class  HoldLock implements Runnable{
   private String lockA;
   private String lockB;
    public HoldLock(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName()+"\t 持有A 尝试获得："+lockB);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName()+"\t 持有B 尝试获得："+lockA);
            }
        }
    }
}

public class DeadLock {
    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";
        new Thread(new HoldLock(lockA,lockB),"aaa").start();
        new Thread(new HoldLock(lockB,lockA),"bbb").start();
    }
}
