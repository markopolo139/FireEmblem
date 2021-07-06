package com.FireEmbelm.FireEmblem.business.value.categories;

public enum WeaponCategory implements ItemCategory{
    SWORD,
    AXE,
    LANCE,
    TOME,
    BOW;

    @Override
    public String getName() {
        return this.name();
    }
}
