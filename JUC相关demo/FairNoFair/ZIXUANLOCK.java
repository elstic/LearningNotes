package 锁.FairNoFair;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class ZIXUANLOCK {
    /**    原子引用线程   */
    AtomicReference<Thread>  atomicReference = new AtomicReference <>();

    public void myLock()
    {
        Thread t1 = Thread.currentThread();
        System.out.println(Thread.currentThread().getName()+"\t   come in ");
        while (!atomicReference.compareAndSet(null,t1)){

        }
    }

    public void  myUnlock(){
        Thread t1 = Thread.currentThread();
        atomicReference.compareAndSet(t1,null);
        System.out.println(Thread.currentThread().getName()+"\t     myUnlock ");
    }


    public static void main(String[] args) {
        ZIXUANLOCK  spinLockDemo = new ZIXUANLOCK();
        new Thread(()->{
            spinLockDemo.myLock();
            try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); }
            spinLockDemo.myUnlock();
        },"A").start();

        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }

        new Thread(()->{
            spinLockDemo.myLock();
            spinLockDemo.myUnlock();
        },"B").start();
    }
}
