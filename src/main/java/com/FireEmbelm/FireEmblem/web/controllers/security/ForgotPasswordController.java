package com.FireEmbelm.FireEmblem.web.controllers.security;

import com.FireEmbelm.FireEmblem.app.interactors.PasswordRecoveryInteractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@CrossOrigin
@Controller
public class ForgotPasswordController {

    @Autowired
    private PasswordRecoveryInteractor mPasswordRecoveryInteractor;

    @GetMapping("/forgotPassword")
    public String getForgotPassword() {
        return "forgotPassword";
    }

    @PostMapping("/forgotPassword")
    public void postForgotPassword() {
    }

    @GetMapping("/passwordRecovery")
    public String getPasswordRecovery() {
        return "newPassword";
    }

    @PostMapping("/passwordRecovery")
    public void postPasswordRecovery() {
    }

}
