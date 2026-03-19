package com.mrlii.authwithpermissions.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Util {

    public static String byCryptPassWord(String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }
}
