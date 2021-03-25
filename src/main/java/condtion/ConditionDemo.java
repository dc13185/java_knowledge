package condtion;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Dong.Chao
 * @Classname CondtionDemo
 * @Description
 * @Date 2021/3/24 14:49
 * @Version V1.0
 */
public class ConditionDemo {

    private static Lock lock = new ReentrantLock();

    private static int temp = 1;

    private static Condition c1 = lock.newCondition();
    private static Condition c2 = lock.newCondition();
    private static Condition c3 = lock.newCondition();

    public static void printA(){
        lock.lock();
        try {
            if (temp != 1){
                c1.await();
            }
            System.out.println("A 进来了！");
            temp = 2;
            c2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    public static void printB(){
        lock.lock();
        try {
            if (temp != 2){
                c2.await();
            }
            System.out.println("B 进来了！");
            temp = 3;
            c3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    public static void printC(){
        lock.lock();
        try {
            if (temp != 3){
                c3.await();
            }
            System.out.println("C 进来了！");
            temp = 1;
            c1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(1000);
                    printC();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(1000);
                    printA();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                printB();
            }).start();
        }


    }



}
