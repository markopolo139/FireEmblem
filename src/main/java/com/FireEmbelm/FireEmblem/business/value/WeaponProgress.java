package com.FireEmbelm.FireEmblem.business.value;

//TODO: all weapon category for one character
public class WeaponProgress {

    private final ItemCategory mItemCategory;
    private int mProgress;
    private int rank = 1;

    public WeaponProgress(ItemCategory itemCategory, int progress) {
        mItemCategory = itemCategory;
        mProgress = progress;
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
