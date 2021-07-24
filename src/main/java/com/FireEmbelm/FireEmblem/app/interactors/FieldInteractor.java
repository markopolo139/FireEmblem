package com.FireEmbelm.FireEmblem.app.interactors;

import com.FireEmbelm.FireEmblem.app.converters.CharacterConverter;
import com.FireEmbelm.FireEmblem.app.converters.SpotConverter;
import com.FireEmbelm.FireEmblem.app.data.entities.CharacterEntity;
import com.FireEmbelm.FireEmblem.app.data.entities.SpotEntity;
import com.FireEmbelm.FireEmblem.app.data.repository.CharacterRepository;
import com.FireEmbelm.FireEmblem.app.data.repository.SpotRepository;
import com.FireEmbelm.FireEmblem.business.entitie.Character;
import com.FireEmbelm.FireEmblem.business.exceptions.InvalidSpotException;
import com.FireEmbelm.FireEmblem.business.service.FieldService;
import com.FireEmbelm.FireEmblem.business.value.field.Spot;
import com.FireEmbelm.FireEmblem.web.models.request.CharacterModel;
import com.FireEmbelm.FireEmblem.web.models.request.SpotModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

//TODO:
// - searching for character by (name and game id [no model]), itemsConvoy by (money, game id [not model])
// - searching for spot by (int x, int y, game id [no model])
// - create game entity (in base have only id [give this id to spot, character, convoy and enemies]
// - In field check if character.move == true
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

    public void placeCharacter(CharacterModel characterModel, SpotModel spotModel)
            throws InvalidSpotException {

        Character character = mCharacterConverter.convertModelToCharacter(characterModel);
        Spot spot = mSpotConverter.convertModelToSpot(spotModel);

        mFieldService.placeCharacter(character,spot);


        SpotEntity spotEntity = mSpotConverter.convertToEntity(spot);
        spotEntity.spotId = mSpotRepository.findByHeightAndWidth(spotEntity.height,spotEntity.width).spotId;

        spotEntity.characterId.characterId =
                mCharacterRepository.findByName(spotEntity.characterId.name).orElseThrow().characterId;

        mCharacterRepository.save(spotEntity.characterId);
        mSpotRepository.save(spotEntity);

    }

    public void moveCharacter(SpotModel characterSpotModel, SpotModel moveToSpotModel)
            throws InvalidSpotException {

        Spot characterSpot = mSpotConverter.convertModelToSpot(characterSpotModel);
        Spot moveToSpot = mSpotConverter.convertModelToSpot(moveToSpotModel);

        mFieldService.moveCharacter(characterSpot, moveToSpot);

        SpotEntity characterSpotEntity = mSpotConverter.convertToEntity(characterSpot);
        characterSpotEntity.spotId =
                mSpotRepository.findByHeightAndWidth(characterSpotEntity.height,characterSpotEntity.width).spotId;

        SpotEntity moveToSpotEntity = mSpotConverter.convertToEntity(moveToSpot);
        moveToSpotEntity.spotId =
                mSpotRepository.findByHeightAndWidth(moveToSpotEntity.height,moveToSpotEntity.width).spotId;

        moveToSpotEntity.characterId.characterId =
                mCharacterRepository.findByName(moveToSpotEntity.characterId.name).orElseThrow().characterId;

        mSpotRepository.save(characterSpotEntity);
        mCharacterRepository.save(moveToSpotEntity.characterId);
        mSpotRepository.save(moveToSpotEntity);
    }

    public void endTurn(CharacterModel characterModel) {

        CharacterEntity characterEntity = mCharacterRepository.findByName(characterModel.name).orElseThrow();
        characterEntity.moved = true;

        mCharacterRepository.save(characterEntity);

    }

    public void startTurn() {

        List<SpotEntity> entitySpotsWithCharacters = mSpotRepository.findByCharacterIdNotNull();

        List<Spot> spotsWithCharacters = mSpotConverter.convertEntityListToSpot(entitySpotsWithCharacters);

        mFieldService.startTurn(spotsWithCharacters);

        List<SpotEntity> afterStartTurn = mSpotConverter.convertListToEntity(spotsWithCharacters);

        for(SpotEntity se : afterStartTurn) {
            se.characterId.characterId = mCharacterRepository.findByName(se.characterId.name).orElseThrow().characterId;
            mCharacterRepository.save(se.characterId);
        }

    }

}
