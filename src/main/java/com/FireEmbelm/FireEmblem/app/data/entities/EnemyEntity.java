package com.FireEmbelm.FireEmblem.app.data.entities;

import com.FireEmbelm.FireEmblem.app.data.entities.embeddable.HealingItemEmbeddable;
import com.FireEmbelm.FireEmblem.app.data.entities.embeddable.StatEmbeddable;
import com.FireEmbelm.FireEmblem.app.data.entities.embeddable.WeaponEmbeddable;
import com.FireEmbelm.FireEmblem.app.data.entities.embeddable.WeaponProgressEmbeddable;
import com.FireEmbelm.FireEmblem.business.entitie.CharacterClass;
import com.FireEmbelm.FireEmblem.business.value.character.related.CharacterState;
import com.FireEmbelm.FireEmblem.business.value.equipment.Seals;
import com.FireEmbelm.FireEmblem.business.value.equipment.StatsUpItems;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "enemies")
public class EnemyEntity {

    @Id
    @GeneratedValue
    public Long enemyId;

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

    public int dropItemId;

    public boolean boss;

    public int goldDrop;

    @ElementCollection
    @CollectionTable(name = "enemies_equipment_heal_items", joinColumns = {
            @JoinColumn(name = "enemy_id ")
    })
    @Nullable
    public List<HealingItemEmbeddable> healingItems;

    @ElementCollection
    @CollectionTable(name = "enemies_equipment_weapons", joinColumns = {
            @JoinColumn(name = "enemy_id ")
    })
    @Nullable
    public List<WeaponEmbeddable> weapons;

    @ElementCollection
    @CollectionTable(name = "enemies_equipment_seals", joinColumns = {
            @JoinColumn(name = "enemy_id ")
    })
    @Nullable
    @Enumerated(EnumType.STRING)
    public List<Seals> sealType;

    @ElementCollection
    @CollectionTable(name = "enemies_equipment_stats_up", joinColumns = {
            @JoinColumn(name = "enemy_id ")
    })
    @Nullable
    @Enumerated(EnumType.STRING)
    public List<StatsUpItems> statUpType;

    @ElementCollection
    @CollectionTable(name = "enemies_stats", joinColumns = {
            @JoinColumn(name = "enemy_id ")
    })
    public List<StatEmbeddable> stats;

    @ElementCollection
    @CollectionTable(name = "enemies_weapon_progress", joinColumns = {
            @JoinColumn(name = "enemy_id ")
    })
    public List<WeaponProgressEmbeddable> weaponProgress;

    public EnemyEntity(
            Long enemyId, String name, int level, int exp, int remainingHealth, @Nullable Integer currentEquipedItemId,
            CharacterClass characterClass, CharacterState characterState, boolean moved, int dropItemId, boolean boss,
            int goldDrop, @Nullable List<HealingItemEmbeddable> healingItems, @Nullable List<WeaponEmbeddable> weapons,
            @Nullable List<Seals> sealType, @Nullable List<StatsUpItems> statUpType, List<StatEmbeddable> stats,
            List<WeaponProgressEmbeddable> weaponProgress
    ) {
        this.enemyId = enemyId;
        this.name = name;
        this.level = level;
        this.exp = exp;
        this.remainingHealth = remainingHealth;
        this.currentEquipedItemId = currentEquipedItemId;
        this.characterClass = characterClass;
        this.characterState = characterState;
        this.moved = moved;
        this.dropItemId = dropItemId;
        this.boss = boss;
        this.goldDrop = goldDrop;
        this.healingItems = healingItems;
        this.weapons = weapons;
        this.sealType = sealType;
        this.statUpType = statUpType;
        this.stats = stats;
        this.weaponProgress = weaponProgress;
    }

    protected EnemyEntity() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnemyEntity that = (EnemyEntity) o;
        return Objects.equals(enemyId, that.enemyId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(enemyId);
    }
}
