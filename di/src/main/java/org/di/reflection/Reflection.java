package org.di.reflection;


import org.reflections.Reflections;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;

public class Reflection {
    List<Reflections> scanners;

    public Reflection(String[] packagesToScan) {
        scanners = new ArrayList<>();

        for (String packageToScan : packagesToScan) {
            scanners.add(new Reflections(packageToScan));
        }
    }

    public <T> Set<Class<? extends T>> getSubTypesOf(Class<T> type) {
        Set<Class<? extends T>> implementations;
        for(Reflections scanner : scanners) {
            implementations = new HashSet<>(scanner.getSubTypesOf(type));
            if(!implementations.isEmpty()) return implementations;
        }
        return Collections.emptySet();
    }
}

//public class Reflection {
//    private final Set<Class<?>> classes;
//
//    public Reflection(String[] packagesToScan) {
//        this.classes = new HashSet<>();
//
//        for (String packageToScan : packagesToScan) {
//            this.classes.addAll(Scanner.findAllClasses(packageToScan));
//        }
//    }
//
//    public <T> Set<Class<? extends T>> getSubTypesOf(Class<T> type) {
//        return classes.parallelStream()
//                .filter(type::isAssignableFrom)
//                .filter(aClass -> !aClass.isInterface())
//                .map(aClass -> (Class<? extends T>) aClass)
//                .collect(toSet());
//    }
//}
//