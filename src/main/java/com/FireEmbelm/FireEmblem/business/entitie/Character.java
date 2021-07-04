package com.FireEmbelm.FireEmblem.business.entitie;

import com.FireEmbelm.FireEmblem.business.value.character.related.CharacterState;
import com.FireEmbelm.FireEmblem.business.value.equipment.Equipment;
import com.FireEmbelm.FireEmblem.business.value.character.related.Stats;
import com.FireEmbelm.FireEmblem.business.value.character.related.WeaponProgress;

import java.util.HashMap;

//TODO : maybe abilities, add chances to increase to characters
public class Character extends BaseCharacter {
    public Character(
            String name, int level, int exp, int remainingHealth, HashMap<String, Stats> stats,
            Equipment currentEquipedItem, Equipment[] equipment, HashMap<String, WeaponProgress> weaponProgresses,
            CharacterClass characterClass, CharacterState characterState, boolean moved
    ) {
        super(
                name, level, exp, remainingHealth, stats, currentEquipedItem, equipment,
                weaponProgresses, characterClass, characterState, moved
        );
    }
}
