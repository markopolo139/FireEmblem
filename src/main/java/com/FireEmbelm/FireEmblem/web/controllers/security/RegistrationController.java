package com.FireEmbelm.FireEmblem.web.controllers.security;

import com.FireEmbelm.FireEmblem.app.data.entities.GameEntity;
import com.FireEmbelm.FireEmblem.app.data.entities.UserEntity;
import com.FireEmbelm.FireEmblem.app.data.repository.GameRepository;
import com.FireEmbelm.FireEmblem.app.data.repository.UserRepository;
import com.FireEmbelm.FireEmblem.app.interactors.service.security.userdetail.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/registration")
    public void register(@RequestBody @NotBlank String username, @RequestBody @NotNull String password) {


        UserEntity userEntity = new UserEntity(
                username, mPasswordEncoder.encode(password), true, Set.of("USER"), null
        );

        GameEntity gameEntity = new GameEntity(
                null, userEntity, new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>(), null
        );

        userEntity.gameEntity = gameEntity;

        mUserRepository.save(userEntity);
        mGameRepository.save(gameEntity);
    }

}
