package com.someazndude.nrbot.asm.adapters;

import org.objectweb.asm.*;

public class MultiClassAdapter extends ClassVisitor implements Opcodes {

    private final ClassVisitor[] cvs;

    public MultiClassAdapter(ClassVisitor cv, ClassVisitor... cvs) {
        super(ASM4, cv);
        this.cvs = cvs;
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        for (ClassVisitor cv : cvs) {
            cv.visit(version, access, name, signature, superName, interfaces);
        }
    }

    @Override
    public void visitSource(String source, String debug) {
        for (ClassVisitor cv : cvs) {
            cv.visitSource(source, debug);
        }
        super.visitSource(source, debug);
    }

    @Override
    public void visitOuterClass(String owner, String name, String desc) {
        for (ClassVisitor cv : cvs) {
            cv.visitOuterClass(owner, name, desc);
        }
        super.visitOuterClass(owner, name, desc);
    }

    @Override
    public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
        for (ClassVisitor cv : cvs) {
            cv.visitAnnotation(desc, visible);
        }
        return super.visitAnnotation(desc, visible);
    }

    @Override
    public void visitAttribute(Attribute attr) {
        for (ClassVisitor cv : cvs) {
            cv.visitAttribute(attr);
        }
        super.visitAttribute(attr);
    }

    @Override
    public void visitInnerClass(String name, String outerName, String innerName, int access) {
        for (ClassVisitor cv : cvs) {
            cv.visitInnerClass(name, outerName, innerName, access);
        }
        super.visitInnerClass(name, outerName, innerName, access);
    }

    @Override
    public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
        for (ClassVisitor cv : cvs) {
            cv.visitField(access, name, desc, signature, value);
        }
        return super.visitField(access, name, desc, signature, value);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        for (ClassVisitor cv : cvs) {
            cv.visitMethod(access, name, desc, signature, exceptions);
        }
        return super.visitMethod(access, name, desc, signature, exceptions);
    }

    @Override
    public void visitEnd() {
        for (ClassVisitor cv : cvs) {
            cv.visitEnd();
        }
        super.visitEnd();
    }
}
