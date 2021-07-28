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
                mSpotRepository.findByHeightAndWidthAndGameId_GameId(spotHeight, spotWidth, gameId);

        Character character = mCharacterConverter.convertEntityToCharacter(beforeChangeCharacter);
        Spot spot = mSpotConverter.convertEntityToSpot(beforeChangeSpot);

        mFieldService.placeCharacter(character,spot);

        SpotEntity spotEntity = mSpotConverter.convertToEntity(spot);
        spotEntity.spotId = beforeChangeSpot.spotId;
        spotEntity.gameId = beforeChangeSpot.gameId;

        spotEntity.characterId.characterId = beforeChangeCharacter.characterId;
        spotEntity.characterId.gameId = beforeChangeCharacter.gameId;

        mCharacterRepository.save(spotEntity.characterId);
        mSpotRepository.save(spotEntity);

    }

    public void moveCharacter(
            int characterSpotHeight, int characterSpotWidth,
            int moveToSpotHeight, int moveToSpotWidth,
            Long gameId
    )
            throws InvalidSpotException, CharacterAlreadyMovedException {

        SpotEntity beforeChangeCharacterSpot = mSpotRepository
                .findByHeightAndWidthAndGameId_GameId(characterSpotHeight, characterSpotWidth, gameId);

        if(beforeChangeCharacterSpot.characterId == null)
            throw new InvalidSpotException("Can't select spot without character on it");

        if (beforeChangeCharacterSpot.characterId.moved)
                throw new CharacterAlreadyMovedException();

        SpotEntity beforeChangeMoveToSpot = mSpotRepository
                .findByHeightAndWidthAndGameId_GameId(moveToSpotHeight, moveToSpotWidth, gameId);

        Spot characterSpot = mSpotConverter.convertEntityToSpot(beforeChangeCharacterSpot);
        Spot moveToSpot = mSpotConverter.convertEntityToSpot(beforeChangeMoveToSpot);

        mFieldService.moveCharacter(characterSpot, moveToSpot);

        SpotEntity characterSpotEntity = mSpotConverter.convertToEntity(characterSpot);
        characterSpotEntity.spotId = beforeChangeCharacterSpot.spotId;
        characterSpotEntity.gameId = beforeChangeCharacterSpot.gameId;

        SpotEntity moveToSpotEntity = mSpotConverter.convertToEntity(moveToSpot);
        moveToSpotEntity.spotId = beforeChangeMoveToSpot.spotId;
        moveToSpotEntity.gameId = beforeChangeMoveToSpot.gameId;

        moveToSpotEntity.characterId.characterId = beforeChangeCharacterSpot.characterId.characterId;
        moveToSpotEntity.characterId.gameId = beforeChangeCharacterSpot.characterId.gameId;

        mSpotRepository.save(characterSpotEntity);
        mCharacterRepository.save(moveToSpotEntity.characterId);
        mSpotRepository.save(moveToSpotEntity);
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

            CharacterEntity characterInBase =
                    mCharacterRepository.findByNameAndGameId_GameId(se.characterId.name,gameId).orElseThrow();

            se.characterId.characterId = characterInBase.characterId;
            se.characterId.gameId = characterInBase.gameId;
            mCharacterRepository.save(se.characterId);

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

        CharacterEntity characterEntity = mCharacterConverter.convertToEntity(character);
        characterEntity.characterId = beforeChangeCharacter.characterId;
        characterEntity.gameId = beforeChangeCharacter.gameId;

        mCharacterRepository.save(characterEntity);

    }

    public void useHealingItem(String characterName, int itemId, Long gameId)
            throws InvalidEquipmentException, CharacterAlreadyMovedException {

        CharacterEntity beforeChangeCharacter =
                mCharacterRepository.findByNameAndGameId_GameId(characterName, gameId).orElseThrow();

        if (beforeChangeCharacter.moved)
            throw new CharacterAlreadyMovedException();


        Character character = mCharacterConverter.convertEntityToCharacter(beforeChangeCharacter);

        mFieldService.useHealingItem(character,itemId);

        CharacterEntity characterEntity = mCharacterConverter.convertToEntity(character);
        characterEntity.characterId = beforeChangeCharacter.characterId;
        characterEntity.gameId = beforeChangeCharacter.gameId;

        mCharacterRepository.save(characterEntity);

    }

    public void useStaff(
            int healingSpotHeight, int healingSpotWidth,
            int healedSpotHeight, int healedSpotWidth,
            int itemId, Long gameId
    )
            throws InvalidEquipmentException, InvalidSpotException, CharacterAlreadyMovedException {

        SpotEntity beforeChangeHealingSpot =
                mSpotRepository.findByHeightAndWidthAndGameId_GameId(healingSpotHeight, healingSpotWidth, gameId);
        SpotEntity beforeChangeHealedSpot=
                mSpotRepository.findByHeightAndWidthAndGameId_GameId(healedSpotHeight, healedSpotWidth, gameId);

        if (beforeChangeHealingSpot.characterId == null || beforeChangeHealedSpot.characterId == null)
            throw new InvalidSpotException("Can't select spot without character on it");

        if (beforeChangeHealingSpot.characterId.moved)
                throw new CharacterAlreadyMovedException();

        Spot healingCharacter = mSpotConverter.convertEntityToSpot(beforeChangeHealingSpot);
        Spot healedCharacter = mSpotConverter.convertEntityToSpot(beforeChangeHealedSpot);

        mFieldService.useStaff(healingCharacter, healedCharacter, itemId);

        SpotEntity healingSpot = mSpotConverter.convertToEntity(healingCharacter);

        healingSpot.characterId.characterId = beforeChangeHealingSpot.characterId.characterId;
        healingSpot.characterId.gameId = beforeChangeHealingSpot.gameId;

        SpotEntity healedSpot = mSpotConverter.convertToEntity(healedCharacter);


        healedSpot.characterId.characterId = beforeChangeHealedSpot.characterId.characterId;
        healedSpot.characterId.gameId = beforeChangeHealedSpot.gameId;

        mCharacterRepository.save(healingSpot.characterId);
        mCharacterRepository.save(healedSpot.characterId);

    }

}
