package com.FireEmbelm.FireEmblem.business.utils;

import com.FireEmbelm.FireEmblem.business.exceptions.InvalidRankException;
import com.FireEmbelm.FireEmblem.business.value.categories.ItemCategory;
import com.FireEmbelm.FireEmblem.business.value.character.related.Stats;
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

    public static HashMap<String, Stats> createStats(
            int hpValue, int hpChances,
            int strValue, int strChances,
            int magValue, int magChances,
            int skillValue, int skillChances,
            int spdValue, int spdChances,
            int luckValue, int luckChances,
            int defValue, int defChances,
            int resValue, int resChances){
        return (HashMap<String, Stats>) (Stream.of(
                new Object[][] {
                        { Stats.HEALTH.name(), Stats.HEALTH.setValueAndChances(hpValue, hpChances) },
                        { Stats.STRENGTH.name(), Stats.STRENGTH.setValueAndChances(strValue, strChances) },
                        { Stats.MAGICK.name(), Stats.MAGICK.setValueAndChances(magValue, magChances) },
                        { Stats.SKILL.name(), Stats.SKILL.setValueAndChances(skillValue, skillChances) },
                        { Stats.SPEED.name(), Stats.SPEED.setValueAndChances(spdValue, spdChances) },
                        { Stats.LUCK.name(), Stats.LUCK.setValueAndChances(luckValue, luckChances) },
                        { Stats.DEFENSE.name(), Stats.DEFENSE.setValueAndChances(defValue, defChances) },
                        { Stats.RESISTANCE.name(), Stats.RESISTANCE.setValueAndChances(resValue, resChances) }
                }
        ).collect(Collectors.toMap(data -> (String) data[0], data -> (Stats) data[2])));
    }

    public static HashMap<String, WeaponProgress> startUpWeaponProgress() {
        return (HashMap<String, WeaponProgress>) (Stream.of(
               new Object[][] {
                       { ItemCategory.AXE.name(), new WeaponProgress(ItemCategory.AXE,0,1) },
                       { ItemCategory.STAFF.name(), new WeaponProgress(ItemCategory.STAFF,0,1) },
                       { ItemCategory.SWORD.name(), new WeaponProgress(ItemCategory.SWORD,0,1) },
                       { ItemCategory.LANCE.name(), new WeaponProgress(ItemCategory.LANCE,0,1) },
                       { ItemCategory.BOW.name(), new WeaponProgress(ItemCategory.BOW,0,1) },
                       { ItemCategory.TOME.name(), new WeaponProgress(ItemCategory.TOME,0,1) }
               }
        ).collect(Collectors.toMap(data -> (String) data[0], data -> (WeaponProgress) data[1])));
    }

}
