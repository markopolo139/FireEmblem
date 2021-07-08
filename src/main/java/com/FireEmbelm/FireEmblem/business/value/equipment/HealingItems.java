package com.FireEmbelm.FireEmblem.business.value.equipment;

import com.FireEmbelm.FireEmblem.business.value.categories.ConsumableItemCategory;
import com.FireEmbelm.FireEmblem.business.value.categories.ItemCategory;
import com.FireEmbelm.FireEmblem.business.value.categories.WeaponCategory;

public enum HealingItems {
    VULNERARY("Vulnerary", 10, 300, 1, ConsumableItemCategory.RECOVERY_ITEM),
    CONCOCTIONS("Concoctions", 20 , 600, 1, ConsumableItemCategory.RECOVERY_ITEM),
    ELIXIR("Elixir", 30, 900, 1, ConsumableItemCategory.RECOVERY_ITEM),
    MEND("Mend", 25, 1500, 2, WeaponCategory.STAFF),
    HEAL("Heal", 15, 800, 2, WeaponCategory.STAFF);

    private final String mItemName;
    private final int mHealValue;
    private final int mWorth;
    private final int mRange;
    private final ItemCategory mItemCategory;

    HealingItems(String itemName, int healValue, int worth, int range, ItemCategory itemCategory) {
        this.mItemName = itemName;
        this.mHealValue = healValue;
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

    public ItemCategory getItemCategory() {
        return mItemCategory;
    }

    public String getName() {
        return getItemName();
    }

    public int getMight() {
        return 0;
    }

    public int getWorth() {
        return mWorth;
    }

    public int getRange() {
        return mRange;
    }

    public String getDescription() {
        return "ItemName = '" + mItemName +
                "\n HealValue = " + mHealValue +
                "\n Worth = " + mWorth +
                "\n Range = " + mRange;
    }
}
