package com.FireEmbelm.FireEmblem.business.entitie;

import com.FireEmbelm.FireEmblem.business.value.categories.WeaponCategory;
import com.FireEmbelm.FireEmblem.business.value.character.related.*;
import com.FireEmbelm.FireEmblem.business.value.equipment.Equipment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

public abstract class BaseCharacter {

    private String mName;
    private int mLevel;
    private int mExp;
    private int mRemainingHealth;
    private HashMap<StatsType, Stats> mStats;
    private Equipment mCurrentEquipedItem;
    private ArrayList<Equipment> mEquipment;
    private HashMap<WeaponCategory, WeaponProgress> mWeaponProgresses;
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

    public ArrayList<Equipment> getEquipment() {
        return mEquipment;
    }

    public void setEquipment(ArrayList<Equipment> equipment) {
        mEquipment = equipment;
    }

    public HashMap<StatsType, Stats> getStats() {
        return mStats;
    }

    public void setStats(HashMap<StatsType, Stats> stats) {
        mStats = stats;
    }

    public HashMap<WeaponCategory, WeaponProgress> getWeaponProgresses() {
        return mWeaponProgresses;
    }

    public void setWeaponProgresses(HashMap<WeaponCategory, WeaponProgress> weaponProgresses) {
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
            String name, int level, int exp, int remainingHealth, HashMap<StatsType, Stats> stats,
            Equipment currentEquipedItem, ArrayList<Equipment> equipment, HashMap<WeaponCategory, WeaponProgress> weaponProgresses,
            CharacterClass characterClass, CharacterState characterState, boolean moved
    ) {
        mName = name;
        mLevel = level;
        mExp = exp;
        mRemainingHealth = remainingHealth;
        mStats = stats;
        mEquipment = equipment;
        mCurrentEquipedItem = mEquipment.stream().filter(i -> i.equals(currentEquipedItem)).findAny().orElse(null);
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
                && isMoved() == that.isMoved()
                && Objects.equals(getName(), that.getName())
                && Objects.equals(getStats(), that.getStats())
                && Objects.equals(getCurrentEquipedItem(), that.getCurrentEquipedItem())
                && Objects.equals(getEquipment(), that.getEquipment())
                && Objects.equals(getWeaponProgresses(), that.getWeaponProgresses())
                && getCharacterClass() == that.getCharacterClass()
                && Objects.equals(getCharacterBattleStats(), that.getCharacterBattleStats())
                && getCharacterState() == that.getCharacterState();
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(
                getName(), getLevel(), getExp(), getRemainingHealth(), getStats(), getCurrentEquipedItem(),
                getWeaponProgresses(), getCharacterClass(), getCharacterBattleStats(), getCharacterState(), isMoved()
        );
        result = 31 * result + getEquipment().hashCode();
        return result;
    }
}
