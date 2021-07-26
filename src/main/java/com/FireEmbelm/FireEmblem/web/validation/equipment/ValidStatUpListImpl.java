package com.FireEmbelm.FireEmblem.web.validation.equipment;

import com.FireEmbelm.FireEmblem.business.value.equipment.StatsUpItems;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ValidStatUpListImpl implements ConstraintValidator<ValidStatUpList, List<String>> {
    @Override
    public boolean isValid(List<String> value, ConstraintValidatorContext context) {
        try {
            value.forEach(StatsUpItems::valueOf);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}
