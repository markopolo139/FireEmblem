package com.FireEmbelm.FireEmblem.business.utils;

import com.FireEmbelm.FireEmblem.business.exceptions.InvalidRankException;
import com.FireEmbelm.FireEmblem.business.value.categories.WeaponCategory;
import com.FireEmbelm.FireEmblem.business.value.character.related.Stat;
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

    public static HashMap<StatsType, Stat> createStats(
            int hpValue, int hpChances,
            int strValue, int strChances,
            int magValue, int magChances,
            int skillValue, int skillChances,
            int spdValue, int spdChances,
            int luckValue, int luckChances,
            int defValue, int defChances,
            int resValue, int resChances){
        return (HashMap<StatsType, Stat>) (Stream.of(
                new Object[][] {
                        { StatsType.HEALTH, new Stat(StatsType.HEALTH, hpValue, hpChances) },
                        { StatsType.STRENGTH, new Stat(StatsType.STRENGTH, strValue, strChances) },
                        { StatsType.MAGICK, new Stat(StatsType.MAGICK, magValue, magChances) },
                        { StatsType.SKILL, new Stat(StatsType.SKILL, skillValue, skillChances) },
                        { StatsType.SPEED, new Stat(StatsType.SPEED, spdValue, spdChances) },
                        { StatsType.LUCK, new Stat(StatsType.LUCK, luckValue, luckChances) },
                        { StatsType.DEFENSE, new Stat(StatsType.DEFENSE, defValue, defChances) },
                        { StatsType.RESISTANCE, new Stat(StatsType.RESISTANCE, resValue, resChances) }
                }
        ).collect(Collectors.toMap(data -> (StatsType) data[0], data -> (Stat) data[1])));
    }

    public static HashMap<WeaponCategory, WeaponProgress> startUpWeaponProgress() {
        return (HashMap<WeaponCategory, WeaponProgress>) (Stream.of(
               new Object[][] {
                       { WeaponCategory.AXE, new WeaponProgress(WeaponCategory.AXE,0,1) },
                       { WeaponCategory.STAFF, new WeaponProgress(WeaponCategory.STAFF,0,1) },
                       { WeaponCategory.SWORD, new WeaponProgress(WeaponCategory.SWORD,0,1) },
                       { WeaponCategory.LANCE, new WeaponProgress(WeaponCategory.LANCE,0,1) },
                       { WeaponCategory.BOW, new WeaponProgress(WeaponCategory.BOW,0,1) },
                       { WeaponCategory.TOME, new WeaponProgress(WeaponCategory.TOME,0,1) }
               }
        ).collect(Collectors.toMap(data -> (WeaponCategory) data[0], data -> (WeaponProgress) data[1])));
    }

}
