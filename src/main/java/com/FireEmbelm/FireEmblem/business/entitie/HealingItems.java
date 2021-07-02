package com.FireEmbelm.FireEmblem.business.entitie;

import com.FireEmbelm.FireEmblem.business.value.ItemCategory;

public enum HealingItems implements Equipment {
    VULNERARY("Vulnerary", 10, 3, ItemCategory.RECOVERY_ITEM),
    CONCOCTIONS("Concoctions", 20, 2, ItemCategory.RECOVERY_ITEM),
    ELIXIR("Elixir", 30, 1, ItemCategory.RECOVERY_ITEM),
    MEND("Mend", 25, 10, ItemCategory.STAFF),
    HEAL("Heal", 15, 20, ItemCategory.STAFF);

    private final String mItemName;
    private final int mHealValue;
    private int mItemUse;
    private final ItemCategory mItemCategory;

    HealingItems(String itemName, int healValue, int itemUse, ItemCategory itemCategory) {
        this.mItemName = itemName;
        this.mHealValue = healValue;
        this.mItemUse = itemUse;
        this.mItemCategory = itemCategory;
    }

    public String getItemName() {
        return mItemName;
    }

    public int getHealValue() {
        return mHealValue;
    }

    public int getItemUse() {
        return mItemUse;
    }

    public void setItemUse(int itemUse) {
        this.mItemUse = itemUse;
    }

    public ItemCategory getItemCategory() {
        return mItemCategory;
    }

    @Override
    public String getName() {
        return getItemName();
    }
}
