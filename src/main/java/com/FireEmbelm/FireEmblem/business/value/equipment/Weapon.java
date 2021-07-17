package com.FireEmbelm.FireEmblem.business.value.equipment;

import com.FireEmbelm.FireEmblem.business.value.categories.ItemCategory;
import com.FireEmbelm.FireEmblem.business.value.categories.WeaponCategory;

import java.util.Objects;

public class Weapon implements Equipment {

    private final String mName;
    private final int mRank;
    private final int mMight;
    private final int mHit;
    private final int mAvo;
    private final int mCrit;
    private int mUses;
    private final int mRange;
    private final int mWorth;
    private final WeaponCategory mItemCategory;

    public int getRank() {
        return mRank;
    }

    public int getMight() {
        return mMight;
    }

    public int getHit() {
        return mHit;
    }

    public int getAvo() {
        return mAvo;
    }

    public int getCrit() {
        return mCrit;
    }

    @Override
    public String getName() {
        return mName;
    }

    @Override
    public int getWorth() {
        return mWorth;
    }

    @Override
    public int getRange() {
        return mRange;
    }

    @Override
    public int getUses() {
        return mUses;
    }

    @Override
    public void setUses(int uses) {
        mUses = uses;
    }

    @Override
    public ItemCategory getItemCategory() {
        return mItemCategory;
    }

    @Override
    public String getDescription() {
        return "Name = '" + mName+
                "\n Rank = " + mRank +
                "\n Mt = " + mMight +
                "\n Hit = " + mHit +
                "\n Avo = " + mAvo +
                "\n Crit = " + mCrit +
                "\n Uses = " + mUses +
                "\n Range = " + mRange +
                "\n Worth = " + mWorth +
                "\n ItemCategory = " + mItemCategory;
    }

    public Weapon(
            String name, int rank, int might, int hit, int avo, int crit,
            int uses, int range, int worth, WeaponCategory itemCategory
    ) {
        mName = name;
        mRank = rank;
        mMight = might;
        mHit = hit;
        mAvo = avo;
        mCrit = crit;
        mUses = uses;
        mRange = range;
        mWorth = worth;
        mItemCategory = itemCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Weapon weapon = (Weapon) o;
        return getRank() == weapon.getRank()
                && getMight() == weapon.getMight()
                && getHit() == weapon.getHit()
                && getAvo() == weapon.getAvo()
                && getCrit() == weapon.getCrit()
                && getUses() == weapon.getUses()
                && getRange() == weapon.getRange()
                && getWorth() == weapon.getWorth()
                && Objects.equals(getName(), weapon.getName())
                && getItemCategory() == weapon.getItemCategory();
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                getName(), getRank(), getMight(), getHit(), getAvo(),
                getCrit(), getUses(), getRange(), getWorth(), getItemCategory()
        );
    }
}
