package com.FireEmbelm.FireEmblem.business.value.character.related;

import java.util.Objects;

public class Stat {

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

    public Stat(StatsType statsType, int value, int chanceToIncrease) {
        mStatsType = statsType;
        mValue = value;
        mChanceToIncrease = chanceToIncrease;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stat stat = (Stat) o;
        return getValue() == stat.getValue()
                && getChanceToIncrease() == stat.getChanceToIncrease()
                && getStatsType() == stat.getStatsType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStatsType(), getValue(), getChanceToIncrease());
    }
}
