package com.FireEmbelm.FireEmblem.app.data.entities;

import com.FireEmbelm.FireEmblem.app.data.entities.embeddable.HealingItemEmbeddable;
import com.FireEmbelm.FireEmblem.app.data.entities.embeddable.WeaponEmbeddable;
import com.FireEmbelm.FireEmblem.business.value.equipment.Seals;
import com.FireEmbelm.FireEmblem.business.value.equipment.StatsUpItems;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "player_convoy")
public class ItemsConvoyEntity {

    @Id
    @GeneratedValue
    public Long convoyId;

    @ElementCollection
    @CollectionTable(name = "player_healing_items", joinColumns = {
            @JoinColumn(name = "convoy_id  ")
    })
    @Nullable
    public List<HealingItemEmbeddable> healingItems;

    @ElementCollection
    @CollectionTable(name = "player_weapons", joinColumns = {
            @JoinColumn(name = "convoy_id  ")
    })
    @Nullable
    public List<WeaponEmbeddable> weapons;

    @ElementCollection
    @CollectionTable(name = "player_seals", joinColumns = {
            @JoinColumn(name = "convoy_id  ")
    })
    @Nullable
    @Enumerated(EnumType.STRING)
    public List<Seals> seals;

    @ElementCollection
    @CollectionTable(name = "player_stat_up_items", joinColumns = {
            @JoinColumn(name = "convoy_id  ")
    })
    @Nullable
    @Enumerated(EnumType.STRING)
    public List<StatsUpItems> statsUpItems;

    public ItemsConvoyEntity(
            Long convoyId, @Nullable List<HealingItemEmbeddable> healingItems, @Nullable List<WeaponEmbeddable> weapons,
            @Nullable List<Seals> seals, @Nullable List<StatsUpItems> statsUpItems
    ) {
        this.convoyId = convoyId;
        this.healingItems = healingItems;
        this.weapons = weapons;
        this.seals = seals;
        this.statsUpItems = statsUpItems;
    }

    private ItemsConvoyEntity() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemsConvoyEntity that = (ItemsConvoyEntity) o;
        return Objects.equals(convoyId, that.convoyId)
                && Objects.equals(healingItems, that.healingItems)
                && Objects.equals(weapons, that.weapons)
                && Objects.equals(seals, that.seals)
                && Objects.equals(statsUpItems, that.statsUpItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(convoyId, healingItems, weapons, seals, statsUpItems);
    }
}
