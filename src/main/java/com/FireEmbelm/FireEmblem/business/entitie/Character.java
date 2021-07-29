package com.FireEmbelm.FireEmblem.business.entitie;

import com.FireEmbelm.FireEmblem.business.value.categories.WeaponCategory;
import com.FireEmbelm.FireEmblem.business.value.character.related.CharacterState;
import com.FireEmbelm.FireEmblem.business.value.character.related.Stat;
import com.FireEmbelm.FireEmblem.business.value.character.related.StatsType;
import com.FireEmbelm.FireEmblem.business.value.character.related.WeaponProgress;
import com.FireEmbelm.FireEmblem.business.value.equipment.Equipment;

import java.util.ArrayList;
import java.util.HashMap;

public class Character extends BaseCharacter {
    public Character(
            String name, int level, int exp, int remainingHealth, HashMap<StatsType, Stat> stats,
            Equipment currentEquippedItem, ArrayList<Equipment> equipment, HashMap<WeaponCategory, WeaponProgress> weaponProgresses,
            CharacterClass characterClass, CharacterState characterState, boolean moved
    ) {
        super(
                name, level, exp, remainingHealth, stats, currentEquippedItem, equipment,
                weaponProgresses, characterClass, characterState, moved
        );
    }
}
