package com.FireEmbelm.FireEmblem.business.value;

//TODO: all weapon category for one character
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
}
