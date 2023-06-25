package com.example.hommieenglish.utils;

import java.util.Base64;

public class Password {
    public static String encodePassword(String password) {
        byte[] encodedBytes = new byte[0];
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            encodedBytes = Base64.getEncoder().encode(password.getBytes());
        }
        return new String(encodedBytes);
    }

    public static String decodePassword(String encodedPassword) {
        byte[] decodedBytes = new byte[0];
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            decodedBytes = Base64.getDecoder().decode(encodedPassword);
        }
        return new String(decodedBytes);
    }
}
