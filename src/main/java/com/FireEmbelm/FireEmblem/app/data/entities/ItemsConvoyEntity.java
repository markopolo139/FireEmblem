package com.FireEmbelm.FireEmblem.app.data.entities;

import com.FireEmbelm.FireEmblem.app.data.entities.embeddable.EquipmentEmbeddable;
import com.FireEmbelm.FireEmblem.app.data.entities.embeddable.HealingItemEmbeddable;
import com.FireEmbelm.FireEmblem.app.data.entities.embeddable.WeaponEmbeddable;
import com.FireEmbelm.FireEmblem.business.value.equipment.Seals;
import com.FireEmbelm.FireEmblem.business.value.equipment.StatsUpItems;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "player_convoy")
public class ItemsConvoyEntity {

    @Id
    @GeneratedValue
    public Long convoyId;

    @Embedded
    public EquipmentEmbeddable equipmentEmbeddable;

    @CollectionTable(name = "player_healing_items", joinColumns = {
            @JoinColumn(name = "convoy_id  ")
    })
    public List<HealingItemEmbeddable> getHealingItems() {
        return equipmentEmbeddable.healingItems;
    }

    @CollectionTable(name = "player_weapons", joinColumns = {
            @JoinColumn(name = "convoy_id  ")
    })
    public List<WeaponEmbeddable> getWeapons() {
        return equipmentEmbeddable.weapons;
    }

    @CollectionTable(name = "player_seals", joinColumns = {
            @JoinColumn(name = "convoy_id  ")
    })
    public List<Seals> getSeals() {
        return equipmentEmbeddable.seals;
    }

    @CollectionTable(name = "player_stat_up_items", joinColumns = {
            @JoinColumn(name = "convoy_id  ")
    })
    public List<StatsUpItems> getStatsUpItems() {
        return equipmentEmbeddable.statsUpItems;
    }

    public ItemsConvoyEntity(Long convoyId, EquipmentEmbeddable equipmentEmbeddable) {
        this.convoyId = convoyId;
        this.equipmentEmbeddable = equipmentEmbeddable;
    }

    private ItemsConvoyEntity() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemsConvoyEntity that = (ItemsConvoyEntity) o;
        return Objects.equals(convoyId, that.convoyId) && Objects.equals(equipmentEmbeddable, that.equipmentEmbeddable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(convoyId, equipmentEmbeddable);
    }
}
