package jvm;

/**
 * @author Dong.Chao
 * @Classname StringTable
 * @Description String常量池的一些东西
 * @Date 2021/3/29 17:58
 * @Version V1.0
 */
public class StringTable {

    public static void main(String[] args) {
        String s1 = "a";
        String s2 = "b";
        String s3 = "a" + "b";
        String s4 = s1 + s2;
        String s5 = "ab";
        String s6 = s4.intern();
        //问
        System.out.println(s3 == s4);
        // javac 在编译期的优化，结果在编译期间确定为ab
        System.out.println(s3 == s5);
        System.out.println(s3 == s6);  // true

        String x2 = new String("c") + new String("d");
        String x1 = "cd";
        x2.intern();

        System.out.println(x1 == x2);  //true

     }
}
