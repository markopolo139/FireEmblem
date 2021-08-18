package com.FireEmbelm.FireEmblem.app.interactors;

import com.FireEmbelm.FireEmblem.app.data.entities.UserEntity;
import com.FireEmbelm.FireEmblem.app.data.repository.UserRepository;
import com.FireEmbelm.FireEmblem.app.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PasswordRecoveryInteractor {

    @Autowired
    private UserRepository mUserRepository;

    public UserEntity getUserByEmail(String email) throws UserNotFoundException {
        return mUserRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
    }

    public UserEntity getUserByPasswordToken(String token) throws UserNotFoundException {
        return mUserRepository.findByPasswordToken(token).orElseThrow(UserNotFoundException::new);
    }

    public void updatePassword(String token, String newPassword) throws UserNotFoundException {
        UserEntity userEntity = getUserByPasswordToken(token);
        userEntity.password = newPassword;
        userEntity.passwordToken = null;
        mUserRepository.save(userEntity);
    }

    public void updateToken(String email, String resetToken) throws UserNotFoundException {
        UserEntity userEntity = getUserByEmail(email);
        userEntity.passwordToken = resetToken;
        mUserRepository.save(userEntity);
    }

}
