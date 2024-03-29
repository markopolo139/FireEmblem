package com.FireEmbelm.FireEmblem.business.service;

import com.FireEmbelm.FireEmblem.business.entitie.Character;
import com.FireEmbelm.FireEmblem.business.entitie.CharacterClass;
import com.FireEmbelm.FireEmblem.business.exceptions.CharacterLevelException;
import com.FireEmbelm.FireEmblem.business.exceptions.PromoteException;
import com.FireEmbelm.FireEmblem.business.value.character.related.StatsType;
import com.FireEmbelm.FireEmblem.business.value.equipment.Seals;

import java.util.List;

public class UnitPromoteService {

    public static final int PROMOTE_EDGE = 10;

    public List<CharacterClass> getPossibleClassesToPromote(Character character, Seals seals)
            throws PromoteException, CharacterLevelException {

        if(character.getLevel() < PROMOTE_EDGE)
            throw new CharacterLevelException();

        return seals.possiblePromoteClass(character);
    }

    public void promoteCharacter(Character character, List<CharacterClass> possibleClass, int possibleClassId) {

        character.setCharacterClass(possibleClass.get(possibleClassId));
        character.setLevel(1);
        character.setExp(0);
        character.setRemainingHealth(
                character.getStats().get(StatsType.HEALTH).getValue()
                        + character.getCharacterClass().getBonusStats().get(StatsType.HEALTH).getValue()
        );

        if(character.getCurrentEquippedItem() != null)
            if(!character.getCharacterClass().getAllowedWeapons().contains(character.getCurrentEquippedItem().getItemCategory()))
                character.setCurrentEquippedItem(null);

    }
}
