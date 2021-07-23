package com.FireEmbelm.FireEmblem.web.models.request;

import com.FireEmbelm.FireEmblem.business.value.equipment.Seals;
import com.FireEmbelm.FireEmblem.business.value.equipment.StatsUpItems;
import com.FireEmbelm.FireEmblem.web.validation.ValidCharacterClass;
import com.FireEmbelm.FireEmblem.web.validation.ValidCharacterState;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.Objects;

public class CharacterModel {

    @NotBlank
    public String name;

    @Positive
    public int level;

    @Positive
    public int exp;

    @Positive
    public int remainingHealth;

    @NotEmpty
    public List<StatModel> stats;

    @Nullable
    public Integer currentEquippedItemId;

    @NotNull
    public List<WeaponModel> weapons;

    @NotNull
    public List<HealingItemModel> healingItems;

    @NotNull
    public List<Seals> seals;

    @NotNull
    public List<StatsUpItems> statsUpItems;

    @NotEmpty
    public List<WeaponProgressModel> weaponProgress;

    @ValidCharacterClass
    public String characterClass;

    @ValidCharacterState
    public String characterState;

    @NotNull
    public boolean moved;

    public CharacterModel(
            String name, int level, int exp, int remainingHealth,
            List<StatModel> stats, @Nullable Integer currentEquippedItemId,
            List<WeaponModel> weapons, List<HealingItemModel> healingItems,
            List<Seals> seals, List<StatsUpItems> statsUpItems, List<WeaponProgressModel> weaponProgress,
            String characterClass, String characterState, boolean moved
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
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CharacterModel that = (CharacterModel) o;
        return level == that.level
                && exp == that.exp
                && remainingHealth == that.remainingHealth
                && moved == that.moved
                && Objects.equals(name, that.name)
                && stats.containsAll(that.stats) && stats.size() == that.stats.size()
                && Objects.equals(currentEquippedItemId, that.currentEquippedItemId)
                && Objects.equals(weapons, that.weapons)
                && Objects.equals(healingItems, that.healingItems)
                && Objects.equals(seals, that.seals)
                && Objects.equals(statsUpItems, that.statsUpItems)
                && weaponProgress.containsAll(that.weaponProgress) && weaponProgress.size() == that.weaponProgress.size()
                && Objects.equals(characterClass, that.characterClass)
                && Objects.equals(characterState, that.characterState);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                name, level, exp, remainingHealth, stats, currentEquippedItemId,
                weapons, healingItems, seals, statsUpItems, weaponProgress, characterClass,
                characterState, moved
        );
    }
}
