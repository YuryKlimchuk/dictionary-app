package com.hydroyura.dictinaryapp.core.context;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.utils.Disposable;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hydroyura.dictinaryapp.core.context.annotations.Bean;
import com.hydroyura.dictinaryapp.core.context.annotations.Inject;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public class ApplicationContext implements Disposable {

    private Map<Class<?>, Map<String, Object>> beans = new HashMap<>();

    private boolean isReady = false;

    private String packageName;


    public ApplicationContext(String packageName) {
        this.packageName = packageName;

    }

    void createBeans() {
        //addBean(AssetManager.class, new AssetManager(), "AssetManager");
        addBean(ObjectMapper.class, new ObjectMapper(), "ObjectMapper");
    }

    void injectBean() {
        List<Object> beansList = new ArrayList<>();
        for (Map<String, Object> entry : beans.values()) {
            for (Map.Entry<String, Object> subEntry : entry.entrySet()) {
                beansList.add(subEntry.getValue());
            }
        }

        beansList.forEach(arg4 -> {
            Field[] fields = arg4.getClass().getDeclaredFields();

            for (Field field : fields) {
                Inject[] injects = field.getAnnotationsByType(Inject.class);
                if (injects.length > 0) {
                    Map<String, Object> arg5 = beans.get(field.getType());
                    field.setAccessible(true);
                    try {
                        field.set(arg4, arg5.get(injects[0].name()));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                }
            }
        });


    }

    public<T> void addBean(Class<T> type, T bean, String name) {
        if(beans.containsKey(type)) {
            beans.get(type).put(name, bean);
        } else {
            Map<String, Object> arg3 = new HashMap<>();
            arg3.put(name, bean);
            beans.put(type, arg3);
        }
    }

    public void init() {
        isReady = Boolean.FALSE;
        createBeans();

        Reflections reflections = new Reflections(packageName, new SubTypesScanner(false));
        Set<Class<? extends Object>> classes = reflections.getSubTypesOf(Object.class);
        Set<Class<?>> classForBeanCreating = classes.stream().filter(type -> {
            for(Annotation annotation: type.getAnnotations()) {
                if (annotation instanceof Bean) return true;
            }
            return false;
        }).collect(Collectors.toSet());

        classForBeanCreating.forEach(type -> {
            try {
                String beanName = type.getAnnotation(Bean.class).name();
                if(beanName.equals("")) {
                    String[] arg1 = type.getName().split("\\.");
                    beanName = arg1[arg1.length - 1];
                }

                if(beans.containsKey(type)) {
                    Map<String, Object> beansByType = beans.get(type);
                    beansByType.put(beanName, type.getConstructors()[0].newInstance());
                } else {
                    Map<String, Object> arg2 = new HashMap<>();
                    arg2.put(beanName, type.getConstructors()[0].newInstance());
                    beans.put(type, arg2);
                }


            } catch (Exception e) {
                throw new RuntimeException();
            }
        });

        injectBean();
        isReady = Boolean.TRUE;
    }


    @Override
    public void dispose() {

    }

    public boolean isReady() {
        return isReady;
    }

    public<T> T getBean(String name, Class<T> type) {
        Map<String, Object> map = beans.get(type);
        if (map == null) {
            throw new RuntimeException();
        }

        T bean = type.cast(map.get(name));
        return bean;
    }
}
