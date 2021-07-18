package com.FireEmbelm.FireEmblem.web.validation;

import com.FireEmbelm.FireEmblem.business.value.categories.WeaponCategory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidWeaponCategoryImpl implements ConstraintValidator<ValidWeaponCategory, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            WeaponCategory.valueOf(value);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}
