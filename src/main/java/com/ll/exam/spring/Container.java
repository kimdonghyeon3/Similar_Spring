package com.ll.exam.spring;

import com.ll.exam.App;
import com.ll.exam.db.MyDb;
import com.ll.exam.util.Util;
import com.ll.exam.annotation.Autowired;
import com.ll.exam.annotation.Controller;
import com.ll.exam.annotation.Repository;
import com.ll.exam.annotation.Service;
import org.reflections.Reflections;

import java.util.*;

public class Container {

    private static Map<Class, Object> objects;

    static {
        objects = new HashMap<>();
        scanComponents();
    }

    private static void scanComponents() {
        scanRepositories();
        scanServices();
        scanControllers();
        scanCustom();

        resolveDependenciesAllComponents();
    }

    private static void scanCustom() {
        objects.put(MyDb.class, new MyDb(App.DB_HOST, App.DB_PORT, App.DB_ID, App.DB_PASSWORD, App.DB_NAME));
    }

    private static void resolveDependenciesAllComponents() {
        for (Class cls : objects.keySet()) {
            Object o = objects.get(cls);

            resolveDependencies(o);
        }
    }

    private static void resolveDependencies(Object o) {
        Arrays.asList(o.getClass().getDeclaredFields())
                .stream()
                .filter(f -> f.isAnnotationPresent(Autowired.class))
                .map(field -> {
                    field.setAccessible(true);
                    return field;
                })
                .forEach(field -> {
                    Class cls = field.getType();
                    Object dependency = objects.get(cls);

                    try {
                        field.set(o, dependency);
                    } catch (IllegalAccessException e) {

                    }
                });
    }

    private static void scanRepositories() {
        Reflections ref = new Reflections(App.BASE_PACKAGE_PATH);
        for (Class<?> cls : ref.getTypesAnnotatedWith(Repository.class)) {
            objects.put(cls, Util.cls.newObj(cls, null));
        }
    }

    private static void scanServices() {
        Reflections ref = new Reflections("com.ll.exam");
        for (Class<?> cls : ref.getTypesAnnotatedWith(Service.class)) {
            objects.put(cls, Util.cls.newObj(cls, null));
        }
    }

    private static void scanControllers() {
        Reflections ref = new Reflections("com.ll.exam");
        for (Class<?> cls : ref.getTypesAnnotatedWith(Controller.class)) {
            objects.put(cls, Util.cls.newObj(cls, null));
        }
    }

    public static <T> T getObj(Class<T> cls) {
        return (T) objects.get(cls);
    }

    public static List<String> getControllerNames() {
        List<String> names = new ArrayList<>();

        Reflections ref = new Reflections("com.ll.exam");
        for (Class<?> cls : ref.getTypesAnnotatedWith(Controller.class)) {
            String name = cls.getSimpleName(); // com.ll.exam.HomeController
            name = name.replace("Controller", ""); // Home
            name = Util.str.decapitalize(name); // home

            names.add(name.replace("Controller", name));
        }

        return names;
    }
}
