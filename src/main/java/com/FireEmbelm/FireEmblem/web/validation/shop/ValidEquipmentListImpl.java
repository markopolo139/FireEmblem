package com.FireEmbelm.FireEmblem.web.validation.shop;

import com.FireEmbelm.FireEmblem.business.value.equipment.Equipment;
import com.FireEmbelm.FireEmblem.business.value.equipment.Seals;
import com.FireEmbelm.FireEmblem.business.value.equipment.StatsUpItems;
import com.FireEmbelm.FireEmblem.web.models.request.HealingItemModel;
import com.FireEmbelm.FireEmblem.web.models.request.WeaponModel;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ValidEquipmentListImpl implements ConstraintValidator<ValidEquipmentList, List<Equipment>> {
    @Override
    public boolean isValid(List<Equipment> value, ConstraintValidatorContext context) {
        for(Equipment e : value) {
            if(!(e instanceof WeaponModel))
                if(!(e instanceof HealingItemModel))
                    if(!(e instanceof Seals))
                        if(!(e instanceof StatsUpItems))
                            return false;
        }
        return true;
    }
}
