package org.di;

import org.di.annotation.Singleton;
import org.di.configurator.Configurator;
import org.di.factory.ObjectFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class ApplicationContext {
    private final Map<Class, Object> cache = new ConcurrentHashMap<>();

    private final Configurator configurator;

    private ObjectFactory factory;

    public ApplicationContext(Configurator configurator) {
        this.configurator = configurator;
    }

    public <T> T getObject(Class<T> type) {
        if (cache.containsKey(type)) {
            return (T) cache.get(type);
        }

        Class<? extends T> implClass = type;

        if (type.isInterface()) {
            implClass = configurator.getImplClass(type);
        }
        T t = factory.createObject(implClass);

        if (implClass.isAnnotationPresent(Singleton.class)) {
            cache.put(type, t);
        }

        return t;
    }

    public Configurator getConfig() {
        return configurator;
    }

    public void setFactory(ObjectFactory factory) {
        this.factory = factory;
    }
}
