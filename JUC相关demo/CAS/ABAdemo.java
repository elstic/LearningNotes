package 多线程.CAS;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ABAdemo {
                                                    /**                                 真实值       */
  static   AtomicReference<Integer>  atomicReference = new AtomicReference <>(100);
    /**                                                                                            真实值             版本号 */
  static AtomicStampedReference<Integer>  atomicStampedReference = new AtomicStampedReference <>(100,1);
    public static void main(String[] args) {
        /**                 ABA 问题                                                   */
            new Thread(() -> {               /**     期望值     要修改的值     */
                atomicReference.compareAndSet(100, 101);   // B
                atomicReference.compareAndSet(101, 100);   // A
            }, "t1").start();

            new Thread(() -> {        /**   睡1秒  让t1 线程完成了 ABA         */
                try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }

                System.out.println(atomicReference.compareAndSet(100, 2019) + "\t" + atomicReference.get());
            }, "t2").start();

        try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println("=====================以下是 ABA 的解决============================================");

        new Thread(() -> {
            /**                                    获取版本号  */
             int  stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName()+"\t 第一次版本号："+atomicStampedReference.getStamp());
            /**    睡 1秒，让 t4 线程也拿到  atomicStampedReference  */
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            /**   atomicStampedReference 的compareAndSet  四个参数： 期望值，想要修改的值，现在版本号，修改之后的版本号   */
            atomicStampedReference.compareAndSet(100,101,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName()+"\t 第二次版本号："+atomicStampedReference.getStamp());
            atomicStampedReference.compareAndSet(101,100,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName()+"\t 第三次版本号："+atomicStampedReference.getStamp());
            }, "t3").start();


        new Thread(() -> {
            int  stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName()+"\t 第一次版本号："+atomicStampedReference.getStamp());
            try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
           boolean b =  atomicStampedReference.compareAndSet(100,2019,stamp,stamp+1);
            System.out.println(Thread.currentThread().getName()+" 修改成功否："+b);
            System.out.println(Thread.currentThread().getName()+"以为的版本号 ："+stamp+" 当前最新的版本号 ："+atomicStampedReference.getStamp());
            System.out.println("  当前实际最新的值 :" +atomicStampedReference.getReference());

        }, "t4").start();

    }
}
