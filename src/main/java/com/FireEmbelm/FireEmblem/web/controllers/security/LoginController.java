package com.FireEmbelm.FireEmblem.web.controllers.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin
@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

}
