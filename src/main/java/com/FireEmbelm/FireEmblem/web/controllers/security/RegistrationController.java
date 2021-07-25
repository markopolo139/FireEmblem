package com.FireEmbelm.FireEmblem.web.controllers.security;

import com.FireEmbelm.FireEmblem.app.data.entities.GameEntity;
import com.FireEmbelm.FireEmblem.app.data.entities.UserEntity;
import com.FireEmbelm.FireEmblem.app.data.repository.GameRepository;
import com.FireEmbelm.FireEmblem.app.data.repository.UserRepository;
import com.FireEmbelm.FireEmblem.app.interactors.service.security.userdetail.UserDetail;
import com.FireEmbelm.FireEmblem.web.models.request.LoginModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Set;

@CrossOrigin
@RestController
public class RegistrationController {

    @Autowired
    private UserRepository mUserRepository;

    @Autowired
    private GameRepository mGameRepository;

    @Autowired
    private PasswordEncoder mPasswordEncoder;

    @PostMapping("/api/v1/registration")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void register(@Valid @RequestBody LoginModel loginModel) {

        UserEntity userEntity = new UserEntity(
                null, loginModel.login, mPasswordEncoder.encode(loginModel.password),
                true, Set.of("USER"), null
        );

        GameEntity gameEntity = new GameEntity(
                null, userEntity, new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>(), null
        );

        mUserRepository.save(userEntity);
        mGameRepository.save(gameEntity);
    }

}
