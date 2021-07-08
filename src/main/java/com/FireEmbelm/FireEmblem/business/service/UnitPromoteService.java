package com.FireEmbelm.FireEmblem.business.service;

import com.FireEmbelm.FireEmblem.business.entitie.Character;
import com.FireEmbelm.FireEmblem.business.entitie.CharacterClass;
import com.FireEmbelm.FireEmblem.business.exceptions.PromoteException;
import com.FireEmbelm.FireEmblem.business.value.character.related.StatsType;
import com.FireEmbelm.FireEmblem.business.value.equipment.Seals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

//TODO : think where to put or is there correct
// front end must give only seals there
public class UnitPromoteService {

    private List<CharacterClass> mPossibleClasses;

    public Collection<CharacterClass> getPossibleClassesToPromote(Character character, Seals seals)
            throws PromoteException {

        mPossibleClasses = seals.possiblePromoteClass(character);
        return mPossibleClasses;
    }

    public void promoteCharacter(Character character, int possibleClassId) {

        character.setCharacterClass(mPossibleClasses.get(possibleClassId));
        character.setLevel(1);
        character.setExp(0);
        character.setRemainingHealth(
                character.getStats().get(StatsType.HEALTH).getValue()
                        + character.getCharacterClass().getBonusStats().get(StatsType.HEALTH).getValue()
        );

        if(!character.getCharacterClass().getAllowedWeapons().contains(character.getCurrentEquipedItem().getItemCategory())) {
            character.setCurrentEquipedItem(null);
        }

        character.getCharacterBattleStats().calculateBattleStats(character);
    }
}
