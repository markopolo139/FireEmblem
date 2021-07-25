package com.FireEmbelm.FireEmblem.app.data.entities;

import com.FireEmbelm.FireEmblem.app.data.entities.embeddable.HealingItemEmbeddable;
import com.FireEmbelm.FireEmblem.app.data.entities.embeddable.WeaponEmbeddable;
import com.FireEmbelm.FireEmblem.business.value.equipment.Seals;
import com.FireEmbelm.FireEmblem.business.value.equipment.StatsUpItems;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    public int money;

    @ElementCollection
    @CollectionTable(name = "player_healing_items", joinColumns = {
            @JoinColumn(name = "convoy_id")
    })
    @Nullable
    public List<HealingItemEmbeddable> healingItems;

    @ElementCollection
    @CollectionTable(name = "player_weapons", joinColumns = {
            @JoinColumn(name = "convoy_id")
    })
    @Nullable
    public List<WeaponEmbeddable> weapons;

    @ElementCollection
    @CollectionTable(name = "player_seals", joinColumns = {
            @JoinColumn(name = "convoy_id")
    })
    @Nullable
    @Enumerated(EnumType.STRING)
    public List<Seals> sealType;

    @ElementCollection
    @CollectionTable(name = "player_stat_up_items", joinColumns = {
            @JoinColumn(name = "convoy_id")
    })
    @Nullable
    @Enumerated(EnumType.STRING)
    public List<StatsUpItems> statUpType;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "game_id")
    public GameEntity gameId;

    public ItemsConvoyEntity(
            Long convoyId, int money, @Nullable List<HealingItemEmbeddable> healingItems,
            @Nullable List<WeaponEmbeddable> weapons, @Nullable List<Seals> sealType, @Nullable List<StatsUpItems> statUpType
    ) {
        this.convoyId = convoyId;
        this.money = money;
        this.healingItems = healingItems;
        this.weapons = weapons;
        this.sealType = sealType;
        this.statUpType = statUpType;
    }

    protected ItemsConvoyEntity() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemsConvoyEntity that = (ItemsConvoyEntity) o;
        return money == that.money
                && Objects.equals(convoyId, that.convoyId)
                && Objects.equals(healingItems, that.healingItems)
                && Objects.equals(weapons, that.weapons)
                && Objects.equals(sealType, that.sealType)
                && Objects.equals(statUpType, that.statUpType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(convoyId, money, healingItems, weapons, sealType, statUpType);
    }
}
