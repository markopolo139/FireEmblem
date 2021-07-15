package com.FireEmbelm.FireEmblem.business.value.equipment;

import com.FireEmbelm.FireEmblem.business.value.categories.ItemCategory;

import java.util.Objects;

public class HealingItemWithUses implements Equipment{

    private HealingItems mHealingItems;
    private int mUses;

    public HealingItems getHealingItems() {
        return mHealingItems;
    }

    public void setHealingItems(HealingItems healingItems) {
        mHealingItems = healingItems;
    }

    @Override
    public String getName() {
        return mHealingItems.getName();
    }

    @Override
    public int getMight() {
        return mHealingItems.getMight();
    }

    @Override
    public int getWorth() {
        return mHealingItems.getWorth();
    }

    @Override
    public int getRange() {
        return mHealingItems.getRange();
    }

    @Override
    public int getUses() {
        return mUses;
    }

    @Override
    public ItemCategory getItemCategory() {
        return mHealingItems.getItemCategory();
    }

    @Override
    public String getDescription() {
        return mHealingItems.getDescription();
    }

    @Override
    public void setUses(int uses) {
        mUses = uses;
    }

    public HealingItemWithUses(HealingItems healingItems, int uses) {
        mHealingItems = healingItems;
        mUses = uses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HealingItemWithUses that = (HealingItemWithUses) o;
        return getUses() == that.getUses() && getHealingItems() == that.getHealingItems();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getHealingItems(), getUses());
    }
}
