package com.xiongtao.plugin;


import org.gradle.internal.impldep.org.objectweb.asm.ClassReader;
import org.gradle.internal.impldep.org.objectweb.asm.ClassWriter;
import org.gradle.internal.impldep.org.objectweb.asm.ClassReader;
import org.gradle.internal.impldep.org.objectweb.asm.ClassVisitor;
import org.gradle.internal.impldep.org.objectweb.asm.ClassWriter;
import org.gradle.internal.impldep.org.objectweb.asm.MethodVisitor;
import org.gradle.internal.impldep.org.objectweb.asm.Opcodes;
import org.gradle.internal.impldep.org.objectweb.asm.Type;

import java.io.IOException;
import java.io.InputStream;

//import jdk.internal.org.objectweb.asm.ClassReader;
//import jdk.internal.org.objectweb.asm.ClassVisitor;
//import jdk.internal.org.objectweb.asm.ClassWriter;
//import jdk.internal.org.objectweb.asm.MethodVisitor;
//import jdk.internal.org.objectweb.asm.Opcodes;
//import jdk.internal.org.objectweb.asm.Type;


/**
 * @author Lance
 * @date 2019-09-23
 */
public class ClassUtils {


    public static byte[] referHackWhenInit(InputStream inputStream) throws IOException {
        ClassReader cr = new ClassReader(inputStream);
        ClassWriter cw = new ClassWriter(cr, 0);
        ClassVisitor cv = new ClassVisitor(Opcodes.ASM5, cw) {
            @Override
            public MethodVisitor visitMethod(int access, final String name, String desc,
                                             String signature, String[] exceptions) {

                MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
                mv = new MethodVisitor(api, mv) {
                    @Override
                    public void visitInsn(int opcode) {
                        //在构造方法中插入AntilazyLoad引用
                        if ("<init>".equals(name) && opcode == Opcodes.RETURN) {
                            super.visitLdcInsn(Type.getType("Lcom/xiongtao/hack/AntilazyLoad;"));
                        }
                        super.visitInsn(opcode);
                    }
                };
                return mv;
            }

        };
        cr.accept(cv, 0);
        return cw.toByteArray();
    }
}