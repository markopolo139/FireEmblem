package com.FireEmbelm.FireEmblem.business.utils;

import com.FireEmbelm.FireEmblem.business.exceptions.InvalidRankException;
import com.FireEmbelm.FireEmblem.business.value.ItemCategory;
import com.FireEmbelm.FireEmblem.business.value.Stats;
import com.FireEmbelm.FireEmblem.business.value.WeaponProgress;

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

    public static Stats[] createStats(
            int hpValue, int hpChances,
            int strValue, int strChances,
            int magValue, int magChances,
            int skillValue, int skillChances,
            int spdValue, int spdChances,
            int luckValue, int luckChances,
            int defValue, int defChances,
            int resValue, int resChances){
        return new Stats[] {
                Stats.HEALTH.setValueAndChances(hpValue, hpChances),
                Stats.STRENGTH.setValueAndChances(strValue, strChances),
                Stats.MAGICK.setValueAndChances(magValue, magChances),
                Stats.SKILL.setValueAndChances(skillValue, skillChances),
                Stats.SPEED.setValueAndChances(spdValue, spdChances),
                Stats.LUCK.setValueAndChances(luckValue, luckChances),
                Stats.DEFENSE.setValueAndChances(defValue, defChances),
                Stats.RESISTANCE.setValueAndChances(resValue, resChances)
        };
    }

    public static WeaponProgress[] startUpWeaponProgress() {
        return new WeaponProgress[] {
                new WeaponProgress(ItemCategory.AXE,0,1),
                new WeaponProgress(ItemCategory.STAFF,0,1),
                new WeaponProgress(ItemCategory.SWORD,0,1),
                new WeaponProgress(ItemCategory.LANCE,0,1),
                new WeaponProgress(ItemCategory.BOW,0,1),
                new WeaponProgress(ItemCategory.TOME,0,1),
        };
    }

}
