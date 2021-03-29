package lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author Dong.Chao
 * @Classname CyclicBarrierDemo
 * @Description CyclicBarrier demo
 * @Date 2021/3/23 9:46
 * @Version V1.0
 */
public class CyclicBarrierDemo {

    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(5);


    public static void main(String[] args) {
        for (int i = 1; i < 6; i++) {
            final int temp = i;
            new Thread(() -> {
                try {
                    String  threadName = Thread.currentThread().getName();
                    System.out.println(threadName + "进来了!");
                    cyclicBarrier.await();
                    Thread.sleep(1000);
                    System.out.println(threadName + "进入游戏");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },"线程"+temp).start();
        }
    }

}
