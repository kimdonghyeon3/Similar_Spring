package com.ll.exam;

import com.ll.exam.annotation.Controller;
import com.ll.exam.article.controller.ArticleController;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Map;

public class Util {
    public static class cls{

        private static Map<Class, Object> objects;

        static {
            objects = new HashMap<>();

            Reflections ref = new Reflections("com.ll.exam");
            for (Class<?> cls : ref.getTypesAnnotatedWith(Controller.class)) {
                objects.put(cls, Util.cls.newObj(cls, null));
            }
        }

        public static <T> T getObj(Class<T> cls) {
            return (T) objects.get(cls);
        }
        public static <T> T newObj(Class<T> cls, T defaultValue) {
            try {
                return cls.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                return defaultValue;
            }
        }
    }

    public static class str{
        public static String decapitalize(String string) {

            if (string == null || string.length() == 0) {
                return string;
            }

            char c[] = string.toCharArray();
            c[0] = Character.toLowerCase(c[0]);

            return new String(c);

        }
    }
}
