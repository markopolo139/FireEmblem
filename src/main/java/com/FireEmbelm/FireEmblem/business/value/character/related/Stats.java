package com.FireEmbelm.FireEmblem.business.value.character.related;

public class Stats {

    private final StatsType mStatsType;
    private int mValue = 0;
    private int mChanceToIncrease = 0;

    public int getValue() {
        return mValue;
    }

    public void setValue(int value) {
        this.mValue = value;
    }

    public int getChanceToIncrease() {
        return mChanceToIncrease;
    }

    public void setChanceToIncrease(int chanceToIncrease) {
        mChanceToIncrease = chanceToIncrease;
    }

    public StatsType getStatsType() {
        return mStatsType;
    }

    public void setValueAndChances(int value, int chances) {
        mValue = value;
        mChanceToIncrease = chances;
    }

    public Stats(StatsType statsType, int value, int chanceToIncrease) {
        mStatsType = statsType;
        mValue = value;
        mChanceToIncrease = chanceToIncrease;
    }
}
