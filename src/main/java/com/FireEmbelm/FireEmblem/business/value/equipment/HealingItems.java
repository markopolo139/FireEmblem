package com.FireEmbelm.FireEmblem.business.value.equipment;

import com.FireEmbelm.FireEmblem.business.value.categories.ConsumableItemCategory;
import com.FireEmbelm.FireEmblem.business.value.categories.ItemCategory;

public enum HealingItems implements Equipment {
    VULNERARY("Vulnerary", 10, 3, 300, 1, ConsumableItemCategory.RECOVERY_ITEM),
    CONCOCTIONS("Concoctions", 20, 2, 600, 1, ConsumableItemCategory.RECOVERY_ITEM),
    ELIXIR("Elixir", 30, 1, 900, 1, ConsumableItemCategory.RECOVERY_ITEM),
    MEND("Mend", 25, 10, 1500, 2, ConsumableItemCategory.STAFF),
    HEAL("Heal", 15, 20, 800, 2, ConsumableItemCategory.STAFF);

    private final String mItemName;
    private final int mHealValue;
    private int mItemUses;
    private final int mWorth;
    private final int mRange;
    private final ItemCategory mItemCategory;

    HealingItems(String itemName, int healValue, int itemUses, int worth, int range, ItemCategory itemCategory) {
        this.mItemName = itemName;
        this.mHealValue = healValue;
        this.mItemUses = itemUses;
        mWorth = worth;
        mRange = range;
        this.mItemCategory = itemCategory;
    }

    public String getItemName() {
        return mItemName;
    }

    public int getHealValue() {
        return mHealValue;
    }

    public int getItemUses() {
        return mItemUses;
    }

    public void setItemUses(int itemUses) {
        this.mItemUses = itemUses;
    }

    public ItemCategory getItemCategory() {
        return mItemCategory;
    }

    @Override
    public String getName() {
        return getItemName();
    }

    @Override
    public int getMight() {
        return 0;
    }

    @Override
    public int getWorth() {
        return mWorth;
    }

    @Override
    public int getRange() {
        return mRange;
    }

    @Override
    public int getUses() {
        return mItemUses;
    }

    @Override
    public String getDescription() {
        return "ItemName = '" + mItemName +
                "\n HealValue = " + mHealValue +
                "\n ItemUses = " + mItemUses +
                "\n Worth = " + mWorth +
                "\n Range = " + mRange;
    }
}
