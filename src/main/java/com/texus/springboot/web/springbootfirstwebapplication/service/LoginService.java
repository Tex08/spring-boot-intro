package com.texus.springboot.web.springbootfirstwebapplication.service;

import org.springframework.stereotype.Component;

@Component
public class LoginService {

    public boolean validateUser(String userId, String password) {
        return userId.equalsIgnoreCase("Texus")
                && password.equalsIgnoreCase("pass");
    }
}
