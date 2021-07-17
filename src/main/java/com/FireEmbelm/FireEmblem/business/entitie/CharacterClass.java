package com.FireEmbelm.FireEmblem.business.entitie;

import com.FireEmbelm.FireEmblem.business.utils.Utils;
import com.FireEmbelm.FireEmblem.business.value.categories.WeaponCategory;
import com.FireEmbelm.FireEmblem.business.value.character.related.Stat;
import com.FireEmbelm.FireEmblem.business.value.character.related.StatsType;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public enum CharacterClass  {
    GREAT_LORD(
            "Great Lord",
            6,
            Arrays.asList(WeaponCategory.SWORD, WeaponCategory.LANCE),
            Utils.createStats(
                    23,40,
                    10,20,
                    0,0,
                    7,20,
                    9,20,
                    0,0,
                    10,10,
                    3,5
            ),
            true,
            Collections.emptyList()
    ),
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
            ),
            false,
            Collections.singletonList(GREAT_LORD)
    ),
    PALADIN(
            "Paladin",
            8,
            Arrays.asList(WeaponCategory.SWORD, WeaponCategory.LANCE),
            Utils.createStats(
                    25,45,
                    9,20,
                    1,0,
                    7,20,
                    8,20,
                    0,0,
                    10,10,
                    6,10
            ),
            true,
            Collections.emptyList()
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
            ),
            false,
            Collections.singletonList(PALADIN)
    ),
    GENERAL(
            "General",
            5,
            Arrays.asList(WeaponCategory.AXE, WeaponCategory.LANCE),
            Utils.createStats(
                    28,50,
                    12,25,
                    0,0,
                    7,15,
                    4,10,
                    0,0,
                    15,15,
                    3,10
            ),
            true,
            Collections.emptyList()
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
            ),
            false,
            Collections.singletonList(GENERAL)
    ),
    SWORDMASTER(
            "Swordmaster",
            6,
            Collections.singletonList(WeaponCategory.SWORD),
            Utils.createStats(
                    20,40,
                    7,20,
                    2,0,
                    11,25,
                    13,25,
                    0,0,
                    6,5,
                    4,10
            ),
            true,
            Collections.emptyList()
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
            ),
            false,
            Collections.singletonList(SWORDMASTER)
    ),
    WARRIOR(
            "Warrior",
            6,
            Collections.singletonList(WeaponCategory.AXE),
            Utils.createStats(
                    28,45,
                    12,25,
                    0,0,
                    8,20,
                    7,15,
                    0,0,
                    7,10,
                    3,5
            ),
            true,
            Collections.emptyList()
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
            ),
            false,
            Collections.singletonList(WARRIOR)
    ),
    HERO(
            "Hero",
            6,
            Arrays.asList(WeaponCategory.SWORD, WeaponCategory.AXE),
            Utils.createStats(
                    22,45,
                    8,20,
                    1,0,
                    11,25,
                    10,20,
                    0,0,
                    8,10,
                    3,5
            ),
            true,
            Collections.emptyList()
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
            ),
            false,
            Collections.singletonList(HERO)
    ),
    SNIPER(
            "Sniper",
            6,
            Collections.singletonList(WeaponCategory.BOW),
            Utils.createStats(
                    20,45,
                    7,15,
                    1,0,
                    12,30,
                    9,15,
                    0,0,
                    10,15,
                    3,5
            ),
            true,
            Collections.emptyList()
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
            ),
            false,
            Collections.singletonList(SNIPER)
    ),
    SAGE(
            "Sage",
            6,
            Collections.singletonList(WeaponCategory.TOME),
            Utils.createStats(
                    20,35,
                    1,0,
                    7,20,
                    5,20,
                    7,20,
                    0,0,
                    4,5,
                    5,10
            ),
            true,
            Collections.emptyList()
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
            ),
            false,
            Collections.singletonList(SAGE)
    ),
    WAR_MONK(
            "War monk",
            6,
            Arrays.asList(WeaponCategory.AXE, WeaponCategory.STAFF),
            Utils.createStats(
                    24,45,
                    5,15,
                    5,15,
                    4,10,
                    6,15,
                    0,0,
                    6,10,
                    6,10
            ),
            true,
            Collections.emptyList()
    ),
    PRIEST(
            "Priest",
            5,
            Collections.singletonList(WeaponCategory.STAFF),
            Utils.createStats(
                    16,35,
                    0,5,
                    3,15,
                    2,15,
                    4,15,
                    0,0,
                    1,5,
                    6,15
            ),
            false,
            Collections.singletonList(WAR_MONK)
    );

    private final String mClassName;
    private final int mMovement;
    private final List<WeaponCategory> mAllowedWeapons;
    private final HashMap<StatsType, Stat> mBonusStats;
    private final boolean mPromotedClass;
    private final List<CharacterClass> mPromoteToClasses;

    public String getClassName() {
        return mClassName;
    }

    public int getMovement() {
        return mMovement;
    }

    public List<WeaponCategory> getAllowedWeapons() {
        return mAllowedWeapons;
    }

    public HashMap<StatsType, Stat> getBonusStats() {
        return mBonusStats;
    }

    public boolean isPromotedClass() {
        return mPromotedClass;
    }

    public List<CharacterClass> getPromoteToClasses() {
        return mPromoteToClasses;
    }

    CharacterClass(
            String className, int movement, List<WeaponCategory> allowedWeapons,
            HashMap<StatsType, Stat> bonusStats, boolean promotedClass, List<CharacterClass> promoteToClasses
    ) {
        mClassName = className;
        mMovement = movement;
        mAllowedWeapons = allowedWeapons;
        mBonusStats = bonusStats;
        mPromotedClass = promotedClass;
        mPromoteToClasses = promoteToClasses;
    }
}
