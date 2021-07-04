package com.FireEmbelm.FireEmblem.business.value;

//TODO: master_seal and hear_seal will have special effect
public enum StatsUpItems implements Equipment {
    STRENGTH_UP("Str UP", 1, 2000),
    MAGICK_UP("Mag UP", 1, 2000),
    DEFENSE_UP("Def UP", 2, 2000),
    RESISTANCE_UP("Res UP", 2, 2000),
    LUCK_UP("Lck UP", 3, 2000),
    SKILL_UP("Skill UP", 3, 2000),
    SPEED_UP("Spd UP", 1, 2000);

    private final String mItemName;
    private final int mHowMuchStatUp;
    private final int mWorth;

    StatsUpItems(String itemName, int howMuchStatUp, int worth) {
        mItemName = itemName;
        mHowMuchStatUp = howMuchStatUp;
        mWorth = worth;
    }

    public int getHowMuchStatUp() {
        return mHowMuchStatUp;
    }

    @Override
    public String getName() {
        return mItemName;
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
    public ItemCategory getItemCategory() {
        return ItemCategory.STATS_UP_ITEMS;
    }

    @Override
    public String getDescription() {
        return "Boost named after stat by one or two points";
    }
}
