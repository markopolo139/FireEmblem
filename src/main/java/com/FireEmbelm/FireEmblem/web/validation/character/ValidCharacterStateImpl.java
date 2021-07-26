package com.FireEmbelm.FireEmblem.web.validation.character;

import com.FireEmbelm.FireEmblem.business.value.character.related.CharacterState;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidCharacterStateImpl implements ConstraintValidator<ValidCharacterState, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try{
            CharacterState.valueOf(value);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}
