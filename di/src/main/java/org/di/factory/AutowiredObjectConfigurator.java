package org.di.factory;

import org.di.ApplicationContext;
import org.di.annotation.Autowired;

import java.lang.reflect.Field;


public class AutowiredObjectConfigurator implements ObjectConfigurator {
    @Override
    public void configure(Object t, ApplicationContext context) {
        for (Field field : t.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Autowired.class)) {
                field.setAccessible(true);
                Object object = context.getObject(field.getType());
                try {
                    field.set(t, object);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Error with injecting to class");
                }
            }
        }
    }
}
