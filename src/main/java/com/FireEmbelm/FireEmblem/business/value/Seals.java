package com.FireEmbelm.FireEmblem.business.value;

public enum Seals implements Equipment {
    MASTER_SEAL(
            "Master Seal", 2500,
            "Master Seal - enable to promote to higher class from lv 15"
    ),
    HEART_SEAL(
            "Heart Seal", 2000,
            "Heart Seal - enable to change to another class, chosen randomly"
            );

    private final String mItemName;
    private final int mWorth;
    private final String mDescription;

    Seals(String itemName, int worth, String description) {
        mItemName = itemName;
        mWorth = worth;
        mDescription = description;
    }

    @Override
    public String getName() {
        return mItemName;
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
        return 0;
    }

    @Override
    public int getUses() {
        return 1;
    }

    @Override
    public ItemCategory getItemCategory() {
        return ItemCategory.SEALS;
    }

    @Override
    public String getDescription() {
        return mDescription;
    }
}
