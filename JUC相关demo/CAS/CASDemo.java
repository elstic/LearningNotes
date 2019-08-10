package 多线程.CAS;

import java.util.concurrent.atomic.AtomicInteger;

public class CASDemo {
    public static void main(String[] args) {
        AtomicInteger  atomicInteger = new AtomicInteger(60);

        System.out.println(atomicInteger.compareAndSet(60,2019)+"\t  currate date:"+atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(60,1010)+"\t  currate date:"+atomicInteger.get());

       int b = atomicInteger.getAndIncrement();
        System.out.println(b);
    }
}
