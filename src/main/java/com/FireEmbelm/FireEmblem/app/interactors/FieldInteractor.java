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
import com.FireEmbelm.FireEmblem.web.models.request.CharacterModel;
import com.FireEmbelm.FireEmblem.web.models.request.SpotModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//TODO:
// - use string name instead of model and in spot (use height and width)

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

    public void placeCharacter(CharacterModel characterModel, SpotModel spotModel, Long gameId)
            throws InvalidSpotException {

        CharacterEntity beforeChangeCharacter =
                mCharacterRepository.findByNameAndGameId_GameId(characterModel.name, gameId).orElseThrow();

        SpotEntity beforeChangeSpot =
                mSpotRepository.findByHeightAndWidthAndGameId_GameId(spotModel.height,spotModel.width, gameId);

        Character character = mCharacterConverter.convertModelToCharacter(characterModel);
        Spot spot = mSpotConverter.convertModelToSpot(spotModel);

        mFieldService.placeCharacter(character,spot);


        SpotEntity spotEntity = mSpotConverter.convertToEntity(spot);
        spotEntity.spotId = beforeChangeSpot.spotId;
        spotEntity.gameId = beforeChangeSpot.gameId;

        spotEntity.characterId.characterId = beforeChangeCharacter.characterId;
        spotEntity.characterId.gameId = beforeChangeCharacter.gameId;

        mCharacterRepository.save(spotEntity.characterId);
        mSpotRepository.save(spotEntity);

    }

    public void moveCharacter(SpotModel characterSpotModel, SpotModel moveToSpotModel, Long gameId)
            throws InvalidSpotException, CharacterAlreadyMovedException {

        if(characterSpotModel.characterOnSpot != null)
            if (characterSpotModel.characterOnSpot.moved)
                throw new CharacterAlreadyMovedException();

        SpotEntity beforeChangeCharacterSpot = mSpotRepository
                .findByHeightAndWidthAndGameId_GameId(characterSpotModel.height,characterSpotModel.width,gameId);

        SpotEntity beforeChangeMoveToSpot = mSpotRepository
                .findByHeightAndWidthAndGameId_GameId(moveToSpotModel.height,moveToSpotModel.width, gameId);

        Spot characterSpot = mSpotConverter.convertModelToSpot(characterSpotModel);
        Spot moveToSpot = mSpotConverter.convertModelToSpot(moveToSpotModel);

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

    public void endTurn(CharacterModel characterModel, Long gameId) throws CharacterAlreadyMovedException {

        if (characterModel.moved)
                throw new CharacterAlreadyMovedException();

        CharacterEntity characterEntity =
                mCharacterRepository.findByNameAndGameId_GameId(characterModel.name, gameId).orElseThrow();
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

    public void useConsumableItem(CharacterModel characterModel, int itemId, Long gameId)
            throws InvalidEquipmentException, CharacterAlreadyMovedException {

        if (characterModel.moved)
            throw new CharacterAlreadyMovedException();

        CharacterEntity beforeChangeCharacter =
                mCharacterRepository.findByNameAndGameId_GameId(characterModel.name, gameId).orElseThrow();

        Character character = mCharacterConverter.convertModelToCharacter(characterModel);

        mFieldService.useConsumableItem(character,itemId);

        CharacterEntity characterEntity = mCharacterConverter.convertToEntity(character);
        characterEntity.characterId = beforeChangeCharacter.characterId;
        characterEntity.gameId = beforeChangeCharacter.gameId;

        mCharacterRepository.save(characterEntity);

    }

    public void useHealingItem(CharacterModel characterModel, int itemId, Long gameId)
            throws InvalidEquipmentException, CharacterAlreadyMovedException {

        if (characterModel.moved)
            throw new CharacterAlreadyMovedException();

        CharacterEntity beforeChangeCharacter =
                mCharacterRepository.findByNameAndGameId_GameId(characterModel.name, gameId).orElseThrow();

        Character character = mCharacterConverter.convertModelToCharacter(characterModel);

        mFieldService.useHealingItem(character,itemId);

        CharacterEntity characterEntity = mCharacterConverter.convertToEntity(character);
        characterEntity.characterId = beforeChangeCharacter.characterId;
        characterEntity.gameId = beforeChangeCharacter.gameId;

        mCharacterRepository.save(characterEntity);

    }

    public void useStaff(SpotModel healingCharacterModel, SpotModel healedCharacterModel, int itemId, Long gameId)
            throws InvalidEquipmentException, InvalidSpotException, CharacterAlreadyMovedException {

        if(healingCharacterModel.characterOnSpot != null )
            if (healingCharacterModel.characterOnSpot.moved)
                throw new CharacterAlreadyMovedException();

        Spot healingCharacter = mSpotConverter.convertModelToSpot(healingCharacterModel);
        Spot healedCharacter = mSpotConverter.convertModelToSpot(healedCharacterModel);

        mFieldService.useStaff(healingCharacter, healedCharacter, itemId);

        SpotEntity healingSpot = mSpotConverter.convertToEntity(healingCharacter);

        CharacterEntity beforeChangeHealingCharacter =
                mCharacterRepository.findByNameAndGameId_GameId(healingSpot.characterId.name, gameId).orElseThrow();

        healingSpot.characterId.characterId = beforeChangeHealingCharacter.characterId;
        healingSpot.characterId.gameId = beforeChangeHealingCharacter.gameId;

        SpotEntity healedSpot = mSpotConverter.convertToEntity(healedCharacter);

        CharacterEntity beforeChangeHealedCharacter =
                mCharacterRepository.findByNameAndGameId_GameId(healedSpot.characterId.name, gameId).orElseThrow();

        healedSpot.characterId.characterId = beforeChangeHealedCharacter.characterId;
        healedSpot.characterId.gameId = beforeChangeHealedCharacter.gameId;

        mCharacterRepository.save(healingSpot.characterId);
        mCharacterRepository.save(healedSpot.characterId);

    }

}
