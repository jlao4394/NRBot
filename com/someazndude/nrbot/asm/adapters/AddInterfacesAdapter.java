package com.someazndude.nrbot.asm.adapters;

import org.objectweb.asm.*;

import java.util.ArrayList;
import java.util.List;

public class AddInterfacesAdapter extends ClassVisitor implements Opcodes {

    private final Class[] interfacesToAdd;

    public AddInterfacesAdapter(ClassVisitor cv, Class... interfacesToAdd) {
        super(ASM4, cv);
        this.interfacesToAdd = interfacesToAdd;
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        List<String> list = new ArrayList<>();

        if (interfaces != null) {
            for (String s : interfaces) {
                list.add(s);
            }
        }

        for (Class c : interfacesToAdd) {
            list.add(Type.getInternalName(c));
        }

        super.visit(version, access, name, signature, superName, list.toArray(new String[list.size()]));
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
        return null;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        return null;
    }

    @Override
    public void visitEnd() {
    }
}
