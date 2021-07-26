package com.FireEmbelm.FireEmblem.web.validation.shop;

import com.FireEmbelm.FireEmblem.business.value.equipment.Equipment;
import com.FireEmbelm.FireEmblem.business.value.equipment.Seals;
import com.FireEmbelm.FireEmblem.business.value.equipment.StatsUpItems;
import com.FireEmbelm.FireEmblem.web.models.request.HealingItemModel;
import com.FireEmbelm.FireEmblem.web.models.request.WeaponModel;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidEquipmentImpl implements ConstraintValidator<ValidEquipment, Equipment> {
    @Override
    public boolean isValid(Equipment value, ConstraintValidatorContext context) {
        if(!(value instanceof WeaponModel))
            if(!(value instanceof HealingItemModel))
                if(!(value instanceof Seals))
                    if(!(value instanceof StatsUpItems))
                        return false;
        return true;
    }
}
