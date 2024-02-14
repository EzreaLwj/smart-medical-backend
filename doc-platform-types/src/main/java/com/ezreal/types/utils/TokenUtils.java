package com.ezreal.types.utils;

/**
 * @author Ezreal
 * @Date 2024/2/15
 */
public class TokenUtils {
    private static final ThreadLocal<String> TOKEN_LOCAL = new ThreadLocal<>();

    public static void setToken(String token) {
        TOKEN_LOCAL.set(token);
    }

    public static String getToken() {
        return TOKEN_LOCAL.get();
    }

    public static void clear() {
        TOKEN_LOCAL.remove();
    }
}
