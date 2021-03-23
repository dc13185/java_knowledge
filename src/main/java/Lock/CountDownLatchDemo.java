package Lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * @author Dong.Chao
 * @Classname CountDownLatch
 * @Description CountDownLatchDemo
 * @Date 2021/3/23 9:41
 * @Version V1.0
 */
public class CountDownLatchDemo {

    private static final CountDownLatch countDownLatch = new CountDownLatch(5);



    public static void main(String[] args) {
        for (int i = 1; i < 6; i++) {
            final int temp = i;
            new Thread(() -> {
                String  threadName = Thread.currentThread().getName();
                System.out.println(threadName + "进来了!");
                countDownLatch.countDown();
            },"线程"+temp).start();
        }


        new Thread(() -> {
            try {
                countDownLatch.await();
                String  threadName = Thread.currentThread().getName();
                System.out.println(threadName + "进来了!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"被拦截的线程").start();


    }


}
