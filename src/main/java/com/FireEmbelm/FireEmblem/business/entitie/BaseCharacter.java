package com.FireEmbelm.FireEmblem.business.entitie;

import com.FireEmbelm.FireEmblem.business.utils.Utils;
import com.FireEmbelm.FireEmblem.business.value.Equipment;
import com.FireEmbelm.FireEmblem.business.value.Stats;
import com.FireEmbelm.FireEmblem.business.value.WeaponProgress;

import java.util.Arrays;
import java.util.Objects;

//TODO : create base stats (hp 5, and all stats 1 or 2)
//  create base weapon development (all weapons on rank 1)
public abstract class BaseCharacter {

    private String mName;
    private int mLevel;
    private int mExp;
    private int mRemainingHealth;
    private Stats[] mStats;
    private Equipment[] mEquipment = new Equipment[6];
    private WeaponProgress[] mWeaponProgresses = Utils.startUpWeaponProgress();
    private CharacterClass mCharacterClass;

    public int getLevel() {
        return mLevel;
    }

    public void setLevel(int level) {
        mLevel = level;
    }

    public int getExp() {
        return mExp;
    }

    public void setExp(int exp) {
        mExp = exp;
    }

    public int getRemainingHealth() {
        return mRemainingHealth;
    }

    public void setRemainingHealth(int remainingHealth) {
        mRemainingHealth = remainingHealth;
    }

    public Stats[] getStats() {
        return mStats;
    }

    public void setStats(Stats[] stats) {
        mStats = stats;
    }

    public Equipment[] getEquipment() {
        return mEquipment;
    }

    public void setEquipment(Equipment[] equipment) {
        mEquipment = equipment;
    }

    public WeaponProgress[] getWeaponProgresses() {
        return mWeaponProgresses;
    }

    public void setWeaponProgresses(WeaponProgress[] weaponProgresses) {
        mWeaponProgresses = weaponProgresses;
    }

    public CharacterClass getCharacterClass() {
        return mCharacterClass;
    }

    public void setCharacterClass(CharacterClass characterClass) {
        mCharacterClass = characterClass;
    }

    public String getName() {
        return mName;
    }

    public BaseCharacter(
            String name, int level, int exp, int remainingHealth, Stats[] stats,
            Equipment[] equipment, WeaponProgress[] weaponProgresses, CharacterClass characterClass
    ) {
        mName = name;
        mLevel = level;
        mExp = exp;
        mRemainingHealth = remainingHealth;
        mStats = stats;
        mEquipment = equipment;
        mWeaponProgresses = weaponProgresses;
        mCharacterClass = characterClass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseCharacter that = (BaseCharacter) o;
        return getLevel() == that.getLevel()
                && getExp() == that.getExp()
                && getRemainingHealth() == that.getRemainingHealth()
                && Objects.equals(getName(), that.getName())
                && Arrays.equals(getStats(), that.getStats())
                && Arrays.equals(getEquipment(), that.getEquipment())
                && Arrays.equals(getWeaponProgresses(), that.getWeaponProgresses())
                && getCharacterClass() == that.getCharacterClass();
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getName(), getLevel(), getExp(), getRemainingHealth(), getCharacterClass());
        result = 31 * result + Arrays.hashCode(getStats());
        result = 31 * result + Arrays.hashCode(getEquipment());
        result = 31 * result + Arrays.hashCode(getWeaponProgresses());
        return result;
    }
}
