package com.FireEmbelm.FireEmblem.business.entitie;

import com.FireEmbelm.FireEmblem.business.value.categories.ItemCategory;
import com.FireEmbelm.FireEmblem.business.value.equipment.*;

import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

public class ItemsConvoy {

    private int mMoney;
    private final ArrayList<Equipment> mEquipmentCollection;

    public ArrayList<Equipment> getEquipmentCollection() {
        return mEquipmentCollection;
    }

    public int getMoney() {
        return mMoney;
    }

    public void setMoney(int money) {
        mMoney = money;
    }

    public ArrayList<Equipment> getWeapons() {

        if (mEquipmentCollection == null)
            return null;

        return (ArrayList<Equipment>) mEquipmentCollection.stream()
                .filter(i -> i instanceof Weapon)
                .collect(Collectors.toList());
    }

    public ArrayList<Equipment> getSeals() {

        if (mEquipmentCollection == null)
            return null;

        return (ArrayList<Equipment>) mEquipmentCollection.stream()
                .filter(i -> i instanceof Seals)
                .collect(Collectors.toList());
    }

    public ArrayList<Equipment> getStatsUpItems() {

        if (mEquipmentCollection == null)
            return null;

        return (ArrayList<Equipment>) mEquipmentCollection.stream()
                .filter(i -> i instanceof StatsUpItems)
                .collect(Collectors.toList());
    }

    public ArrayList<Equipment> getHealingItems() {

        if (mEquipmentCollection == null)
            return null;

        return (ArrayList<Equipment>) mEquipmentCollection.stream()
                .filter(i -> i instanceof HealingItemWithUses)
                .collect(Collectors.toList());
    }

    public ArrayList<Equipment> getByItemCategory(ItemCategory itemCategory) {

        if (mEquipmentCollection == null)
            return null;

        return (ArrayList<Equipment>) mEquipmentCollection.stream()
                .filter(i -> i.getItemCategory().equals(itemCategory))
                .collect(Collectors.toList());
    }

    public ItemsConvoy(int money, ArrayList<Equipment> equipmentCollection) {
        mMoney = money;
        mEquipmentCollection = equipmentCollection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemsConvoy that = (ItemsConvoy) o;
        return getMoney() == that.getMoney()
                && Objects.equals(getEquipmentCollection(), that.getEquipmentCollection());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMoney(), getEquipmentCollection());
    }
}
