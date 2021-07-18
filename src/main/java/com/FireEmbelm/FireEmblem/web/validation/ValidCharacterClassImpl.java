package com.FireEmbelm.FireEmblem.web.validation;

import com.FireEmbelm.FireEmblem.business.entitie.Character;
import com.FireEmbelm.FireEmblem.business.entitie.CharacterClass;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidCharacterClassImpl implements ConstraintValidator<ValidCharacterClass, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try{
            CharacterClass.valueOf(value);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}
