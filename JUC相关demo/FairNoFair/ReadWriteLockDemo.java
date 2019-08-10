package 锁.FairNoFair;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;


/**   读写锁，写(put)的时候只允许有一个线程，但是读 (get)的时候可以有多个线程      */
class  MyCache{
    private volatile Map<String,Object>  map = new HashMap <>();
    private ReentrantReadWriteLock rowlock = new  ReentrantReadWriteLock();

    public void  put(String key,Object value){
        rowlock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t  正在写入 ："+key);
            try {
                TimeUnit.SECONDS.sleep(3 ); } catch (InterruptedException e) { e.printStackTrace(); }
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"\t  写入完成");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rowlock.writeLock().unlock();
        }
    }

    public void  get(String key ){
            rowlock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t  正在读取 " );
            try {
                TimeUnit.SECONDS.sleep(3 ); } catch (InterruptedException e) { e.printStackTrace(); }
            Object o = map.get(key);
            System.out.println(Thread.currentThread().getName()+"\t  读取完成"+o);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rowlock.readLock().unlock();
        }
    }

    public void clear(){
        map.clear();
    }
}

public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache  myCache =  new MyCache();
        for (int i = 0; i <5 ; i++) {
            final int  temp = i ;
            new Thread(()->{
               myCache.put(temp+"",temp+"");
            },String.valueOf(i)).start();
        }

        for (int i = 0; i <5 ; i++) {
            final int  temp = i ;
            new Thread(()->{
                myCache.get(temp+"" );
            },String.valueOf(i)).start();
        }
    }
}
