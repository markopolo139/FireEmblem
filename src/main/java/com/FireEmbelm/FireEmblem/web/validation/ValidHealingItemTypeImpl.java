package com.FireEmbelm.FireEmblem.web.validation;

import com.FireEmbelm.FireEmblem.business.value.equipment.HealingItems;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidHealingItemTypeImpl implements ConstraintValidator<ValidHealingItemType, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try{
            HealingItems.valueOf(value);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}
