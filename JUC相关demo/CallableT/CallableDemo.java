package 多线程.CallableT;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

class MyThread2 implements Callable<Integer> {


    public Integer call() throws Exception {
        TimeUnit.SECONDS.sleep(2);
        System.out.println(" call ");
        return 1024;
    }
}


public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer>  futureTask = new FutureTask<>(new MyThread2());
            Thread thread = new Thread(futureTask,"ABC");
        Thread thread2 = new Thread(futureTask,"ABC");
        thread2.start();
        thread.start();
        System.out.println(Thread.currentThread().getName()+"    *** ");

        int  res2 = 100 ;
            System.out.println(futureTask.get()+res2);

    }
}
