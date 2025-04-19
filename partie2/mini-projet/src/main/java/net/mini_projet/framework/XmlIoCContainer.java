package net.mini_projet.framework;

import net.mini_projet.xml.Bean;
import net.mini_projet.xml.Beans;
import net.mini_projet.xml.Property;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class XmlIoCContainer {

    private final Map<String, Object> beans = new HashMap<>();

    public void loadConfig(String resourcePath) throws Exception {
        // Charger le fichier XML depuis le classpath (src/main/resources)
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(resourcePath);

        if (inputStream == null) {
            throw new RuntimeException("Fichier XML non trouvé : " + resourcePath);
        }

        JAXBContext context = JAXBContext.newInstance(Beans.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Beans config = (Beans) unmarshaller.unmarshal(inputStream);

        // 1. Créer les instances (sans injection pour le moment)
        for (Bean bean : config.beans) {
            Class<?> clazz = Class.forName(bean.className);
            Object instance = clazz.getDeclaredConstructor().newInstance();
            beans.put(bean.id, instance);
        }

        // 2. Injecter les dépendances
        for (Bean bean : config.beans) {
            Object instance = beans.get(bean.id);
            Class<?> clazz = instance.getClass();

            if (bean.properties != null) {
                for (Property property : bean.properties) {
                    Object dependency = beans.get(property.ref);

                    // a. Injection par champ (field)
                    try {
                        Field field = clazz.getDeclaredField(property.name);
                        field.setAccessible(true);
                        field.set(instance, dependency);
                        continue;
                    } catch (NoSuchFieldException ignored) {}

                    // b. Injection par setter
                    String setterName = "set" + capitalize(property.name);
                    for (Method method : clazz.getMethods()) {
                        if (method.getName().equalsIgnoreCase(setterName) && method.getParameterCount() == 1) {
                            method.invoke(instance, dependency);
                            break;
                        }
                    }
                }
            }

            // c. Injection par constructeur (si constructeurs avec dépendances)
            Constructor<?>[] constructors = clazz.getDeclaredConstructors();
            for (Constructor<?> constructor : constructors) {
                Class<?>[] paramTypes = constructor.getParameterTypes();
                if (paramTypes.length > 0) {
                    Object[] args = new Object[paramTypes.length];
                    boolean match = true;

                    for (int i = 0; i < paramTypes.length; i++) {
                        boolean found = false;
                        for (Property property : bean.properties) {
                            Object dep = beans.get(property.ref);
                            if (paramTypes[i].isAssignableFrom(dep.getClass())) {
                                args[i] = dep;
                                found = true;
                                break;
                            }
                        }
                        if (!found) {
                            match = false;
                            break;
                        }
                    }

                    if (match) {
                        constructor.setAccessible(true);
                        instance = constructor.newInstance(args);
                        beans.put(bean.id, instance); // Remplacer l'instance créée précédemment
                        break;
                    }
                }
            }
        }
    }

    public Object getBean(String id) {
        return beans.get(id);
    }

    private String capitalize(String str) {
        if (str == null || str.isEmpty()) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
