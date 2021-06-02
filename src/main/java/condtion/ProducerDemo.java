package condtion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Dong.Chao
 * @Classname ProducerDemo
 * @Description Condition 生产者消费者案例
 * @Date 2021/3/24 15:03
 * @Version V1.0
 */
public class ProducerDemo {

    private static Lock lock = new ReentrantLock();

    private static Condition c1 = lock.newCondition();

    private static List list = new ArrayList();

    private static volatile int temp = 0;

    private static volatile int max = 1;


    public static void push(Object o){
        lock.lock();
        try {
            //为什么说判断  不能用 if 进行判断呢?
            while (temp == max){
                c1.await();
            }
            temp ++ ;
            list.add(o);
            System.out.println(o.toString()+"进来了！");
            c1.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }



    public static Object pop(){
        Object o = null;
        lock.lock();
        try {
            // 如果此处用if的话 ，多条线程的情况下 下次c1被唤醒就会直接进行运行下面的条件
            while (temp == 0){
                c1.await();
            }
            temp --;
            o = list.remove(list.size()-1);
            System.out.println(o.toString()+ "出去了!");
            c1.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return o;
    }

    public static void main(String[] args) {

        for (int i = 0; i < 4 ; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                while (true){
                    pop();
                }
            }).start();
        }

        for (int i = 1; i <= 40 ; i++) {
            final int temp = i;
            new Thread(() -> {
                push(temp);
            }).start();
        }




    }



}
