package com.harmony.authservice.common.util;

public class ObjectUtil {

    public static <T> T notNullOrDefault(T value, T defaultValue) {
        if (value == null) {
            return defaultValue;
        }

        return value;
    }
}