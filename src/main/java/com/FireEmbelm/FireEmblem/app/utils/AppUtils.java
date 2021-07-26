package com.FireEmbelm.FireEmblem.app.utils;

import com.FireEmbelm.FireEmblem.app.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppUtils {

    @Autowired
    private UserRepository mUserRepository;

    public Long getGameIdFromLogin(String login) {
        return mUserRepository.findByLogin(login).gameEntity.gameId;
    }

}
