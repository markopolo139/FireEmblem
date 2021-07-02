package com.FireEmbelm.FireEmblem.business.value;

public enum HealingItems implements Equipment {
    VULNERARY("Vulnerary", 10, 3, 300, ItemCategory.RECOVERY_ITEM),
    CONCOCTIONS("Concoctions", 20, 2, 600, ItemCategory.RECOVERY_ITEM),
    ELIXIR("Elixir", 30, 1, 900, ItemCategory.RECOVERY_ITEM),
    MEND("Mend", 25, 10, 1200, ItemCategory.STAFF),
    HEAL("Heal", 15, 20, 600, ItemCategory.STAFF);

    private final String mItemName;
    private final int mHealValue;
    private int mItemUses;
    private final int mWorth;
    private final ItemCategory mItemCategory;

    HealingItems(String itemName, int healValue, int itemUses, int worth, ItemCategory itemCategory) {
        this.mItemName = itemName;
        this.mHealValue = healValue;
        this.mItemUses = itemUses;
        mWorth = worth;
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
    public int getWorth() {
        return mWorth;
    }

    @Override
    public int getRange() {
        return 1;
    }

    @Override
    public int getUses() {
        return mItemUses;
    }
}
