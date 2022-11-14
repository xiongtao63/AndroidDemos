package com.xiongtao.asmdemo;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.AdviceAdapter;
import org.objectweb.asm.commons.Method;

public class MyMethodVisitor extends AdviceAdapter {
    protected  MyMethodVisitor(int api, MethodVisitor mv, int access, String name, String descriptor) {
        super(api,mv,access,name,descriptor);
    }
    boolean inject = false;
    private int start;

    @Override
    public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
        if("Lcom/xiongtao/asmdemo/ASMTest;".equals(descriptor)){
            inject = true; // 这个方法的注解为Lcom/demo/test/ASMTest;的时候，将inject置为true表示需要插桩
        }
        return super.visitAnnotation(descriptor, visible);
    }

    @Override
    protected void onMethodEnter() {
        super.onMethodEnter();
        if(!inject) {
            return;
        }
        //invokeStatic指令，调用静态方法
        // 相当于java中System.currentTimeMillis();这一句代码
        invokeStatic(Type.getType("Ljava/lang/System;"),new Method("currentTimeMillis","()J"));
        //用一个本地变量接收上一步的执行结果
        start = newLocal(Type.LONG_TYPE);//start表示当前long类型的本地变量的索引
        storeLocal(start); //store指令，将方法执行结果从操作数栈存储到局部变量

    }

    @Override
    protected void onMethodExit(int opcode) {
        super.onMethodExit(opcode);
        if(!inject) {
            return;
        }
        invokeStatic(Type.getType("Ljava/lang/System;"),new Method("currentTimeMillis","()J"));
        int end = newLocal(Type.LONG_TYPE);

        storeLocal(end);
        getStatic(Type.getType("Ljava/lang/System;"),"out",Type.getType("Ljava/io" +"/PrintStream;"));//执行System.out
        newInstance(Type.getType("Ljava/lang/StringBuilder;"));// 执行new StringBuilder分配内存
        dup();//dup压入栈顶，让下面的INVOKESPECIAL 知道执行谁的构造方法创建StringBuilder
        invokeConstructor(Type.getType("Ljava/lang/StringBuilder;"),new Method("<init>","()V"));
        //调用StringBuilder的构造方法
        visitLdcInsn("execute");
        invokeVirtual(Type.getType( "Ljava/lang/StringBuilder;"),new Method("append","(Ljava/lang/String;)Ljava/lang/StringBuilder;")); // 调用StringBuilder的append方法
        loadLocal(end);// 加载方法结束的时间
        loadLocal(start);//加载方法开始的时间
        math(SUB,Type.LONG_TYPE);//减法
        invokeVirtual(Type.getType("Ljava/lang/StringBuilder;"),new Method("append","(J)Ljava/lang/StringBuilder;"));
        // 调用StringBuilder的append方法
        visitLdcInsn("ms");
        invokeVirtual(Type.getType("Ljava/lang/StringBuilder;"),new Method("append","(Ljava/lang/String;)Ljava/lang/StringBuilder;"));
        invokeVirtual(Type.getType("Ljava/lang/StringBuilder;"),new Method("toString","()Ljava/lang/String;"));
        invokeVirtual(Type.getType("Ljava/io/PrintStream;"),new Method("println","(Ljava/lang/String;)V"));

    }
}
