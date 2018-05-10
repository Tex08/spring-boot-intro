package com.texus.springboot.web.springbootfirstwebapplication.controller;

import com.texus.springboot.web.springbootfirstwebapplication.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    LoginService service;

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String loginMessage(ModelMap model) {
        //model.put("name", name);
        return "login";
    }

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public String showWelcomePage(@RequestParam String name, @RequestParam String password, ModelMap model) {
        boolean isValidUser = service.validateUser(name, password);
        if(!isValidUser) {
            model.put("Emessage", "Invalid Credentials!!");
            return "login";
        }
        model.put("name", name);
        model.put("password", password);
        return "welcome";
    }

}
