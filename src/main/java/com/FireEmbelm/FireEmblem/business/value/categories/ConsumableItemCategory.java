package com.FireEmbelm.FireEmblem.business.value.categories;

public enum ConsumableItemCategory implements ItemCategory{
    RECOVERY_ITEM,
    SEALS,
    STATS_UP_ITEMS;

    @Override
    public String getName() {
        return this.name();
    }
}
