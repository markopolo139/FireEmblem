package com.FireEmbelm.FireEmblem.business.value;

public enum Seals implements Equipment {
    MASTER_SEAL("Master Seal", 2500),
    HEART_SEAL("Heart Seal", 2000); // CHANGE CLASS

    private final String mItemName;
    private final int mWorth;

    Seals(String itemName, int worth) {
        mItemName = itemName;
        mWorth = worth;
    }


    @Override
    public String getName() {
        return mItemName;
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
}
