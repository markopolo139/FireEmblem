package com.FireEmbelm.FireEmblem.app.utils;

import com.FireEmbelm.FireEmblem.app.data.repository.UserRepository;
import org.passay.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class AppUtils {

    @Autowired
    private UserRepository mUserRepository;

    public Long getGameIdFromLogin(String login) {
        return mUserRepository.findByLogin(login).orElseThrow().gameEntity.gameId;
    }

    public static boolean validatePassword(String password) {
        PasswordValidator passwordValidator = new PasswordValidator(Arrays.asList(
                new LengthRule(8,30),
                new SpecialCharacterRule(1),
                new UppercaseCharacterRule(1),
                new DigitCharacterRule(1),
                new WhitespaceRule()
        ));

        RuleResult ruleResult = passwordValidator.validate(new PasswordData(password));
        return ruleResult.isValid();
    }

}
