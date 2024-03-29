package com.FireEmbelm.FireEmblem.app.data.entities;

import com.FireEmbelm.FireEmblem.app.data.entities.embeddable.HealingItemEmbeddable;
import com.FireEmbelm.FireEmblem.app.data.entities.embeddable.StatEmbeddable;
import com.FireEmbelm.FireEmblem.app.data.entities.embeddable.WeaponEmbeddable;
import com.FireEmbelm.FireEmblem.app.data.entities.embeddable.WeaponProgressEmbeddable;
import com.FireEmbelm.FireEmblem.business.entitie.CharacterClass;
import com.FireEmbelm.FireEmblem.business.value.character.related.CharacterState;
import com.FireEmbelm.FireEmblem.business.value.equipment.Seals;
import com.FireEmbelm.FireEmblem.business.value.equipment.StatsUpItems;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "characters")
public class CharacterEntity {

    @Id
    @GeneratedValue
    public Long characterId;

    public String name;

    public int level;

    public int exp;

    public int remainingHealth;

    @Nullable
    public Integer currentEquippedItemId;

    @Enumerated(EnumType.STRING)
    public CharacterClass characterClass;

    @Enumerated(EnumType.STRING)
    public CharacterState characterState;

    public boolean moved;

    @ElementCollection
    @CollectionTable(name = "character_equipment_weapons", joinColumns = {
            @JoinColumn(name = "character_id")
    })
    @Nullable
    public List<WeaponEmbeddable> weapons;

    @ElementCollection
    @CollectionTable(name = "character_equipment_heal_items", joinColumns = {
            @JoinColumn(name = "character_id")
    })
    @Nullable
    public List<HealingItemEmbeddable> healingItems;

    @ElementCollection
    @CollectionTable(name = "character_equipment_seals", joinColumns = {
            @JoinColumn(name = "character_id")
    })
    @Nullable
    @Enumerated(EnumType.STRING)
    public List<Seals> sealType;

    @ElementCollection
    @CollectionTable(name = "character_equipment_stats_up", joinColumns = {
            @JoinColumn(name = "character_id")
    })
    @Nullable
    @Enumerated(EnumType.STRING)
    public List<StatsUpItems> statUpType;

    @ElementCollection
    @CollectionTable(name = "character_stats", joinColumns = {
            @JoinColumn(name = "character_id")
    })
    public List<StatEmbeddable> stats;

    @ElementCollection
    @CollectionTable(name = "character_weapon_progress", joinColumns = {
            @JoinColumn(name = "character_id")
    })
    public List<WeaponProgressEmbeddable> weaponProgress;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id")
    public GameEntity gameId;

    protected CharacterEntity() {
    }

    public CharacterEntity(
            Long characterId, String name, int level, int exp, int remainingHealth,
            @Nullable Integer currentEquippedItemId, CharacterClass characterClass,
            CharacterState characterState, boolean moved, @Nullable List<WeaponEmbeddable> weapons,
            @Nullable List<HealingItemEmbeddable> healingItems, @Nullable List<Seals> seals,
            @Nullable List<StatsUpItems> statUpType, List<StatEmbeddable> stats,
            List<WeaponProgressEmbeddable> weaponProgress
    ) {
        this.characterId = characterId;
        this.name = name;
        this.level = level;
        this.exp = exp;
        this.remainingHealth = remainingHealth;
        this.currentEquippedItemId = currentEquippedItemId;
        this.characterClass = characterClass;
        this.characterState = characterState;
        this.moved = moved;
        this.weapons = weapons;
        this.healingItems = healingItems;
        this.sealType = seals;
        this.statUpType = statUpType;
        this.stats = stats;
        this.weaponProgress = weaponProgress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CharacterEntity that = (CharacterEntity) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
