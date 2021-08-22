package com.FireEmbelm.FireEmblem.web.validation;

import com.FireEmbelm.FireEmblem.business.value.DifficultySettings;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidDifficultySettingImpl implements ConstraintValidator<ValidDifficultySetting, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try{
            DifficultySettings.valueOf(value);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}
