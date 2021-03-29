package lock;

import java.util.Queue;
import java.util.concurrent.Semaphore;

/**
 * @author Dong.Chao
 * @Classname SemaphoreDemo
 * @Description Semaphore 代码验证
 * @Date 2021/3/23 14:07
 * @Version V1.0
 */
public class SemaphoreDemo {
    private static Semaphore semaphore = new Semaphore(5);

    public static void main(String[] args) {
        for (int i = 1; i < 10; i++) {
            try {
                final int temp = i;
                semaphore.acquire();
                new Thread(() -> {
                    System.out.println(Thread.currentThread().getName()+"进来了！");
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+"出来了！");
                    semaphore.release();
                },"Thread"+temp).start();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
            }

        }
    }

}
