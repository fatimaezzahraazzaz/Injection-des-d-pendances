package net.mini_projet.framework;

import net.mini_projet.annotations.Component;
import net.mini_projet.annotations.Inject;


import java.lang.reflect.*;
import java.util.*;

public class AnnotationIoCContainer {
    private final Map<String, Object> beans = new HashMap<>();

    public void loadComponents(List<Class<?>> classes) throws Exception {
        // 1. Instancier tous les composants marqués @Component
        for (Class<?> clazz : classes) {
            if (clazz.isAnnotationPresent(Component.class)) {
                Component component = clazz.getAnnotation(Component.class);
                String id = component.id();
                Object instance = clazz.getDeclaredConstructor().newInstance();
                beans.put(id, instance);
            }
        }

        // 2. Injection des dépendances
        for (Object bean : beans.values()) {
            injectDependencies(bean);
        }
    }

    private void injectDependencies(Object bean) throws Exception {
        Class<?> clazz = bean.getClass();

        // a. Injection par champ (field)
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(Inject.class)) {
                String dependencyId = field.getType().getSimpleName();
                dependencyId = Character.toLowerCase(dependencyId.charAt(0)) + dependencyId.substring(1); // camelCase

                Object dependency = findBeanByType(field.getType());

                field.setAccessible(true);
                field.set(bean, dependency);
            }
        }

        // b. Injection par méthode (setter)
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Inject.class)) {
                Class<?> paramType = method.getParameterTypes()[0];
                Object dependency = findBeanByType(paramType);

                method.setAccessible(true);
                method.invoke(bean, dependency);
            }
        }

        // c. Injection par constructeur
        for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {
            if (constructor.isAnnotationPresent(Inject.class)) {
                Class<?>[] paramTypes = constructor.getParameterTypes();
                Object[] params = new Object[paramTypes.length];
                for (int i = 0; i < paramTypes.length; i++) {
                    params[i] = findBeanByType(paramTypes[i]);
                }
                Object newInstance = constructor.newInstance(params);
                String id = getComponentId(clazz);
                beans.put(id, newInstance);
            }
        }
    }

    private Object findBeanByType(Class<?> type) {
        for (Object bean : beans.values()) {
            if (type.isAssignableFrom(bean.getClass())) {
                return bean;
            }
        }
        throw new RuntimeException("Aucune dépendance trouvée pour " + type.getName());
    }

    private String getComponentId(Class<?> clazz) {
        Component component = clazz.getAnnotation(Component.class);
        return component.id();
    }

    public Object getBean(String id) {
        return beans.get(id);
    }
}
