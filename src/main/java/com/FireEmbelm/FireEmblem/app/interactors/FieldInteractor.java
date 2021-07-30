package com.FireEmbelm.FireEmblem.app.interactors;

import com.FireEmbelm.FireEmblem.app.converters.CharacterConverter;
import com.FireEmbelm.FireEmblem.app.converters.SpotConverter;
import com.FireEmbelm.FireEmblem.app.data.entities.CharacterEntity;
import com.FireEmbelm.FireEmblem.app.data.entities.SpotEntity;
import com.FireEmbelm.FireEmblem.app.data.repository.CharacterRepository;
import com.FireEmbelm.FireEmblem.app.data.repository.SpotRepository;
import com.FireEmbelm.FireEmblem.app.exceptions.CharacterAlreadyMovedException;
import com.FireEmbelm.FireEmblem.business.entitie.Character;
import com.FireEmbelm.FireEmblem.business.exceptions.InvalidEquipmentException;
import com.FireEmbelm.FireEmblem.business.exceptions.InvalidSpotException;
import com.FireEmbelm.FireEmblem.business.service.FieldService;
import com.FireEmbelm.FireEmblem.business.value.field.Spot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FieldInteractor {

    @Autowired
    private FieldService mFieldService;

    @Autowired
    private SpotRepository mSpotRepository;

    @Autowired
    private SpotConverter mSpotConverter;

    @Autowired
    private CharacterRepository mCharacterRepository;

    @Autowired
    private CharacterConverter mCharacterConverter;

    public void placeCharacter(String characterName, int spotHeight, int spotWidth, Long gameId)
            throws InvalidSpotException {

        CharacterEntity beforeChangeCharacter =
                mCharacterRepository.findByNameAndGameId_GameId(characterName, gameId).orElseThrow();

        SpotEntity beforeChangeSpot =
                mSpotRepository.findByHeightAndWidthAndGameId_GameId(spotHeight, spotWidth, gameId).orElseThrow();

        Character character = mCharacterConverter.convertEntityToCharacter(beforeChangeCharacter);
        Spot spot = mSpotConverter.convertEntityToSpot(beforeChangeSpot);

        mFieldService.placeCharacter(character,spot);

        SpotEntity spotEntity = mSpotConverter.convertToEntity(spot);

        saveCharacter(spotEntity.characterId,  beforeChangeCharacter);
        saveSpot(spotEntity, beforeChangeSpot);

    }

    public void moveCharacter(
            int characterSpotHeight, int characterSpotWidth,
            int moveToSpotHeight, int moveToSpotWidth,
            Long gameId
    )
            throws InvalidSpotException, CharacterAlreadyMovedException {

        SpotEntity beforeChangeCharacterSpot = mSpotRepository
                .findByHeightAndWidthAndGameId_GameId(characterSpotHeight, characterSpotWidth, gameId).orElseThrow();

        if(beforeChangeCharacterSpot.characterId == null)
            throw new InvalidSpotException("Can't select spot without character on it");

        if (beforeChangeCharacterSpot.characterId.moved)
                throw new CharacterAlreadyMovedException();

        SpotEntity beforeChangeMoveToSpot = mSpotRepository
                .findByHeightAndWidthAndGameId_GameId(moveToSpotHeight, moveToSpotWidth, gameId).orElseThrow();

        Spot characterSpot = mSpotConverter.convertEntityToSpot(beforeChangeCharacterSpot);
        Spot moveToSpot = mSpotConverter.convertEntityToSpot(beforeChangeMoveToSpot);

        mFieldService.moveCharacter(characterSpot, moveToSpot);

        SpotEntity characterSpotEntity = mSpotConverter.convertToEntity(characterSpot);

        SpotEntity moveToSpotEntity = mSpotConverter.convertToEntity(moveToSpot);

        saveCharacter(moveToSpotEntity.characterId,beforeChangeCharacterSpot.characterId);
        saveSpot(moveToSpotEntity,beforeChangeMoveToSpot);
        saveSpot(characterSpotEntity, beforeChangeCharacterSpot);
    }

    public void endTurn(String characterName, Long gameId) throws CharacterAlreadyMovedException {

        CharacterEntity characterEntity =
                mCharacterRepository.findByNameAndGameId_GameId(characterName, gameId).orElseThrow();

        if (characterEntity.moved)
                throw new CharacterAlreadyMovedException();

        characterEntity.moved = true;

        mCharacterRepository.save(characterEntity);

    }

    public void startTurn(Long gameId) {

        List<SpotEntity> entitySpotsWithCharacters = mSpotRepository.findByCharacterIdNotNullAndGameId_GameId(gameId);

        List<Spot> spotsWithCharacters = mSpotConverter.convertEntityListToSpot(entitySpotsWithCharacters);

        mFieldService.startTurn(spotsWithCharacters);

        List<SpotEntity> afterStartTurn = mSpotConverter.convertListToEntity(spotsWithCharacters);

        for(SpotEntity se : afterStartTurn) {
            saveCharacter(
                    se.characterId, mCharacterRepository
                            .findByNameAndGameId_GameId(se.characterId.name,gameId).orElseThrow()
            );
        }

    }

    public void useConsumableItem(String characterName, int itemId, Long gameId)
            throws InvalidEquipmentException, CharacterAlreadyMovedException {

        CharacterEntity beforeChangeCharacter =
                mCharacterRepository.findByNameAndGameId_GameId(characterName, gameId).orElseThrow();

        if (beforeChangeCharacter.moved)
            throw new CharacterAlreadyMovedException();

        Character character = mCharacterConverter.convertEntityToCharacter(beforeChangeCharacter);

        mFieldService.useConsumableItem(character,itemId);

        saveCharacter(mCharacterConverter.convertToEntity(character), beforeChangeCharacter);

    }

    public void useHealingItem(String characterName, int itemId, Long gameId)
            throws InvalidEquipmentException, CharacterAlreadyMovedException {

        CharacterEntity beforeChangeCharacter =
                mCharacterRepository.findByNameAndGameId_GameId(characterName, gameId).orElseThrow();

        if (beforeChangeCharacter.moved)
            throw new CharacterAlreadyMovedException();


        Character character = mCharacterConverter.convertEntityToCharacter(beforeChangeCharacter);

        mFieldService.useHealingItem(character,itemId);

        saveCharacter(mCharacterConverter.convertToEntity(character),beforeChangeCharacter);

    }

    public void useStaff(
            int healingSpotHeight, int healingSpotWidth,
            int healedSpotHeight, int healedSpotWidth,
            int itemId, Long gameId
    )
            throws InvalidEquipmentException, InvalidSpotException, CharacterAlreadyMovedException {

        SpotEntity beforeChangeHealingSpot = mSpotRepository
                .findByHeightAndWidthAndGameId_GameId(healingSpotHeight, healingSpotWidth, gameId).orElseThrow();

        SpotEntity beforeChangeHealedSpot= mSpotRepository
                .findByHeightAndWidthAndGameId_GameId(healedSpotHeight, healedSpotWidth, gameId).orElseThrow();

        if (beforeChangeHealingSpot.characterId == null || beforeChangeHealedSpot.characterId == null)
            throw new InvalidSpotException("Can't select spot without character on it");

        if (beforeChangeHealingSpot.characterId.moved)
                throw new CharacterAlreadyMovedException();

        Spot healingCharacter = mSpotConverter.convertEntityToSpot(beforeChangeHealingSpot);
        Spot healedCharacter = mSpotConverter.convertEntityToSpot(beforeChangeHealedSpot);

        mFieldService.useStaff(healingCharacter, healedCharacter, itemId);

        saveCharacter
                (mSpotConverter.convertToEntity(healingCharacter).characterId, beforeChangeHealingSpot.characterId
                );

        saveCharacter(
                mSpotConverter.convertToEntity(healedCharacter).characterId, beforeChangeHealedSpot.characterId
        );

    }

    private void saveCharacter(CharacterEntity toSave, CharacterEntity beforeSave) {
        toSave.characterId = beforeSave.characterId;
        toSave.gameId = beforeSave.gameId;

        mCharacterRepository.save(toSave);
    }

    private void saveSpot(SpotEntity toSave, SpotEntity beforeSave) {
        toSave.spotId = beforeSave.spotId;
        toSave.gameId = beforeSave.gameId;

        mSpotRepository.save(toSave);
    }

}
