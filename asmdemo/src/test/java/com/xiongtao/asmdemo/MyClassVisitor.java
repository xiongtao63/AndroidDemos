package com.xiongtao.asmdemo;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class MyClassVisitor extends ClassVisitor {
    public MyClassVisitor(ClassVisitor cv) {
        super(Opcodes.ASM7, cv);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        System.out.println("方法:" + name + " 签名:" + signature);
        MethodVisitor mv = super.visitMethod(access, name, descriptor, signature, exceptions);
        return new MyMethodVisitor(api,mv,access,name,descriptor);

    }

}
