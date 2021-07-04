package com.FireEmbelm.FireEmblem.business.entitie;

import com.FireEmbelm.FireEmblem.business.value.CharacterState;
import com.FireEmbelm.FireEmblem.business.value.Equipment;
import com.FireEmbelm.FireEmblem.business.value.Stats;
import com.FireEmbelm.FireEmblem.business.value.WeaponProgress;

import java.util.Objects;

public class Enemy extends BaseCharacter {

    private Equipment mDropItem;
    private boolean mBoss;
    private int mGoldDrop;

    public Equipment getDropItem() {
        return mDropItem;
    }

    public void setDropItem(Equipment dropItem) {
        mDropItem = dropItem;
    }

    public boolean isBoss() {
        return mBoss;
    }

    public void setBoss(boolean boss) {
        mBoss = boss;
    }

    public int getGoldDrop() {
        return mGoldDrop;
    }

    public void setGoldDrop(int goldDrop) {
        mGoldDrop = goldDrop;
    }

    public Enemy(
            int level, int exp, int remainingHealth, Stats[] stats, Equipment currentEquipedItem,
            Equipment[] equipment, WeaponProgress[] weaponProgresses, CharacterClass characterClass,
            CharacterState characterState, boolean moved, Equipment dropItem, boolean boss, int goldDrop
    ) {
        super(
                "Ruffian", level, exp, remainingHealth,
                stats, currentEquipedItem, equipment, weaponProgresses,
                characterClass, characterState, moved
        );
        mDropItem = dropItem;
        mBoss = boss;
        mGoldDrop = goldDrop;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Enemy enemy = (Enemy) o;
        return isBoss() == enemy.isBoss()
                && getGoldDrop() == enemy.getGoldDrop()
                && Objects.equals(getDropItem(), enemy.getDropItem());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getDropItem(), isBoss(), getGoldDrop());
    }
}
