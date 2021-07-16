package com.FireEmbelm.FireEmblem.app.data.entities.embeddable;

import com.FireEmbelm.FireEmblem.business.value.equipment.Seals;
import com.FireEmbelm.FireEmblem.business.value.equipment.StatsUpItems;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.List;

@Embeddable
public class EquipmentEmbeddable {

    @ElementCollection
    @Nullable
    public List<HealingItemEmbeddable> healingItems;

    @ElementCollection
    @Nullable
    public List<WeaponEmbeddable> weapons;

    @ElementCollection
    @Nullable
    @Enumerated(EnumType.STRING)
    public List<Seals> seals;

    @ElementCollection
    @Nullable
    @Enumerated(EnumType.STRING)
    public List<StatsUpItems> statsUpItems;

    public EquipmentEmbeddable() {
    }

    public EquipmentEmbeddable(
            List<HealingItemEmbeddable> healingItems, List<WeaponEmbeddable> weapons,
            List<Seals> seals, List<StatsUpItems> statsUpItems
    ) {
        this.healingItems = healingItems;
        this.weapons = weapons;
        this.seals = seals;
        this.statsUpItems = statsUpItems;
    }
}
