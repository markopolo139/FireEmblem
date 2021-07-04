package com.FireEmbelm.FireEmblem.business.entitie;

import com.FireEmbelm.FireEmblem.business.value.*;

import java.util.Collection;
import java.util.stream.Collectors;

public class ItemsConvoy {

    private int mMoney;
    private final Collection<Equipment> mEquipmentCollection;

    public Collection<Equipment> getEquipmentCollection() {
        return mEquipmentCollection;
    }

    public int getMoney() {
        return mMoney;
    }

    public void setMoney(int money) {
        mMoney = money;
    }

    public Collection<Equipment> getWeapons() {
        return mEquipmentCollection.stream()
                .filter(i -> i instanceof Weapon)
                .collect(Collectors.toList());
    }

    public Collection<Equipment> getSeals() {
        return mEquipmentCollection.stream()
                .filter(i -> i instanceof Seals)
                .collect(Collectors.toList());
    }

    public Collection<Equipment> getStatsUpItems() {
        return mEquipmentCollection.stream()
                .filter(i -> i instanceof StatsUpItems)
                .collect(Collectors.toList());
    }

    public Collection<Equipment> getHealingItems() {
        return mEquipmentCollection.stream()
                .filter(i -> i instanceof HealingItems)
                .collect(Collectors.toList());
    }

    public Collection<Equipment> getByItemCategory(ItemCategory itemCategory) {
        return mEquipmentCollection.stream()
                .filter(i -> i.getItemCategory().equals(itemCategory))
                .collect(Collectors.toList());
    }

    public ItemsConvoy(int money, Collection<Equipment> equipmentCollection) {
        mMoney = money;
        mEquipmentCollection = equipmentCollection;
    }
}
