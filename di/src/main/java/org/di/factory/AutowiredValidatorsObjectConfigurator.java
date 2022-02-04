package org.di.factory;

import com.netcracker.db.validator.ContractValidator;
import com.netcracker.utils.List;
import com.netcracker.utils.impl.MyArrayList;
import org.di.ApplicationContext;
import org.di.annotation.Autowired;
import org.di.annotation.AutowiredValidators;

import java.lang.reflect.Field;


public class AutowiredValidatorsObjectConfigurator implements ObjectConfigurator {
    @Override
    public void configure(Object t, ApplicationContext context) {
        for (Field field : t.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(AutowiredValidators.class) && List.class.isAssignableFrom(field.getType())) {
                var implsOfContractValidator = context
                        .getConfig()
                        .getScanner()
                        .getSubTypesOf(ContractValidator.class);

                MyArrayList<ContractValidator> list = implsOfContractValidator
                        .stream()
                        .map(context::getObject)
                        .collect(MyArrayList::new, MyArrayList::add, MyArrayList::addAll);

                field.setAccessible(true);

                try {
                    field.set(t, list);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Error with injecting to class");
                }
            }
        }
    }
}
