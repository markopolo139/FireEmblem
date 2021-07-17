package com.FireEmbelm.FireEmblem.business.entitie;

import com.FireEmbelm.FireEmblem.business.value.categories.ItemCategory;
import com.FireEmbelm.FireEmblem.business.value.equipment.*;

import java.util.ArrayList;
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
        return (ArrayList<Equipment>) mEquipmentCollection.stream()
                .filter(i -> i instanceof Weapon)
                .collect(Collectors.toList());
    }

    public ArrayList<Equipment> getSeals() {
        return (ArrayList<Equipment>) mEquipmentCollection.stream()
                .filter(i -> i instanceof Seals)
                .collect(Collectors.toList());
    }

    public ArrayList<Equipment> getStatsUpItems() {
        return (ArrayList<Equipment>) mEquipmentCollection.stream()
                .filter(i -> i instanceof StatsUpItems)
                .collect(Collectors.toList());
    }

    public ArrayList<Equipment> getHealingItems() {
        return (ArrayList<Equipment>) mEquipmentCollection.stream()
                .filter(i -> i instanceof HealingItemWithUses)
                .collect(Collectors.toList());
    }

    public ArrayList<Equipment> getByItemCategory(ItemCategory itemCategory) {
        return (ArrayList<Equipment>) mEquipmentCollection.stream()
                .filter(i -> i.getItemCategory().equals(itemCategory))
                .collect(Collectors.toList());
    }

    public ItemsConvoy(int money, ArrayList<Equipment> equipmentCollection) {
        mMoney = money;
        mEquipmentCollection = equipmentCollection;
    }
}
