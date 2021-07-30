package com.FireEmbelm.FireEmblem.app.interactors.service.security;

import com.FireEmbelm.FireEmblem.app.data.entities.UserEntity;
import com.FireEmbelm.FireEmblem.app.data.repository.UserRepository;
import com.FireEmbelm.FireEmblem.app.interactors.service.security.userdetail.UserDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserLoginService implements UserDetailsService {

    @Autowired
    private UserRepository mUserRepository;

    @Autowired
    private UserDetailMapper mUserDetailMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userEntity = mUserRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("This login already exist"));

        return mUserDetailMapper.toUserDetail(userEntity);
    }
}
