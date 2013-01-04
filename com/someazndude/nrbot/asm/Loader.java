package com.someazndude.nrbot.asm;

import com.someazndude.nrbot.asm.accessors.Entity;
import com.someazndude.nrbot.asm.accessors.Player;
import com.someazndude.nrbot.asm.accessors.client;
import com.someazndude.nrbot.asm.interfaces.FieldDescriptions;
import com.someazndude.nrbot.asm.transformations.Transformation;
import com.someazndude.nrbot.asm.transformations.Transformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;

public class Loader implements FieldDescriptions {

    private final File untransformedClient;
    private final File transformedClient;

    public Loader(File untransformedClient, File transformedClient) {
        this.untransformedClient = untransformedClient;
        this.transformedClient = transformedClient;
    }

    public void transform() {
        try {
            JarFile jarFile = new JarFile(untransformedClient);
            Enumeration<JarEntry> jarEntryEnumeration = jarFile.entries();
            List<JarEntry> jarEntries = new ArrayList<>();

            while (jarEntryEnumeration.hasMoreElements()) {
                JarEntry jarEntry = jarEntryEnumeration.nextElement();
                if (jarEntry.getName().endsWith(".class")) {
                    jarEntries.add(jarEntry);
                }
            }

            final Transformer transformer = new Transformer();
            transformer.add("client", new Transformation() {
                @Override
                public void transform() {
                    addInterfaces(client.class);
                    addGetter("getLocalPlayer", player, "lD", true);
                    addGetter("getPlayerArray", playerArray, "tI", false);
                    addGetter("getPlayerIndices", integerArray, "vI", false);
                    addGetter("getPlayerCount", integer, "uI", false);
                }
            });
            transformer.add("QZ", new Transformation() {
                @Override
                public void transform() {
                    addInterfaces(Player.class, Entity.class);
                    addGetter("getName", string, "KI", false);
                    addGetter("getCombatLevel", integer, "MI", false);
                    addGetter("getEquipment", integerArray, "zI", false);
                    addGetter("isVisible", bool, "UI", false);
                }
            });
            transformer.add("ZI", new Transformation() {
                @Override
                public void transform() {
                    addInterfaces(Entity.class);
                    addGetter("getX", integer, "x", false);
                    addGetter("getY", integer, "y", false);
                }
            });

            JarOutputStream outputStream = new JarOutputStream(new BufferedOutputStream(new FileOutputStream(transformedClient)));

            for (JarEntry jarEntry : jarEntries) {
                ClassReader classReader = new ClassReader(jarFile.getInputStream(jarEntry));
                ClassWriter classWriter = new ClassWriter(classReader, ClassWriter.COMPUTE_MAXS);
                classReader.accept(transformer.getMultiAdapter(jarEntry.getName().replace(".class", ""), classWriter), ClassReader.SKIP_DEBUG | ClassReader.SKIP_FRAMES);
                outputStream.putNextEntry(new JarEntry(jarEntry.getName()));
                outputStream.write(classWriter.toByteArray());
                outputStream.closeEntry();
            }

            outputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File getTransformedClient() {
        return transformedClient;
    }
}
