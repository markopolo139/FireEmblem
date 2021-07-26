package com.FireEmbelm.FireEmblem.web.validation.equipment;

import com.FireEmbelm.FireEmblem.business.value.equipment.Seals;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidSealImpl implements ConstraintValidator<ValidSeal, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            Seals.valueOf(value);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}
