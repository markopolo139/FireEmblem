package com.FireEmbelm.FireEmblem.app.interactors;

import com.FireEmbelm.FireEmblem.app.converters.CharacterConverter;
import com.FireEmbelm.FireEmblem.app.data.entities.CharacterEntity;
import com.FireEmbelm.FireEmblem.app.data.repository.CharacterRepository;
import com.FireEmbelm.FireEmblem.business.entitie.Character;
import com.FireEmbelm.FireEmblem.business.entitie.CharacterClass;
import com.FireEmbelm.FireEmblem.business.exceptions.CharacterLevelException;
import com.FireEmbelm.FireEmblem.business.exceptions.InvalidEquipmentException;
import com.FireEmbelm.FireEmblem.business.exceptions.PromoteException;
import com.FireEmbelm.FireEmblem.business.service.UnitPromoteService;
import com.FireEmbelm.FireEmblem.business.value.equipment.Seals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnitPromoteInteractor {

    @Autowired
    private UnitPromoteService mUnitPromoteService;

    @Autowired
    private CharacterRepository mCharacterRepository;

    @Autowired
    private CharacterConverter mCharacterConverter;

    public List<CharacterClass> getPossibleClassesToPromote(String characterName, Long gameId, Seals seals)
            throws CharacterLevelException, PromoteException, InvalidEquipmentException {

        CharacterEntity characterEntity =
                mCharacterRepository.findByNameAndGameId_GameId(characterName, gameId).orElseThrow();

        if(characterEntity.sealType == null)
                throw new InvalidEquipmentException("This character does not have selected seal");

        if (!characterEntity.sealType.contains(seals))
                throw new InvalidEquipmentException("This character does not have selected seal");


        return  mUnitPromoteService.getPossibleClassesToPromote(
                mCharacterConverter.convertEntityToCharacter(characterEntity),seals
        );
    }

    public void promoteCharacter(
            String characterName, List<CharacterClass> possibleClass, int possibleClassId, Long gameId
    ) {

        CharacterEntity beforeChangeEntity =
                mCharacterRepository.findByNameAndGameId_GameId(characterName, gameId).orElseThrow();

        Character character = mCharacterConverter.convertEntityToCharacter(beforeChangeEntity);

        mUnitPromoteService.promoteCharacter(character,possibleClass,possibleClassId);

        CharacterEntity characterEntity = mCharacterConverter.convertToEntity(character);
        characterEntity.characterId = beforeChangeEntity.characterId;
        characterEntity.gameId = beforeChangeEntity.gameId;

        mCharacterRepository.save(characterEntity);
    }

}
