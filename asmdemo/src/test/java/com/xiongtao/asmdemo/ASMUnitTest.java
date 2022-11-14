package com.xiongtao.asmdemo;

import org.junit.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ASMUnitTest {
    @Test
    public void test() throws IOException {
        //1 准备待分析的class
        FileInputStream fis = new FileInputStream("src/test/java/com/xiongtao/asmdemo/InjectTest.class");
        //2 执行分析与插桩
        ClassReader cr = new ClassReader(fis); // ClassReader是class字节码的读取与分析引擎
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);// 写出器， COMPUTE_FRAMES表示自动计算栈帧和局部变量表的大小
        cr.accept(new MyClassVisitor(cw),ClassReader.EXPAND_FRAMES); //执行分析，处理结果写入cw， EXPAND_FRAMES表示栈图以扩展格式进行访问
        //3、获得执行了插桩之后的字节码数据
        byte[] newClassBytes = cw.toByteArray();
        FileOutputStream fos = new FileOutputStream("src/test/java/com/xiongtao/asmdemo/InjectTest1.class");
        fos.write(newClassBytes);
        fos.close();
    }
}


