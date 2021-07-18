package com.FireEmbelm.FireEmblem.web.models.request;

import com.FireEmbelm.FireEmblem.business.value.equipment.Equipment;
import com.FireEmbelm.FireEmblem.web.validation.ValidHealingItemType;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

public class HealingItemModel {

    @ValidHealingItemType
    public String healingType;

    @Min(0)
    public int uses;

    public HealingItemModel(String healingType, int uses) {
        this.healingType = healingType;
        this.uses = uses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HealingItemModel that = (HealingItemModel) o;
        return uses == that.uses && Objects.equals(healingType, that.healingType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(healingType, uses);
    }
}
