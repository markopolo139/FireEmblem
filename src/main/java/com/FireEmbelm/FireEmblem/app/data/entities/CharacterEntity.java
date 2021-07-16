package com.FireEmbelm.FireEmblem.app.data.entities;

import com.FireEmbelm.FireEmblem.app.data.entities.embeddable.*;
import com.FireEmbelm.FireEmblem.business.entitie.CharacterClass;
import com.FireEmbelm.FireEmblem.business.value.character.related.CharacterState;
import com.FireEmbelm.FireEmblem.business.value.equipment.Seals;
import com.FireEmbelm.FireEmblem.business.value.equipment.StatsUpItems;
import org.hibernate.annotations.NaturalId;
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

    @NaturalId
    public String name;

    public int level;

    public int exp;

    public int remainingHealth;

    @Nullable
    public Integer currentEquipedItemId;

    @Enumerated(EnumType.STRING)
    public CharacterClass characterClass;

    @Enumerated(EnumType.STRING)
    public CharacterState characterState;

    public boolean moved;

    @Embedded
    public EquipmentEmbeddable equipmentEmbeddable;

    @ElementCollection
    @CollectionTable(name = "character_stats", joinColumns = {
            @JoinColumn(name = "character_id ")
    })
    public List<StatEmbeddable> stats;

    @ElementCollection
    @CollectionTable(name = "character_weapon_progress", joinColumns = {
            @JoinColumn(name = "character_id ")
    })
    public List<WeaponProgressEmbeddable> weaponProgress;

    @CollectionTable(name = "character_equipment_heal_items", joinColumns = {
            @JoinColumn(name = "character_id ")
    })
    public List<HealingItemEmbeddable> getHealingItems() {
        return equipmentEmbeddable.healingItems;
    }

    @CollectionTable(name = "character_equipment_weapons", joinColumns = {
            @JoinColumn(name = "character_id ")
    })
    public List<WeaponEmbeddable> getWeapons() {
        return equipmentEmbeddable.weapons;
    }

    @CollectionTable(name = "character_equipment_seals", joinColumns = {
            @JoinColumn(name = "character_id ")
    })
    public List<Seals> getSeals() {
        return equipmentEmbeddable.seals;
    }

    @CollectionTable(name = "character_equipment_stats_up", joinColumns = {
            @JoinColumn(name = "character_id ")
    })
    public List<StatsUpItems> getStatsUpItems() {
        return equipmentEmbeddable.statsUpItems;
    }

    private CharacterEntity() {
    }

    public CharacterEntity(
            Long characterId, String name, int level, int exp, int remainingHealth,
            @Nullable Integer currentEquipedItemId, CharacterClass characterClass, CharacterState characterState,
            boolean moved, EquipmentEmbeddable equipmentEmbeddable, List<StatEmbeddable> stats, List<WeaponProgressEmbeddable> weaponProgress
    ) {
        this.characterId = characterId;
        this.name = name;
        this.level = level;
        this.exp = exp;
        this.remainingHealth = remainingHealth;
        this.currentEquipedItemId = currentEquipedItemId;
        this.characterClass = characterClass;
        this.characterState = characterState;
        this.moved = moved;
        this.equipmentEmbeddable = equipmentEmbeddable;
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
