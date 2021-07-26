package com.FireEmbelm.FireEmblem.web.models.request;

import com.FireEmbelm.FireEmblem.business.value.equipment.Seals;
import com.FireEmbelm.FireEmblem.business.value.equipment.StatsUpItems;
import com.FireEmbelm.FireEmblem.web.validation.character.ValidCharacterClass;
import com.FireEmbelm.FireEmblem.web.validation.character.ValidCharacterState;
import com.FireEmbelm.FireEmblem.web.validation.equipment.ValidSealList;
import com.FireEmbelm.FireEmblem.web.validation.equipment.ValidStatUpList;
import org.springframework.lang.Nullable;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.Objects;

public class EnemyModel {

    @NotBlank
    public String name;

    @Positive
    public int level;

    @Positive
    public int exp;

    @Positive
    public int remainingHealth;

    @NotEmpty
    public List<@Valid StatModel> stats;

    @Nullable
    public Integer currentEquippedItemId;

    @NotNull
    public List<@Valid WeaponModel> weapons;

    @NotNull
    public List<@Valid HealingItemModel> healingItems;

    @NotNull
    @ValidSealList
    public List<Seals> seals;

    @NotNull
    @ValidStatUpList
    public List<StatsUpItems> statsUpItems;

    @NotEmpty
    public List<@Valid WeaponProgressModel> weaponProgress;

    @ValidCharacterClass
    public String characterClass;

    @ValidCharacterState
    public String characterState;

    @NotNull
    public boolean moved;

    @Nullable
    public Integer dropItemId;

    @NotNull
    public boolean boss;

    @Positive
    public int goldDrop;

    public EnemyModel(
            String name, int level, int exp, int remainingHealth, List<StatModel> stats,
            @Nullable Integer currentEquippedItemId, List<WeaponModel> weapons,
            List<HealingItemModel> healingItems, List<Seals> seals, List<StatsUpItems> statsUpItems,
            List<WeaponProgressModel> weaponProgress, String characterClass, String characterState,
            boolean moved, @Nullable Integer dropItemId, boolean boss, int goldDrop
    ) {
        this.name = name;
        this.level = level;
        this.exp = exp;
        this.remainingHealth = remainingHealth;
        this.stats = stats;
        this.currentEquippedItemId = currentEquippedItemId;
        this.weapons = weapons;
        this.healingItems = healingItems;
        this.seals = seals;
        this.statsUpItems = statsUpItems;
        this.weaponProgress = weaponProgress;
        this.characterClass = characterClass;
        this.characterState = characterState;
        this.moved = moved;
        this.dropItemId = dropItemId;
        this.boss = boss;
        this.goldDrop = goldDrop;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnemyModel that = (EnemyModel) o;
        return level == that.level
                && exp == that.exp
                && remainingHealth == that.remainingHealth
                && moved == that.moved
                && boss == that.boss
                && goldDrop == that.goldDrop
                && Objects.equals(name, that.name)
                && stats.containsAll(that.stats) && stats.size() == that.stats.size()
                && Objects.equals(currentEquippedItemId, that.currentEquippedItemId)
                && Objects.equals(weapons, that.weapons)
                && Objects.equals(healingItems, that.healingItems)
                && Objects.equals(seals, that.seals)
                && Objects.equals(statsUpItems, that.statsUpItems)
                && weaponProgress.containsAll(that.weaponProgress) && weaponProgress.size() == that.weaponProgress.size()
                && Objects.equals(characterClass, that.characterClass)
                && Objects.equals(characterState, that.characterState)
                && Objects.equals(dropItemId, that.dropItemId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                name, level, exp, remainingHealth, stats, currentEquippedItemId, weapons, healingItems,
                seals, statsUpItems, weaponProgress, characterClass, characterState, moved,
                dropItemId, boss, goldDrop
        );
    }
}
