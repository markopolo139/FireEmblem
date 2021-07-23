package com.FireEmbelm.FireEmblem.web.models.request;

import com.FireEmbelm.FireEmblem.business.value.equipment.Seals;
import com.FireEmbelm.FireEmblem.business.value.equipment.StatsUpItems;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

public class ItemsConvoyModel {

    @NotEmpty
    public int money;

    @NotNull
    public List<WeaponModel> weapons;

    @NotNull
    public List<HealingItemModel> healingItems;

    @NotNull
    public List<Seals> seals;

    @NotNull
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
                && weapons.containsAll(that.weapons) && weapons.size() == that.weapons.size()
                && healingItems.containsAll(that.healingItems) && healingItems.size() == that.healingItems.size()
                && seals.containsAll(that.seals) && seals.size() == that.seals.size()
                && statsUpItems.containsAll(that.statsUpItems) && statsUpItems.size() == that.statsUpItems.size();
    }

    @Override
    public int hashCode() {
        return Objects.hash(money, weapons, healingItems, seals, statsUpItems);
    }
}
