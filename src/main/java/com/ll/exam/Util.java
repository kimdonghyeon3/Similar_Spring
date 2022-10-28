package com.ll.exam;

public class Util {
    public static class cls{

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
