package org.di;

import org.di.configuration.Configuration;
import org.di.configuration.JavaConfiguration;
import org.di.configurator.Configurator;
import org.di.configurator.JavaConfigurator;
import org.di.factory.ObjectFactory;

import java.util.Map;


public class Application {
    public static ApplicationContext run(Map<Class, Class> ifc2ImplClass) {
        Configuration configuration = new JavaConfiguration();
        JavaConfigurator config = new JavaConfigurator(configuration.getPackagesToScan(), ifc2ImplClass);
        ApplicationContext context = new ApplicationContext(config);
        ObjectFactory objectFactory = new ObjectFactory(context);
        context.setFactory(objectFactory);

        return context;
    }

    public static ApplicationContext run() {
        Configuration configuration = new JavaConfiguration();
        JavaConfigurator config = new JavaConfigurator(configuration.getPackagesToScan());
        ApplicationContext context = new ApplicationContext(config);
        ObjectFactory objectFactory = new ObjectFactory(context);
        context.setFactory(objectFactory);

        return context;
    }
}
