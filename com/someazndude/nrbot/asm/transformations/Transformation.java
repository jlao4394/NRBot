package com.someazndude.nrbot.asm.transformations;

import com.someazndude.nrbot.asm.adapters.AddGetterAdapter;
import com.someazndude.nrbot.asm.adapters.AddInterfacesAdapter;
import org.objectweb.asm.ClassVisitor;

import java.util.ArrayList;
import java.util.List;

public abstract class Transformation {

    private final List<ClassVisitor> classVisitors = new ArrayList<>();
    private ClassVisitor classVisitor;

    public abstract void transform();

    protected void addInterfaces(Class... interfacesToAdd) {
        classVisitors.add(new AddInterfacesAdapter(classVisitor, interfacesToAdd));
    }

    protected void addGetter(String getterName, String getterDesc, String fieldName, boolean isStatic) {
        classVisitors.add(new AddGetterAdapter(classVisitor, getterName, getterDesc, fieldName, isStatic));
    }

    public ClassVisitor[] getClassVisitors() {
        return classVisitors.toArray(new ClassVisitor[classVisitors.size()]);
    }

    public void setClassVisitor(ClassVisitor classVisitor) {
        this.classVisitor = classVisitor;
    }
}
