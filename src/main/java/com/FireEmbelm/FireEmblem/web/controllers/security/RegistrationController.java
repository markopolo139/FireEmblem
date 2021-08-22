package com.FireEmbelm.FireEmblem.web.controllers.security;

import com.FireEmbelm.FireEmblem.app.data.entities.GameEntity;
import com.FireEmbelm.FireEmblem.app.data.entities.UserEntity;
import com.FireEmbelm.FireEmblem.app.data.repository.GameRepository;
import com.FireEmbelm.FireEmblem.app.data.repository.UserRepository;
import com.FireEmbelm.FireEmblem.app.interactors.RegistrationInteractor;
import com.FireEmbelm.FireEmblem.app.utils.AppUtils;
import com.FireEmbelm.FireEmblem.business.value.DifficultySettings;
import com.FireEmbelm.FireEmblem.web.models.request.LoginModel;
import com.FireEmbelm.FireEmblem.web.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

@CrossOrigin
@Controller
public class RegistrationController {

    @Autowired
    private RegistrationInteractor mRegistrationInteractor;

    @Autowired
    private PasswordEncoder mPasswordEncoder;

    @GetMapping("/api/v1/registration")
    public String registerPage() {
        return "registration";
    }

    @PostMapping("/api/v1/registration")
    @ResponseStatus(code = HttpStatus.CREATED)
    public String register(HttpServletRequest request, Model model, HttpServletResponse response) throws IOException {

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if(!AppUtils.validatePassword(password))
            model.addAttribute("error", "Password is not correct " +
                    "(Password must have at least : 1 uppercase character, 1 digit, 1 special character (@,#)," +
                    " no whitespaces, and length from 8 to 30");
        else {
            if (mRegistrationInteractor.findUserByLogin(username).isPresent()
                    || mRegistrationInteractor.findUserByEmail(email).isPresent())
                model.addAttribute("error", "This login or email already exist");
            else {
                UserEntity userEntity = new UserEntity(
                        null, username, mPasswordEncoder.encode(password), email,
                        null, true, Set.of("USER"), null
                );

                GameEntity gameEntity = new GameEntity(
                        null, userEntity, DifficultySettings.NORMAL, new ArrayList<>(),
                        new ArrayList<>(), new ArrayList<>(), null
                );

                mRegistrationInteractor.saveUser(userEntity);
                mRegistrationInteractor.saveGame(gameEntity);
                response.sendRedirect(WebUtils.getServerPath(request) + "/login");
            }
        }


        return "registration";
    }

}
