package com.hydroyura.dictinaryapp.context;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hydroyura.dictinaryapp.screens.main.MainScreen;
import com.hydroyura.dictinaryapp.stages.main.MainStage;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Context {

    private boolean isContextReady = Boolean.FALSE;

    private Class<Inject> injectType = Inject.class;

    private Map<String, Object> beans = new HashMap<>();

    public Context() {}


    public void addBean(String name, Object bean) {
        if (beans.containsKey(name)) {
            throw new RuntimeException("Duplicate bean name: " + name);
        }
        beans.put(name, bean);
    }

    public void init() {
        isContextReady = Boolean.FALSE;
        addBean("MainScreen", new MainScreen());
        addBean("MainStage", new MainStage());
        addBean("ObjectMapper", new ObjectMapper());
        injectBeans();
        isContextReady = Boolean.TRUE;
    }

    private void injectBeans() {
        beans.forEach((key, value) -> {
            Class<?> type = value.getClass();
            for(Field field: type.getDeclaredFields()) {
                if(field.isAnnotationPresent(injectType)) {
                    String beanNameToInject = field.getAnnotation(injectType).key();
                    Object beanToInject = beans.get(beanNameToInject);
                    if (beanToInject == null) {
                        throw new RuntimeException("Bean with name: " + beanNameToInject + " -> NOT FOUND");
                    }
                    field.setAccessible(true);
                    try {
                        field.set(value, beanToInject);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                        throw new RuntimeException("Error occurred while bean injection");
                    }
                }
            }
        });
    }

    public boolean isContextReady() {
        return isContextReady;
    }

    public <T> T getBean(String name, Class<T> type) {
        return type.cast(beans.get(name));
    }

}
