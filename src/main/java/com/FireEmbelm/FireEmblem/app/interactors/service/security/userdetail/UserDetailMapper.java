package com.FireEmbelm.FireEmblem.app.interactors.service.security.userdetail;

import com.FireEmbelm.FireEmblem.app.data.entities.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserDetailMapper {

    public UserDetail toUserDetail(UserEntity userEntity) {
        return UserDetail.builder()
                .gameId(userEntity.gameEntity.gameId)
                .username(userEntity.login)
                .password(userEntity.password)
                .authorities(userEntity.userRoles.toArray(String[]::new))
                .disabled(!userEntity.enabled)
                .build();
    }

}
