package com.thaidq.ecomm;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.logging.Logger;



public class TestPassword {
    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("123456"));

    }
}