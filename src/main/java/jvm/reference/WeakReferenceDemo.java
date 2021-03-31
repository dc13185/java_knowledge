package jvm.reference;

import java.lang.ref.WeakReference;

/**
 * @author Dong.Chao
 * @Classname WeakReferenceDemo
 * @Description 弱引用
 *
 * 1.弱引用需要用 java.lang.WeakReference 类来实现，它比软引用的生存期更短。
 * 2.如果一个对象只是被弱引用引用者，那么只要发生 GC，不管内存空间是否足够，都会回收该对象。
 * 3.弱引用适合解决某些地方的内存泄漏的问题
 * 4.ThreadLocal 静态内部类 ThreadLocalMap 中的 Entiry 中的 key 就是一个虚引用；
 * @Date 2021/3/31 11:20
 * @Version V1.0
 */
public class WeakReferenceDemo {
    public static void main(String[] args) {
        WeakReference weakReference = new WeakReference(new Object());
        System.out.println("gc前:"+weakReference.get());
        System.gc();
        System.out.println("gc后:"+weakReference.get());
    }
}
