package executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Dong.Chao
 * @Classname ExecutorsDemo
 * @Description
 * @Date 2021/3/25 16:40
 * @Version V1.0
 */
public class FixedThreadPoolDemo {
    public static void main(String[] args) {
        // newFixedThreadPool 创建固定线程数量的线程池
        //  Executors.newSingleThreadExecutor(); 创建一个单线程的线程池
        // Executors.newCachedThreadPool() 创建一个可扩容线程的线程池

        /** ThreadPoolExecutor 构造函数的七个参数 说明:
         *   this(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, Executors.defaultThreadFactory(), defaultHandler);
         * corePoolSize: 俗称核心线程数，也就是线程池常驻的线程数。
         * maximumPoolSize:
         *
         *
         *
         *
         * */

        ExecutorService executorService = Executors.newCachedThreadPool();
        try {
            for (int i = 0; i < 9; i++) {
                executorService.execute(() -> {
                    System.out.println(Thread.currentThread().getName()+"执行线程任务");
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            executorService.shutdown();
        }
    }
}
