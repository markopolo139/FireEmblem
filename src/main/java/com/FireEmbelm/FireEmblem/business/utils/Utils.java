package com.FireEmbelm.FireEmblem.business.utils;

import com.FireEmbelm.FireEmblem.business.exceptions.InvalidRankException;
import com.FireEmbelm.FireEmblem.business.value.categories.ConsumableItemCategory;
import com.FireEmbelm.FireEmblem.business.value.categories.WeaponCategory;
import com.FireEmbelm.FireEmblem.business.value.character.related.Stats;
import com.FireEmbelm.FireEmblem.business.value.character.related.StatsType;
import com.FireEmbelm.FireEmblem.business.value.character.related.WeaponProgress;

import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Utils {

    public static char changeToCharRank(int rank) throws InvalidRankException {
        switch (rank) {
            case 1 :
                return 'E';
            case 2 :
                return 'D';
            case 3 :
                return 'C';
            case 4 :
                return 'B';
            case 5 :
                return 'A';
            case 6 :
                return 'S';
            default:
                throw new InvalidRankException();
        }
    }

    public static HashMap<StatsType, Stats> createStats(
            int hpValue, int hpChances,
            int strValue, int strChances,
            int magValue, int magChances,
            int skillValue, int skillChances,
            int spdValue, int spdChances,
            int luckValue, int luckChances,
            int defValue, int defChances,
            int resValue, int resChances){
        return (HashMap<StatsType, Stats>) (Stream.of(
                new Object[][] {
                        { StatsType.HEALTH, new Stats(StatsType.HEALTH, hpValue, hpChances) },
                        { StatsType.STRENGTH, new Stats(StatsType.STRENGTH, strValue, strChances) },
                        { StatsType.MAGICK, new Stats(StatsType.MAGICK, magValue, magChances) },
                        { StatsType.SKILL, new Stats(StatsType.SKILL, skillValue, skillChances) },
                        { StatsType.SPEED, new Stats(StatsType.SPEED, spdValue, spdChances) },
                        { StatsType.LUCK, new Stats(StatsType.LUCK, luckValue, luckChances) },
                        { StatsType.DEFENSE, new Stats(StatsType.DEFENSE, defValue, defChances) },
                        { StatsType.RESISTANCE, new Stats(StatsType.RESISTANCE, resValue, resChances) }
                }
        ).collect(Collectors.toMap(data -> (StatsType) data[0], data -> (Stats) data[2])));
    }

    public static HashMap<String, WeaponProgress> startUpWeaponProgress() {
        return (HashMap<String, WeaponProgress>) (Stream.of(
               new Object[][] {
                       { WeaponCategory.AXE.name(), new WeaponProgress(WeaponCategory.AXE,0,1) },
                       { ConsumableItemCategory.STAFF.name(), new WeaponProgress(ConsumableItemCategory.STAFF,0,1) },
                       { WeaponCategory.SWORD.name(), new WeaponProgress(WeaponCategory.SWORD,0,1) },
                       { WeaponCategory.LANCE.name(), new WeaponProgress(WeaponCategory.LANCE,0,1) },
                       { WeaponCategory.BOW.name(), new WeaponProgress(WeaponCategory.BOW,0,1) },
                       { WeaponCategory.TOME.name(), new WeaponProgress(WeaponCategory.TOME,0,1) }
               }
        ).collect(Collectors.toMap(data -> (String) data[0], data -> (WeaponProgress) data[1])));
    }

}
