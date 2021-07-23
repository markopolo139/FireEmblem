package com.FireEmbelm.FireEmblem.app.interactors;

import com.FireEmbelm.FireEmblem.app.converters.CharacterConverter;
import com.FireEmbelm.FireEmblem.app.data.entities.CharacterEntity;
import com.FireEmbelm.FireEmblem.app.data.repository.CharacterRepository;
import com.FireEmbelm.FireEmblem.business.entitie.Character;
import com.FireEmbelm.FireEmblem.business.entitie.CharacterClass;
import com.FireEmbelm.FireEmblem.business.exceptions.CharacterLevelException;
import com.FireEmbelm.FireEmblem.business.exceptions.PromoteException;
import com.FireEmbelm.FireEmblem.business.service.UnitPromoteService;
import com.FireEmbelm.FireEmblem.business.value.equipment.Seals;
import com.FireEmbelm.FireEmblem.web.models.request.CharacterModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//TODO:
// in interactor : check status (if dead, set spot to null and save dead character, and spot with null) (if alive, save spot with character
@Service
public class UnitPromoteInteractor {

    @Autowired
    private UnitPromoteService mUnitPromoteService;

    @Autowired
    private CharacterRepository mCharacterRepository;

    @Autowired
    private CharacterConverter mCharacterConverter;

    public List<CharacterClass> getPossibleClassesToPromote(CharacterModel characterModel, Seals seals)
            throws CharacterLevelException, PromoteException {
        return  mUnitPromoteService.getPossibleClassesToPromote(
                mCharacterConverter.convertModelToCharacter(characterModel),seals
        );
    }

    public void promoteCharacter(CharacterModel characterModel, List<CharacterClass> possibleClass, int possibleClassId) {

        Character character = mCharacterConverter.convertModelToCharacter(characterModel);

        mUnitPromoteService.promoteCharacter(character,possibleClass,possibleClassId);

        CharacterEntity characterEntity = mCharacterConverter.convertToEntity(character);
        characterEntity.characterId = mCharacterRepository.findByName(characterEntity.name).orElseThrow().characterId;

        mCharacterRepository.save(characterEntity);
    }

}
