package com.FireEmbelm.FireEmblem.business.value.character.related;

import com.FireEmbelm.FireEmblem.business.value.categories.WeaponCategory;

import java.util.Objects;

public class WeaponProgress {

    private final WeaponCategory mWeaponCategory;
    private int mProgress = 0;
    private int mRank = 1;

    public WeaponProgress(WeaponCategory WeaponCategory, int progress, int rank) {
        mWeaponCategory = WeaponCategory;
        mProgress = progress;
        this.mRank = rank;
    }

    public WeaponCategory getWeaponCategory() {
        return mWeaponCategory;
    }

    public int getProgress() {
        return mProgress;
    }

    public int getRank() {
        return mRank;
    }

    public void setProgress(int progress) {
        mProgress = progress;
    }

    public void setRank(int rank) {
        this.mRank = rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeaponProgress that = (WeaponProgress) o;
        return getProgress() == that.getProgress()
                && getRank() == that.getRank()
                && getWeaponCategory() == that.getWeaponCategory();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getWeaponCategory(), getProgress(), getRank());
    }
}
