package com.ll.exam;

import com.ll.exam.article.controller.ArticleController;

import java.util.HashMap;
import java.util.Map;

public class Util {
    public static class cls{

        private static Map<Class, Object> objects;

        static {
            objects = new HashMap<>();

            objects.put(ArticleController.class, new ArticleController());
            objects.put(HomeController.class, new HomeController());
        }

        public static Object getObj(Class cls) {
            return objects.get(cls);
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
