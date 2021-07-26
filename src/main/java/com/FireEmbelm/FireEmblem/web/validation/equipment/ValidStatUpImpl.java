package com.FireEmbelm.FireEmblem.web.validation.equipment;

import com.FireEmbelm.FireEmblem.business.value.equipment.Seals;
import com.FireEmbelm.FireEmblem.business.value.equipment.StatsUpItems;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidStatUpImpl implements ConstraintValidator<ValidStatUp, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            StatsUpItems.valueOf(value);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}
