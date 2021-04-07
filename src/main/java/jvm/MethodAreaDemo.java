package jvm;

import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.Opcodes;

/**
 * @author Dong.Chao
 * @Classname MethodAreaDemo
 * @Description 方法区Demo
 * @Date 2021/3/29 17:27
 * @Version V1.0
 */
public class MethodAreaDemo extends ClassLoader {

    public static void main(String[] args) {
        MethodAreaDemo methodAreaDemo = new MethodAreaDemo();
        int i = 0;
        try{
            for (int j = 0; j < 10000; j++,i++) {
                ClassWriter cw= new ClassWriter(0);
                //版本号，public,类名，包名，父类，接口
                cw.visit(Opcodes.V1_8,Opcodes.ACC_PUBLIC,"Class"+i,null,"java/lang/Object",null);
                //返回byte
                byte[] code = cw.toByteArray();
                methodAreaDemo.defineClass("Class"+i,code,0,code.length);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println(i);
        }


    }

}
