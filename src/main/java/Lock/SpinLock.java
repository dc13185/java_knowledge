package Lock;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Dong.Chao
 * @Classname SpinLock
 * @Description 自旋锁模拟
 * @Date 2021/3/22 10:43
 * @Version V1.0
 */
public class SpinLock {

    AtomicReference<Thread> atomicReference = new AtomicReference<Thread>();

    /**
     * @author: dongchao
     * @create: 2021/3/22-10:45
     * @description: 进行加CAS自旋
     * @param:
     * @return:
     */
    public void addLock(){
        Thread thread = Thread.currentThread();
        while (!atomicReference.compareAndSet(null,thread)){
            //直到对比成功 ，即想到于获取到锁，开始执行业务操作
        }
        System.out.println(thread.getName()+"获取到锁！");
    }

    /**
     * @author: dongchao
     * @create: 2021/3/22-10:45
     * @description: 解锁
     * @param:
     * @return:
     */
    public void unLock(){
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName()+"获取释放锁！");
        atomicReference.compareAndSet(thread,null);
    }

    public static void main(String[] args) {
        SpinLock spinLock = new SpinLock();

        Thread thread1 = new Thread(() -> {
            spinLock.addLock();
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLock.unLock();
        },"thread1");
        thread1.start();


        Thread thread2 = new Thread(() -> {
            spinLock.addLock();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLock.unLock();
        },"thread2");

        thread2.start();
    }


}
