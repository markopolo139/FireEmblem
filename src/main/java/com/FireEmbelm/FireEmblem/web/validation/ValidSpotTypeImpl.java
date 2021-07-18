package com.FireEmbelm.FireEmblem.web.validation;

import com.FireEmbelm.FireEmblem.business.value.field.SpotsType;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidSpotTypeImpl implements ConstraintValidator<ValidSpotType, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            SpotsType.valueOf(value);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}
