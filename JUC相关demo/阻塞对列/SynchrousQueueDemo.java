package 阻塞对列;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class SynchrousQueueDemo {
    public static void main(String[] args) {

        SynchronousQueue<String> synchrousQueue = new SynchronousQueue<>();
        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName()+"\t put 1");
                synchrousQueue.put("1");
                /**      很神奇的特性，要是队列中的消息没有被消费掉，程序不会往下执行，或者说
                 * 会在这里加一个指针，等到队列中消息被消费后才会继续执行这段程序    */
                System.out.println(Thread.currentThread().getName()+"\t put 2");
                synchrousQueue.put("2");
                System.out.println(Thread.currentThread().getName()+"\t put 3");
                synchrousQueue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"TTT").start();


        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName()+"\t  take"+ synchrousQueue.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName()+"\t  take"+ synchrousQueue.take());
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName()+"\t take"+synchrousQueue.take());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"VVV").start();
    }
}
