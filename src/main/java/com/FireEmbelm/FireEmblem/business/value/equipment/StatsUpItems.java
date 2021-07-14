package com.FireEmbelm.FireEmblem.business.value.equipment;

import com.FireEmbelm.FireEmblem.business.value.categories.ConsumableItemCategory;
import com.FireEmbelm.FireEmblem.business.value.categories.ItemCategory;
import com.FireEmbelm.FireEmblem.business.value.character.related.StatsType;

public enum StatsUpItems implements Equipment {
    STRENGTH_UP("Str UP", 1, 2000, StatsType.STRENGTH),
    MAGICK_UP("Mag UP", 1, 2000, StatsType.MAGICK),
    DEFENSE_UP("Def UP", 2, 2000, StatsType.DEFENSE),
    RESISTANCE_UP("Res UP", 2, 2000, StatsType.RESISTANCE),
    LUCK_UP("Lck UP", 3, 2000, StatsType.LUCK),
    SKILL_UP("Skill UP", 3, 2000, StatsType.SKILL),
    SPEED_UP("Spd UP", 1, 2000, StatsType.SPEED);

    private final String mItemName;
    private final int mHowMuchStatUp;
    private final int mWorth;
    private final StatsType mBoostedStat;

    StatsUpItems(String itemName, int howMuchStatUp, int worth, StatsType boostedStat) {
        mItemName = itemName;
        mHowMuchStatUp = howMuchStatUp;
        mWorth = worth;
        mBoostedStat = boostedStat;
    }

    public int getHowMuchStatUp() {
        return mHowMuchStatUp;
    }

    public StatsType getBoostedStat() {
        return mBoostedStat;
    }

    @Override
    public String getName() {
        return mItemName;
    }

    @Override
    public int getMight() {
        return 0;
    }

    @Override
    public int getWorth() {
        return mWorth;
    }

    @Override
    public int getRange() {
        return 0;
    }

    @Override
    public int getUses() {
        return 1;
    }

    @Override
    public void setUses(int uses) {
    }

    @Override
    public ItemCategory getItemCategory() {
        return ConsumableItemCategory.STATS_UP_ITEMS;
    }

    @Override
    public String getDescription() {
        return "Boost named after stat by one or two points";
    }
}
