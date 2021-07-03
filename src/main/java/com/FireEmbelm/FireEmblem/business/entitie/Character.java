package com.FireEmbelm.FireEmblem.business.entitie;

import com.FireEmbelm.FireEmblem.business.value.CharacterState;
import com.FireEmbelm.FireEmblem.business.value.Equipment;
import com.FireEmbelm.FireEmblem.business.value.Stats;
import com.FireEmbelm.FireEmblem.business.value.WeaponProgress;

//TODO : maybe abilities, add chances to increase to characters
public class Character extends BaseCharacter {
    public Character(
            String name, int level, int exp, int remainingHealth, Stats[] stats,
            Equipment currentEquipedItem, Equipment[] equipment, WeaponProgress[] weaponProgresses,
            CharacterClass characterClass, CharacterState characterState, boolean moved
    ) {
        super(
                name, level, exp, remainingHealth, stats, currentEquipedItem, equipment,
                weaponProgresses, characterClass, characterState, moved
        );
    }
}
