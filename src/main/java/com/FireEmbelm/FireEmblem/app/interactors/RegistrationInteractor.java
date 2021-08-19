package com.FireEmbelm.FireEmblem.app.interactors;

import com.FireEmbelm.FireEmblem.app.data.entities.GameEntity;
import com.FireEmbelm.FireEmblem.app.data.entities.UserEntity;
import com.FireEmbelm.FireEmblem.app.data.repository.GameRepository;
import com.FireEmbelm.FireEmblem.app.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegistrationInteractor {

    @Autowired
    private UserRepository mUserRepository;

    @Autowired
    private GameRepository mGameRepository;

    public Optional<UserEntity> findUserByLogin(String login) {
        return mUserRepository.findByLogin(login);
    }

    public Optional<UserEntity> findUserByEmail(String login) {
        return mUserRepository.findByEmail(login);
    }

    public void saveGame(GameEntity gameEntity) {
        mGameRepository.save(gameEntity);
    }

    public void saveUser(UserEntity userEntity) {
        mUserRepository.save(userEntity);
    }
}
