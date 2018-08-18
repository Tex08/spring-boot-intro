package com.texus.springboot.web.springbootfirstwebapplication.controller;

import com.texus.springboot.web.springbootfirstwebapplication.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class LoginController {

    @Autowired
    LoginService service;

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String showLoginPage(ModelMap model) {
        model.put("name", "Texus");
        return "welcome";
    }
}
