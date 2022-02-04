package org.di.configurator;

import org.di.reflection.Reflection;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Stream;

public class JavaConfigurator implements Configurator {
    private final Reflection scanner;
    private final Map<Class, Class> ifc2ImplClass;

    public JavaConfigurator(String[] packagesToScan, Map<Class, Class> ifc2ImplClass) {
        this.ifc2ImplClass = ifc2ImplClass;
        this.scanner = new Reflection(packagesToScan);
    }

    public JavaConfigurator(String[] packagesToScan) {
        this.ifc2ImplClass = new HashMap<>();
        this.scanner = new Reflection(packagesToScan);
    }

    @Override
    public <T> Class<? extends T> getImplClass(Class<T> ifc) {
        return ifc2ImplClass.computeIfAbsent(ifc, aClass -> {
            Set<Class<? extends T>> classes = scanner.getSubTypesOf(ifc);

            if (classes.size() == 1) {
                return classes.iterator().next();
            } else {
                String path = Objects.requireNonNull(
                        getClass().getClassLoader().getResource("classContext.properties")
                ).getPath();

                Stream<String> lines;

                try {
                    lines = new BufferedReader(new FileReader(path)).lines();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException("Error with reading properties file");
                }
                String ifcName = ifc.toString().substring(ifc.toString().lastIndexOf(' ') + 1);
                Optional<String[]> impName = lines.map(line -> line.split("="))
                        .filter(arr -> arr[0].trim().equals(ifcName))
                        .findFirst();
                try {
                    return Class.forName(impName.get()[1]);
                } catch (ClassNotFoundException | NoSuchElementException e) {
                    throw new RuntimeException(ifc + " has 0 or more than one impl and and the impl is not set in" +
                            " the config. Please update your config");
                }
            }
        });

    }

    @Override
    public Reflection getScanner() {
        return scanner;
    }
}