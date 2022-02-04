package org.di.configurator;

import org.di.reflection.Reflection;


public interface Configurator {
    <T> Class<? extends T> getImplClass(Class<T> ifc);

    Reflection getScanner();
}
