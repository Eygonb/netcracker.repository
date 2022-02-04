package org.di.reflection;

import java.io.File;
import java.net.URI;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Scanner {
    public static Set<Class<?>> findAllClasses(String packageToScan) {
        String mainPath = String.valueOf(ClassLoader
                .getSystemClassLoader()
                .getResource(packageToScan.replace('.', '/')))
                .replaceAll("libs[\\w\\W]*!", "classes/java/main")
                .replaceAll("jar:", "");

        File[] files = new File(URI.create(mainPath)).listFiles();
        Set<Class<?>> classes = new HashSet<>();
        for (File file : Objects.requireNonNull(files)) {
            classes.addAll(findInDirectory(file, packageToScan));
        }

        return classes;
    }

    private static Set<Class<?>> findInDirectory(File file, String packageToScan) {
        Set<Class<?>> classes = new HashSet<>();
        String classPath = packageToScan + '.' + file.getName();

        if (!file.isDirectory() && classPath.endsWith(".class")) {
            String className = classPath.substring(0, classPath.length() - ".class".length());

            try {
                classes.add(Class.forName(className));
            } catch (ClassNotFoundException | NoClassDefFoundError e) {
                System.out.println("Class not found");
                return Collections.emptySet();
            }
        } else {
            for (File child : Objects.requireNonNull(file.listFiles())) {
                classes.addAll(findInDirectory(child, classPath));
            }
        }

        return classes;
    }
}
