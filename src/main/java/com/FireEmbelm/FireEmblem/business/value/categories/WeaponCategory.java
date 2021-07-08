package com.FireEmbelm.FireEmblem.business.value.categories;

public enum WeaponCategory implements ItemCategory{
    SWORD,
    AXE,
    LANCE,
    TOME,
    BOW,
    STAFF;

    @Override
    public String getName() {
        return this.name();
    }
}
