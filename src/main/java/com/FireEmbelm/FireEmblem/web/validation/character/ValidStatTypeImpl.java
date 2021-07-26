package com.FireEmbelm.FireEmblem.web.validation.character;

import com.FireEmbelm.FireEmblem.business.value.character.related.StatsType;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidStatTypeImpl implements ConstraintValidator<ValidStatType, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try{
            StatsType.valueOf(value);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}
