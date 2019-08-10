package 锁.FairNoFair;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo {

    public static void main(String[] args) {
        /**                   模拟三个停车位 */
        Semaphore  semaphore = new Semaphore(3);
            /**     模拟6 辆车                     */
        for (int i = 1; i <=6 ; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();   /**  代表占到车位了        */
                    System.out.println(Thread.currentThread().getName()+"\t 抢到车位了！ ");
                    try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
                    System.out.println(Thread.currentThread().getName()+"\t  停车三秒后离开车位 ");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();   /** 释放资源 */
                }
            }, String.valueOf(i)).start();
        }

    }
}
