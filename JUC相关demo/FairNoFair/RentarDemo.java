package 锁.FairNoFair;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class phone  implements Runnable{
    public   synchronized  void sendems(){
        System.out.println(Thread.currentThread().getId()+"\t sendems");
        sendeEmail();
    }
    public   synchronized  void sendeEmail(){
        System.out.println(Thread.currentThread().getId()+"\t sendeEmail");
    }

   Lock  lock = new ReentrantLock();
    public void run() {
        get();
    }
    public void get(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getId()+"\t get");
            set();
        }finally {
            lock.unlock();
        }
    }
    public void set(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getId()+"\t   ##$$%%set");
        }finally {
            lock.unlock();
        }
    }

}
public class RentarDemo {
    public static void main(String[] args) {
        phone  ph  = new phone();
        new Thread(()->{
            ph.sendems();
        },"t1").start();

        new Thread(()->{
            ph.sendems();
        },"t2").start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\t"+"\t"+"\t");
        Thread t3 = new Thread(ph,"t3");
        Thread t4 = new Thread(ph,"t4");
        t3.start();t4.start();
    }
}
