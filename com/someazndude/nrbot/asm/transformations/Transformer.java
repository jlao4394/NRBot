package com.someazndude.nrbot.asm.transformations;

import com.someazndude.nrbot.asm.adapters.MultiClassAdapter;
import org.objectweb.asm.ClassVisitor;

import java.util.HashMap;
import java.util.Map;

public class Transformer {

    private final Map<String, Transformation> classAdapters = new HashMap<>();

    public void add(String className, Transformation transformation) {
        classAdapters.put(className, transformation);
    }

    public ClassVisitor getMultiAdapter(String className, ClassVisitor classVisitor) {
        Transformation transformation = classAdapters.get(className);
        if (transformation != null) {
            transformation.setClassVisitor(classVisitor);
            transformation.transform();
            return new MultiClassAdapter(classVisitor, transformation.getClassVisitors());
        } else return classVisitor;
    }
}
