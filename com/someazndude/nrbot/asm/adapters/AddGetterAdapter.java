package com.someazndude.nrbot.asm.adapters;

import com.someazndude.nrbot.asm.interfaces.FieldDescriptions;
import org.objectweb.asm.*;

public class AddGetterAdapter extends ClassVisitor implements Opcodes, FieldDescriptions {

    private final String getterName;
    private final String fieldName;
    private final boolean isStatic;
    private String className;
    private final String getterDesc;
    private String fieldDesc;

    public AddGetterAdapter(ClassVisitor cv, String getterName, String getterDesc, String fieldName, boolean isStatic) {
        super(ASM4, cv);
        this.getterName = getterName;
        this.fieldName = fieldName;
        this.isStatic = isStatic;
        this.getterDesc = getterDesc;
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        className = name;
    }

    @Override
    public void visitSource(String source, String debug) {
    }

    @Override
    public void visitOuterClass(String owner, String name, String desc) {
    }

    @Override
    public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
        return null;
    }

    @Override
    public void visitAttribute(Attribute attr) {
    }

    @Override
    public void visitInnerClass(String name, String outerName, String innerName, int access) {
    }

    @Override
    public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
        if (name.equals(fieldName)) {
            fieldDesc = desc;
        }
        return null;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        return null;
    }

    @Override
    public void visitEnd() {
        MethodVisitor methodVisitor = cv.visitMethod(ACC_PUBLIC, getterName, getterDesc, null, null);
        methodVisitor.visitCode();
        methodVisitor.visitVarInsn(ALOAD, 0);
        methodVisitor.visitFieldInsn(isStatic ? GETSTATIC : GETFIELD, className, fieldName, fieldDesc);
        methodVisitor.visitInsn((getterDesc.equals(integer) || getterDesc.equals(bool)) ? IRETURN : ARETURN);
        methodVisitor.visitMaxs(0, 0);
        methodVisitor.visitEnd();
    }
}
