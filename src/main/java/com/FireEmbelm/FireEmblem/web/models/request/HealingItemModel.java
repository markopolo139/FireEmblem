package com.FireEmbelm.FireEmblem.web.models.request;

import com.FireEmbelm.FireEmblem.business.value.categories.ItemCategory;
import com.FireEmbelm.FireEmblem.business.value.equipment.Equipment;
import com.FireEmbelm.FireEmblem.business.value.equipment.HealingItems;
import com.FireEmbelm.FireEmblem.web.validation.ValidHealingItemType;

import javax.validation.constraints.Min;
import java.util.Objects;

public class HealingItemModel implements Equipment{

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

    @Override
    public String getName() {
        return HealingItems.valueOf(healingType).getName();
    }

    @Override
    public int getMight() {
        return 0;
    }

    @Override
    public int getWorth() {
        return HealingItems.valueOf(healingType).getWorth();
    }

    @Override
    public int getRange() {
        return HealingItems.valueOf(healingType).getRange();
    }

    @Override
    public int getUses() {
        return uses;
    }

    @Override
    public void setUses(int uses) {
        this.uses = uses;
    }

    @Override
    public ItemCategory getItemCategory() {
        return HealingItems.valueOf(healingType).getItemCategory();
    }

    @Override
    public String getDescription() {
        return HealingItems.valueOf(healingType).getDescription();
    }
}
