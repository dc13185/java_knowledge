package executor;


import java.util.concurrent.*;

/**
 * @author Dong.Chao
 * @Classname ThreadPoolExecutorDemo
 * @Description ThreadPoolExecutor 参数代码验证
 * @Date 2021/3/26 11:27
 * @Version V1.0
 */
public class ThreadPoolExecutorDemo {
    static ThreadPoolExecutor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(2,3,5L
            , TimeUnit.SECONDS, new LinkedBlockingQueue<>(5), Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());
    /**
     *      四种线程池拒绝策略
     *      AbortPolicy: 抛出一个类型为 RejectedExecutionException 的 RuntimeException异常
     *      CallerRunsPolicy:让提交任务的线程执行任务，在这段期间（提交线程运行提交任务），提交任务的线程被占用，无法继续提交新的线程。
     *      DiscardPolicy: 将新任务提交后直接丢掉
 *          DiscardOldestPolicy: 直接丢弃存活时间最长的任务。
     *
     *
     *
     *
     *
     * */




    public static void main(String[] args) {
        try {
            for (int i = 1; i <= 9; i++) {
                THREAD_POOL_EXECUTOR.execute(new Thread(() -> {
                    System.out.println(Thread.currentThread().getName()+ "业务处理中");
                }));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            THREAD_POOL_EXECUTOR.shutdown();
        }


    }

}
