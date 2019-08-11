package 多线程.生产者消费者;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// 资源类
class ShareData{
    private int number = 0 ;
    private Lock lock = new ReentrantLock();
    private Condition condition  = lock.newCondition();
    public void increment() throws Exception{
                // 加
        lock.lock();
        try {
            while ( number != 0){
                // 等待
                condition.await();
            }
            number++ ;
            System.out.println(Thread.currentThread().getName()+"\t" + number);
            condition.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void  decrement() throws Exception{
        // 减
        lock.lock();
        try {
            while ( number == 0){
                // 等待
                condition.await();
            }
            number-- ;
            System.out.println(Thread.currentThread().getName()+"\t" + number);
            condition.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

/**     一个线程给number +1 ，一个给它 -1        */

public class Pro {
    public static void main(String[] args) {
        ShareData   shareData = new ShareData();
        new Thread(()->{
            for (int i = 0; i <5 ; i++) {
                try {
                    shareData.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        },"AAA").start();

        new Thread(()->{
            for (int i = 0; i <5 ; i++) {
                try {
                    shareData.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        },"BBB").start();
    }
}
