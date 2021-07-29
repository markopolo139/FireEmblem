package com.FireEmbelm.FireEmblem.business.entitie;

import com.FireEmbelm.FireEmblem.business.value.categories.ItemCategory;
import com.FireEmbelm.FireEmblem.business.value.equipment.*;

import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

public class ItemsConvoy {

    private int mMoney;
    private final ArrayList<Equipment> mPlayerItems;

    public ArrayList<Equipment> getPlayerItems() {
        return mPlayerItems;
    }

    public int getMoney() {
        return mMoney;
    }

    public void setMoney(int money) {
        mMoney = money;
    }

    public ArrayList<Equipment> getWeapons() {
        return (ArrayList<Equipment>) mPlayerItems.stream()
                .filter(i -> i instanceof Weapon)
                .collect(Collectors.toList());
    }

    public ArrayList<Equipment> getSeals() {
        return (ArrayList<Equipment>) mPlayerItems.stream()
                .filter(i -> i instanceof Seals)
                .collect(Collectors.toList());
    }

    public ArrayList<Equipment> getStatsUpItems() {
        return (ArrayList<Equipment>) mPlayerItems.stream()
                .filter(i -> i instanceof StatsUpItems)
                .collect(Collectors.toList());
    }

    public ArrayList<Equipment> getHealingItems() {
        return (ArrayList<Equipment>) mPlayerItems.stream()
                .filter(i -> i instanceof HealingItemWithUses)
                .collect(Collectors.toList());
    }

    public ArrayList<Equipment> getByItemCategory(ItemCategory itemCategory) {
        return (ArrayList<Equipment>) mPlayerItems.stream()
                .filter(i -> i.getItemCategory().equals(itemCategory))
                .collect(Collectors.toList());
    }

    public ItemsConvoy(int money, ArrayList<Equipment> playerItems) {
        mMoney = money;
        mPlayerItems = playerItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemsConvoy that = (ItemsConvoy) o;
        return getMoney() == that.getMoney()
                && Objects.equals(getPlayerItems(), that.getPlayerItems());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMoney(), getPlayerItems());
    }
}
