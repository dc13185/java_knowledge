package queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author Dong.Chao
 * @Classname BlockingQueue
 * @Description
 * @Date 2021/3/24 10:16
 * @Version V1.0
 */
public class ArrayBlockingQueueDemo {

    private static BlockingQueue blockingQueue = new ArrayBlockingQueue(3);

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                blockingQueue.offer(1,100, TimeUnit.SECONDS);
                blockingQueue.offer(2,100, TimeUnit.SECONDS);
                blockingQueue.offer(3,100, TimeUnit.SECONDS);
                System.out.println("等待插入");
                blockingQueue.put(4);
                System.out.println("插入成功");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();


        new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            blockingQueue.poll();
        }).start();


    }
}
