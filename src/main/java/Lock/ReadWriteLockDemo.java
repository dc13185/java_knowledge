package Lock;

import java.util.HashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Dong.Chao
 * @Classname ReadWriteLock ， 读写锁验证
 * @Description
 * @Date 2021/3/22 14:33
 * @Version V1.0
 */
public class ReadWriteLockDemo {

    private static volatile HashMap<String,Object> hashMap = new HashMap<>();

    final static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public  static void put(String key,Object o){
        try {
            readWriteLock.writeLock().lock();
            System.out.println(key+"开始加入缓存");
            hashMap.put(key,o);
            System.out.println(key+"加入缓存完成");
        }finally {
            readWriteLock.writeLock().unlock();
        }


    }


    public static Object get(String key){
        Object o = null;
        try {
            readWriteLock.readLock().lock();
            System.out.println(key+"开始读取缓存");
            Thread.sleep(1000);
            o = hashMap.get(key);
            System.out.println(key+"读取缓存完成为"+o);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
           readWriteLock.readLock().unlock();
        }
        return o;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 4; i++) {
            final int index = i;
            new Thread(() -> {
                put(index+"",index+"");
            },"Thread"+index).start();
        }

        for (int i = 0; i < 4; i++) {
            final int index = i;
            new Thread(() -> {
                //System.out.println("读取缓存,key为:"+index+"value为:"+get(index+""));
                get(index+"");
            },"Thread"+index).start();
        }

    }

}
