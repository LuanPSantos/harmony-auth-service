package com.harmony.authservice.app.util;

public class ObjectUtils {

    public static <T> T notNullOrDefault(T value, T defaultValue) {
        if (value == null) {
            return defaultValue;
        }

        return value;
    }
}
