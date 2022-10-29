package com.ll.exam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.Field;


public class Util {

    public static class json {
        private static final ObjectMapper om;

        static {
            om = new ObjectMapper();
        }

        public static String toStr(Object obj, String defaultValue) {
            try {
                return om.writeValueAsString(obj);
            } catch (JsonProcessingException e) {
                return defaultValue;
            }
        }

        public static <T> T toObj(String jsonStr, Class<T> cls, T defaultValue) {
            try {
                return om.readValue(jsonStr, cls);
            } catch (JsonProcessingException e) {
                return defaultValue;
            }
        }

        public static <T> T toObj(String jsonStr, TypeReference<T> typeReference, T defaultValue) {
            try {
                return om.readValue(jsonStr, typeReference);
            } catch (JsonProcessingException e) {
                return defaultValue;
            }
        }
    }
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

        public static String beforeFrom(String str, String fromStr, int matchCount) {
            StringBuilder sb = new StringBuilder();

            String[] bits = str.split(fromStr);

            for (int i = 0; i < matchCount; i++) {
                sb.append(bits[i]);
                if ( i + 1 < matchCount ) {
                    sb.append(fromStr);
                }
            }

            return sb.toString();
        }
    }

    public static class reflection {
        public static <T> T getFieldValue(Object o, String fieldName, T defaultValue) {
            Field field = null;

            try {
                field = o.getClass().getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                return defaultValue;
            }

            field.setAccessible(true);

            try {
                return (T)field.get(o);
            } catch (IllegalAccessException e) {
                return defaultValue;
            }
        }
    }
}
