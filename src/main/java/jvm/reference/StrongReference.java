package jvm.reference;

import com.carrotsearch.sizeof.RamUsageEstimator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Dong.Chao
 * @Classname StrongReference
 * @Description 强应用 Demo. 只要有引用存在，那么堆中数据不会被回收，哪怕是OOM。
 * @Date 2021/3/31 9:43
 * @Version V1.0
 */
public class StrongReference {
    public static void main(String[] args) {
        List list = new ArrayList();
        for (int i = 0; i < 10; i++) {
            //每个加10M
            list.add(new byte[1024 * 1024 * 10]);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("time:" + i);
        }

     //   System.out.println(RamUsageEstimator.sizeOf(new int[1024*1024*10])/1024/1024);
    }
}
