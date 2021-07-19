package com.FireEmbelm.FireEmblem.web.models.request;

import com.FireEmbelm.FireEmblem.business.value.equipment.Seals;
import com.FireEmbelm.FireEmblem.business.value.equipment.StatsUpItems;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Objects;

public class ItemsConvoyModel {

    @NotEmpty
    public int money;

    @NotEmpty
    public List<WeaponModel> weapons;

    @NotEmpty
    public List<HealingItemModel> healingItems;

    @NotEmpty
    public List<Seals> seals;

    @NotEmpty
    public List<StatsUpItems> statsUpItems;

    public ItemsConvoyModel(
            int money, List<WeaponModel> weapons, List<HealingItemModel> healingItems,
            List<Seals> seals, List<StatsUpItems> statsUpItems
    ) {
        this.money = money;
        this.weapons = weapons;
        this.healingItems = healingItems;
        this.seals = seals;
        this.statsUpItems = statsUpItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemsConvoyModel that = (ItemsConvoyModel) o;
        return money == that.money
                && Objects.equals(weapons, that.weapons)
                && Objects.equals(healingItems, that.healingItems)
                && Objects.equals(seals, that.seals)
                && Objects.equals(statsUpItems, that.statsUpItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(money, weapons, healingItems, seals, statsUpItems);
    }
}