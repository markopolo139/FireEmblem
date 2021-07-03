package com.FireEmbelm.FireEmblem.business.entitie;

import com.FireEmbelm.FireEmblem.business.value.*;

import java.util.Arrays;
import java.util.Objects;

public abstract class BaseCharacter {

    private String mName;
    private int mLevel;
    private int mExp;
    private int mRemainingHealth;
    private Stats[] mStats;
    private Equipment mCurrentEquipedItem;
    private Equipment[] mEquipment;
    private WeaponProgress[] mWeaponProgresses;
    private CharacterClass mCharacterClass;
    private CharacterBattleStats mCharacterBattleStats;
    private CharacterState mCharacterState;
    private boolean mMoved;

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

    public Equipment getCurrentEquipedItem() {
        return mCurrentEquipedItem;
    }

    public void setCurrentEquipedItem(Equipment currentEquipedItem) {
        mCurrentEquipedItem = currentEquipedItem;
    }

    public CharacterBattleStats getCharacterBattleStats() {
        return mCharacterBattleStats;
    }

    public void setCharacterBattleStats(CharacterBattleStats characterBattleStats) {
        mCharacterBattleStats = characterBattleStats;
    }

    public CharacterState getCharacterState() {
        return mCharacterState;
    }

    public void setCharacterState(CharacterState characterState) {
        mCharacterState = characterState;
    }

    public boolean isMoved() {
        return mMoved;
    }

    public void setMoved(boolean moved) {
        this.mMoved = moved;
    }

    public BaseCharacter(
            String name, int level, int exp, int remainingHealth, Stats[] stats, Equipment currentEquipedItem,
            Equipment[] equipment, WeaponProgress[] weaponProgresses, CharacterClass characterClass,
            CharacterState characterState, boolean moved
    ) {
        mName = name;
        mLevel = level;
        mExp = exp;
        mRemainingHealth = remainingHealth;
        mStats = stats;
        mCurrentEquipedItem = currentEquipedItem;
        mEquipment = equipment;
        mWeaponProgresses = weaponProgresses;
        mCharacterClass = characterClass;
        mCharacterBattleStats = new CharacterBattleStats(this);
        mCharacterState = characterState;
        mMoved = moved;
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
                && Objects.equals(getCurrentEquipedItem(), that.getCurrentEquipedItem())
                && Arrays.equals(getEquipment(), that.getEquipment())
                && Arrays.equals(getWeaponProgresses(), that.getWeaponProgresses())
                && getCharacterClass() == that.getCharacterClass()
                && getCharacterState() == that.getCharacterState()
                && isMoved() == that.isMoved();
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(
                getName(), getLevel(), getExp(), getRemainingHealth(), getCharacterClass(), getCharacterState()
        );
        result = 31 * result + getCurrentEquipedItem().hashCode();
        result = 31 * result + Arrays.hashCode(getStats());
        result = 31 * result + Arrays.hashCode(getEquipment());
        result = 31 * result + Arrays.hashCode(getWeaponProgresses());
        return result;
    }
}
