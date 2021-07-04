package com.FireEmbelm.FireEmblem.business.entitie;

import com.FireEmbelm.FireEmblem.business.utils.Utils;
import com.FireEmbelm.FireEmblem.business.value.categories.ConsumableItemCategory;
import com.FireEmbelm.FireEmblem.business.value.categories.ItemCategory;
import com.FireEmbelm.FireEmblem.business.value.categories.WeaponCategory;
import com.FireEmbelm.FireEmblem.business.value.character.related.Stats;
import com.FireEmbelm.FireEmblem.business.value.character.related.StatsType;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

public enum CharacterClass  {
    LORD(
            "Lord",
            5,
            Collections.singletonList(WeaponCategory.SWORD),
            Utils.createStats(
                    18,40,
                    6,20,
                    0,0,
                    5,20,
                    7,20,
                    0,0,
                    7,10,
                    0,5
            )
    ),
    CAVALRY(
            "Cavalry",
            7,
            Arrays.asList(WeaponCategory.SWORD, WeaponCategory.LANCE),
            Utils.createStats(
                    18,45,
                    6,20,
                    0,0,
                    5,20,
                    6,20,
                    0,0,
                    7,10,
                    0,5
            )
    ),
    KNIGHT(
            "Knight",
            4,
            Collections.singletonList(WeaponCategory.LANCE),
            Utils.createStats(
                    18,50,
                    8,25,
                    0,0,
                    4,15,
                    2,10,
                    0,0,
                    11,15,
                    0,5
            )
    ),
    MYRMIDON(
            "Myrmidon",
            5,
            Collections.singletonList(WeaponCategory.SWORD),
            Utils.createStats(
                    16,40,
                    4,20,
                    1,0,
                    9,25,
                    10,25,
                    0,0,
                    4,5,
                    1,5
            )
    ),
    FIGHTER(
            "Fighter",
            5,
            Collections.singletonList(WeaponCategory.AXE),
            Utils.createStats(
                    20,45,
                    8,25,
                    0,0,
                    5,20,
                    5,15,
                    0,0,
                    4,10,
                    0,5
            )
    ),
    MERCENARY(
            "Mercenary",
            5,
            Collections.singletonList(WeaponCategory.SWORD),
            Utils.createStats(
                    18,45,
                    5,20,
                    0,0,
                    8,25,
                    7,20,
                    0,0,
                    5,10,
                    0,5
            )
    ),
    ARCHER(
            "Archer",
            5,
            Collections.singletonList(WeaponCategory.BOW),
            Utils.createStats(
                    16,45,
                    5,15,
                    0,0,
                    8,30,
                    6,15,
                    0,0,
                    5,10,
                    0,5
            )
    ),
    MAGE(
            "Mage",
            5,
            Collections.singletonList(WeaponCategory.TOME),
            Utils.createStats(
                    16,35,
                    0,0,
                    4,20,
                    3,20,
                    4,20,
                    0,0,
                    2,5,
                    3,10
            )
    ),
    PRIEST(
            "Priest",
            5,
            Collections.singletonList(ConsumableItemCategory.STAFF),
            Utils.createStats(
                    16,35,
                    0,5,
                    3,15,
                    2,15,
                    4,15,
                    0,0,
                    1,5,
                    6,15
            )
    );

    private final String mClassName;
    private final int mMovement;
    private final Collection<ItemCategory> mAllowedWeapons;
    private final HashMap<StatsType, Stats> mBonusStats;

    public String getClassName() {
        return mClassName;
    }

    public int getMovement() {
        return mMovement;
    }

    public Collection<ItemCategory> getAllowedWeapons() {
        return mAllowedWeapons;
    }

    public HashMap<StatsType, Stats> getBonusStats() {
        return mBonusStats;
    }

    CharacterClass(
            String className, int movement, Collection<ItemCategory> allowedWeapons, HashMap<StatsType, Stats> bonusStats
    ) {
        mClassName = className;
        mMovement = movement;
        mAllowedWeapons = allowedWeapons;
        mBonusStats = bonusStats;
    }
}
