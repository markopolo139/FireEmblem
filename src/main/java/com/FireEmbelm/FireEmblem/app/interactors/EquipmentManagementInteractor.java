package com.FireEmbelm.FireEmblem.app.interactors;

import com.FireEmbelm.FireEmblem.app.converters.CharacterConverter;
import com.FireEmbelm.FireEmblem.app.converters.ItemsConvoyConverter;
import com.FireEmbelm.FireEmblem.app.data.entities.CharacterEntity;
import com.FireEmbelm.FireEmblem.app.data.entities.ItemsConvoyEntity;
import com.FireEmbelm.FireEmblem.app.data.repository.CharacterRepository;
import com.FireEmbelm.FireEmblem.app.data.repository.ItemsConvoyRepository;
import com.FireEmbelm.FireEmblem.business.entitie.Character;
import com.FireEmbelm.FireEmblem.business.entitie.ItemsConvoy;
import com.FireEmbelm.FireEmblem.business.exceptions.EquipmentLimitException;
import com.FireEmbelm.FireEmblem.business.exceptions.InvalidEquipmentException;
import com.FireEmbelm.FireEmblem.business.service.EquipmentManagementService;
import com.FireEmbelm.FireEmblem.business.value.character.related.CharacterState;
import com.FireEmbelm.FireEmblem.web.models.request.CharacterModel;
import com.FireEmbelm.FireEmblem.web.models.request.ItemsConvoyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentManagementInteractor {

    @Autowired
    private EquipmentManagementService mEquipmentManagementService;

    @Autowired
    private CharacterConverter mCharacterConverter;

    @Autowired
    private ItemsConvoyConverter mItemsConvoyConverter;

    @Autowired
    private CharacterRepository mCharacterRepository;

    @Autowired
    private ItemsConvoyRepository mItemsConvoyRepository;

    public void getEquipmentForCharacterFromConvoy(
            CharacterModel characterModel, ItemsConvoyModel convoyModel, int itemsConvoyId, Long gameId
    ) throws EquipmentLimitException {

        ItemsConvoyEntity enteringConvoy = mItemsConvoyRepository.findByMoneyAndGameId_GameId(convoyModel.money, gameId);
        Character character = mCharacterConverter.convertModelToCharacter(characterModel);
        ItemsConvoy itemsConvoy = mItemsConvoyConverter.convertEntityToItemsConvoy(enteringConvoy);

        mEquipmentManagementService.getEquipmentForCharacterFromConvoy(character, itemsConvoy, itemsConvoyId);

        saveResultToBase(enteringConvoy, character, itemsConvoy, gameId);

    }

    public void giveEquipmentFromCharacterToConvoy(
            CharacterModel characterModel, ItemsConvoyModel convoyModel, int characterEquipmentId, Long gameId
    ) {
        ItemsConvoyEntity enteringConvoy = mItemsConvoyRepository.findByMoneyAndGameId_GameId(convoyModel.money, gameId);
        Character character = mCharacterConverter.convertModelToCharacter(characterModel);
        ItemsConvoy itemsConvoy = mItemsConvoyConverter.convertEntityToItemsConvoy(enteringConvoy);

        mEquipmentManagementService.giveEquipmentFromCharacterToConvoy(character, itemsConvoy, characterEquipmentId);

        saveResultToBase(enteringConvoy, character, itemsConvoy, gameId);
    }

    public void storeAllEquipmentFromCharacters(ItemsConvoyModel convoyModel, Long gameId) {

        ItemsConvoyEntity enteringConvoy = mItemsConvoyRepository.findByMoneyAndGameId_GameId(convoyModel.money, gameId);
        List<Character> characters = mCharacterConverter.convertEntityListToCharacter(
                mCharacterRepository.findByCharacterStateAndGameId_GameId(CharacterState.ALIVE, gameId)
        );
        ItemsConvoy itemsConvoy = mItemsConvoyConverter.convertEntityToItemsConvoy(enteringConvoy);

        mEquipmentManagementService.storeAllEquipmentFromCharacters(characters, itemsConvoy);

        ItemsConvoyEntity exitingConvoy = mItemsConvoyConverter.convertToEntity(itemsConvoy);
        exitingConvoy.convoyId = enteringConvoy.convoyId;
        exitingConvoy.gameId = enteringConvoy.gameId;

        List<CharacterEntity> exitingCharacters = mCharacterConverter.convertListToEntity(characters);

        for(CharacterEntity ce : exitingCharacters) {
            CharacterEntity entityInBase = mCharacterRepository.findByNameAndGameId_GameId(ce.name, gameId).orElseThrow();
            ce.characterId = entityInBase.characterId;
            ce.gameId = entityInBase.gameId;
        }

        mItemsConvoyRepository.save(exitingConvoy);
        mCharacterRepository.saveAll(exitingCharacters);
    }

    public void trade(CharacterModel tradeFrom, CharacterModel tradeTo, int equipmentId, Long gameId)
            throws EquipmentLimitException {

        Character tradeFromCharacter = mCharacterConverter.convertModelToCharacter(tradeFrom);
        Character tradeToCharacter = mCharacterConverter.convertModelToCharacter(tradeTo);

        mEquipmentManagementService.trade(tradeFromCharacter, tradeToCharacter, equipmentId);

        CharacterEntity tradeFromEntity = mCharacterConverter.convertToEntity(tradeFromCharacter);
        CharacterEntity beforeTradeCharacter =
                mCharacterRepository.findByNameAndGameId_GameId(tradeFromEntity.name, gameId).orElseThrow();

        tradeFromEntity.characterId = beforeTradeCharacter.characterId;
        tradeFromEntity.gameId = beforeTradeCharacter.gameId;

        CharacterEntity tradeToEntity = mCharacterConverter.convertToEntity(tradeToCharacter);
        beforeTradeCharacter =
                mCharacterRepository.findByNameAndGameId_GameId(tradeToEntity.name, gameId).orElseThrow();

        tradeToEntity.characterId = beforeTradeCharacter.characterId;
        tradeToEntity.gameId = beforeTradeCharacter.gameId;

        mCharacterRepository.save(tradeFromEntity);
        mCharacterRepository.save(tradeToEntity);
    }

    public void equipItem(CharacterModel characterModel, int equipmentId, Long gameId) throws InvalidEquipmentException {

        Character character = mCharacterConverter.convertModelToCharacter(characterModel);

        mEquipmentManagementService.equipItem(character, equipmentId);

        CharacterEntity characterEntity = mCharacterConverter.convertToEntity(character);
        CharacterEntity beforeEquip =
                mCharacterRepository.findByNameAndGameId_GameId(characterEntity.name, gameId).orElseThrow();

        characterEntity.characterId = beforeEquip.characterId;
        characterEntity.gameId = beforeEquip.gameId;

        mCharacterRepository.save(characterEntity);
    }

    private void saveResultToBase(
            ItemsConvoyEntity enteringConvoy, Character character, ItemsConvoy itemsConvoy, Long gameId
    ) {
        ItemsConvoyEntity exitingConvoy = mItemsConvoyConverter.convertToEntity(itemsConvoy);
        exitingConvoy.convoyId = enteringConvoy.convoyId;
        exitingConvoy.gameId = enteringConvoy.gameId;

        CharacterEntity exitingCharacter = mCharacterConverter.convertToEntity(character);
        CharacterEntity beforeChange =
                mCharacterRepository.findByNameAndGameId_GameId(character.getName(), gameId).orElseThrow();

        exitingCharacter.characterId = beforeChange.characterId;
        exitingCharacter.gameId = beforeChange.gameId;

        mItemsConvoyRepository.save(exitingConvoy);
        mCharacterRepository.save(exitingCharacter);
    }

}
