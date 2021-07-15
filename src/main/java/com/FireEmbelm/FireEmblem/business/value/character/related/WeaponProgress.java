package com.FireEmbelm.FireEmblem.business.value.character.related;

import com.FireEmbelm.FireEmblem.business.value.categories.ItemCategory;

import java.util.Objects;

public class WeaponProgress {

    private final ItemCategory mItemCategory;
    private int mProgress = 0;
    private int rank = 1;

    public WeaponProgress(ItemCategory itemCategory, int progress, int rank) {
        mItemCategory = itemCategory;
        mProgress = progress;
        this.rank = rank;
    }

    public ItemCategory getItemCategory() {
        return mItemCategory;
    }

    public int getProgress() {
        return mProgress;
    }

    public int getRank() {
        return rank;
    }

    public void setProgress(int progress) {
        mProgress = progress;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeaponProgress that = (WeaponProgress) o;
        return getProgress() == that.getProgress()
                && getRank() == that.getRank()
                && getItemCategory() == that.getItemCategory();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getItemCategory(), getProgress(), getRank());
    }
}
