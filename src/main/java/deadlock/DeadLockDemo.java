package deadlock;

import java.util.concurrent.locks.Lock;

/**
 * @author Dong.Chao
 * @Classname deadLockDemo
 * @Description 死锁分析
 * @Date 2021/3/26 17:05
 * @Version V1.0
 */
public class DeadLockDemo {

    private static Object objectA = new Object();

    private static Object objectB = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            String threadName = Thread.currentThread().getName();
            synchronized (objectA){
                System.out.println(threadName+":我获得到了A锁");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (objectB){
                    System.out.println(threadName+":我获取到了B锁");
                }
            }
        },"线程A").start();


        new Thread(() -> {
            String threadName = Thread.currentThread().getName();
            synchronized (objectB){
                System.out.println(threadName+":我获得到了B锁");
                synchronized (objectA){
                    System.out.println(threadName+":我获取到了A锁");
                }
            }
        },"线程B").start();





    }

}
