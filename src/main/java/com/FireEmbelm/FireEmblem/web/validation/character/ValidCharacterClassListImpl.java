package com.FireEmbelm.FireEmblem.web.validation.character;

import com.FireEmbelm.FireEmblem.business.entitie.CharacterClass;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ValidCharacterClassListImpl implements ConstraintValidator<ValidCharacterClassList, List<String>> {
    @Override
    public boolean isValid(List<String> value, ConstraintValidatorContext context) {
        try {
            value.forEach(CharacterClass::valueOf);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}
