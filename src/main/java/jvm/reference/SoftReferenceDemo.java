package jvm.reference;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Dong.Chao
 * @Classname SoftReferenceDemo
 * @Description 软引用
 *  在分配堆内存的时候，如果空间不足，就会将堆中的软引用的数据空间回收。
 *  1.当发生GC时，虚拟机可能会回收SoftReference对象所指向的软引用，如果空间足够，就不会回收，还要取决于该软引用是否是新创建或近期使用过。
 *  2.在虚拟机抛出OutOfMemoryError之前，所有软引用对象都会被回收。
 * @Date 2021/3/31 10:27
 * @Version V1.0
 */
public class SoftReferenceDemo {

    public static void main(String[] args) {

        List<SoftReference> list = new ArrayList();
        for (int i = 1; i < 10; i++) {
            //每个加10M
            SoftReference softReference = new SoftReference(new byte[1024 * 1024 * 10]);
            list.add(softReference);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("time:" + i);
        }
        for (Object o : list) {
            System.out.println(o.toString());
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (SoftReference o : list) {
            System.out.println(o.get());
        }
    }
}
