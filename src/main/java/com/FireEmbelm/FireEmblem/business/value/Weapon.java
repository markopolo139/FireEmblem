package com.FireEmbelm.FireEmblem.business.value;

public class Weapon implements Equipment {

    private final String mName;
    private final int mRank;
    private final int mMt;
    private final int mHit;
    private final int mAvo;
    private final int mCrit;
    private final int mUses;
    private final int mRange;
    private final int mWorth;
    private final Stats[] mBonusStats;
    private final ItemCategory mItemCategory;

    public Weapon(
            String name, int rank, int mt, int hit, int avo,
            int crit, int uses, int range, int worth, Stats[] bonusStats, ItemCategory itemCategory
    ) {
        mName = name;
        mRank = rank;
        mMt = mt;
        mHit = hit;
        mAvo = avo;
        mCrit = crit;
        mUses = uses;
        mRange = range;
        mWorth = worth;
        mBonusStats = bonusStats;
        mItemCategory = itemCategory;
    }

    public int getRank() {
        return mRank;
    }

    public int getMt() {
        return mMt;
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

    public Stats[] getBonusStats() {
        return mBonusStats;
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
    public ItemCategory getItemCategory() {
        return mItemCategory;
    }
}
