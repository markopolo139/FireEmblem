package com.FireEmbelm.FireEmblem.web.validation.equipment;

import com.FireEmbelm.FireEmblem.business.value.equipment.Seals;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ValidSealListImpl implements ConstraintValidator<ValidSealList, List<String>> {
    @Override
    public boolean isValid(List<String> value, ConstraintValidatorContext context) {
        try {
            value.forEach(Seals::valueOf);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}
