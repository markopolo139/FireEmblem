package com.FireEmbelm.FireEmblem.business.entitie;

import com.FireEmbelm.FireEmblem.business.utils.Utils;
import com.FireEmbelm.FireEmblem.business.value.ItemCategory;
import com.FireEmbelm.FireEmblem.business.value.Stats;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

//TODO : change to normal class in future
public enum CharacterClass  {
    LORD(
            "Lord",
            5,
            Collections.singletonList(ItemCategory.SWORD),
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
            Arrays.asList(ItemCategory.SWORD, ItemCategory.LANCE),
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
            Collections.singletonList(ItemCategory.LANCE),
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
            Collections.singletonList(ItemCategory.SWORD),
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
            Collections.singletonList(ItemCategory.AXE),
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
            Collections.singletonList(ItemCategory.SWORD),
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
            Collections.singletonList(ItemCategory.BOW),
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
            Collections.singletonList(ItemCategory.TOME),
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
            Collections.singletonList(ItemCategory.STAFF),
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
    private final HashMap<String, Stats> mBonusStats;

    public String getClassName() {
        return mClassName;
    }

    public int getMovement() {
        return mMovement;
    }

    public Collection<ItemCategory> getAllowedWeapons() {
        return mAllowedWeapons;
    }

    public HashMap<String, Stats> getBonusStats() {
        return mBonusStats;
    }

    CharacterClass(
            String className, int movement, Collection<ItemCategory> allowedWeapons, HashMap<String, Stats> bonusStats
    ) {
        mClassName = className;
        mMovement = movement;
        mAllowedWeapons = allowedWeapons;
        mBonusStats = bonusStats;
    }
}
