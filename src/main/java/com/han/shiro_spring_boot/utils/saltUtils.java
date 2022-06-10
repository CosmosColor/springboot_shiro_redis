package com.han.shiro_spring_boot.utils;

import java.util.Random;

public class saltUtils {
    public static String getSalt(int n){
        char[] chars = "ABCDEFJHIJKLMNOPQRSTUVWXYZabcdefjhijklmnopqrstuvwxyz0123456789!@#$%^&*()".toCharArray();
        StringBuilder stringBuilder=new StringBuilder();
        for (int i = 0; i < n; i++) {
            char aChar=chars[new Random().nextInt(chars.length)];
            stringBuilder.append(aChar);
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        System.out.println(getSalt(4));
    }
}
