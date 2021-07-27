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
            String characterName, int convoyMoney, int itemsConvoyId, Long gameId
    ) throws EquipmentLimitException {


        ItemsConvoyEntity enteringConvoy = mItemsConvoyRepository.findByMoneyAndGameId_GameId(convoyMoney, gameId);

        CharacterEntity beforeChange =
                mCharacterRepository.findByNameAndGameId_GameId(characterName, gameId).orElseThrow();

        Character character = mCharacterConverter.convertEntityToCharacter(beforeChange);
        ItemsConvoy itemsConvoy = mItemsConvoyConverter.convertEntityToItemsConvoy(enteringConvoy);

        mEquipmentManagementService.getEquipmentForCharacterFromConvoy(character, itemsConvoy, itemsConvoyId);

        saveResultToBase(enteringConvoy, character, itemsConvoy, beforeChange);

    }

    public void giveEquipmentFromCharacterToConvoy(
            String characterName, int convoyMoney, int characterEquipmentId, Long gameId
    ) {
        ItemsConvoyEntity enteringConvoy = mItemsConvoyRepository.findByMoneyAndGameId_GameId(convoyMoney, gameId);

        CharacterEntity beforeChange =
                mCharacterRepository.findByNameAndGameId_GameId(characterName, gameId).orElseThrow();

        Character character = mCharacterConverter.convertEntityToCharacter(beforeChange);
        ItemsConvoy itemsConvoy = mItemsConvoyConverter.convertEntityToItemsConvoy(enteringConvoy);

        mEquipmentManagementService.giveEquipmentFromCharacterToConvoy(character, itemsConvoy, characterEquipmentId);

        saveResultToBase(enteringConvoy, character, itemsConvoy, beforeChange);
    }

    public void storeAllEquipmentFromCharacters(int convoyMoney, Long gameId) {

        ItemsConvoyEntity enteringConvoy = mItemsConvoyRepository.findByMoneyAndGameId_GameId(convoyMoney, gameId);

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

            CharacterEntity entityInBase =
                    mCharacterRepository.findByNameAndGameId_GameId(ce.name, gameId).orElseThrow();
            ce.characterId = entityInBase.characterId;
            ce.gameId = entityInBase.gameId;

        }

        mItemsConvoyRepository.save(exitingConvoy);
        mCharacterRepository.saveAll(exitingCharacters);
    }

    public void trade(String tradeFromCharacterName, String tradeToCharacterName, int equipmentId, Long gameId)
            throws EquipmentLimitException {

        CharacterEntity beforeTradeFromCharacter =
                mCharacterRepository.findByNameAndGameId_GameId(tradeFromCharacterName, gameId).orElseThrow();

        CharacterEntity beforeTradeToCharacter =
                mCharacterRepository.findByNameAndGameId_GameId(tradeToCharacterName, gameId).orElseThrow();

        Character tradeFromCharacter = mCharacterConverter.convertEntityToCharacter(beforeTradeFromCharacter);
        Character tradeToCharacter = mCharacterConverter.convertEntityToCharacter(beforeTradeToCharacter);

        mEquipmentManagementService.trade(tradeFromCharacter, tradeToCharacter, equipmentId);

        CharacterEntity tradeFromEntity = mCharacterConverter.convertToEntity(tradeFromCharacter);
        tradeFromEntity.characterId = beforeTradeFromCharacter.characterId;
        tradeFromEntity.gameId = beforeTradeFromCharacter.gameId;

        CharacterEntity tradeToEntity = mCharacterConverter.convertToEntity(tradeToCharacter);
        tradeToEntity.characterId = beforeTradeToCharacter.characterId;
        tradeToEntity.gameId = beforeTradeToCharacter.gameId;

        mCharacterRepository.save(tradeFromEntity);
        mCharacterRepository.save(tradeToEntity);
    }

    public void equipItem(String characterName, int equipmentId, Long gameId) throws InvalidEquipmentException {

        CharacterEntity beforeEquip =
                mCharacterRepository.findByNameAndGameId_GameId(characterName, gameId).orElseThrow();

        Character character = mCharacterConverter.convertEntityToCharacter(beforeEquip);

        mEquipmentManagementService.equipItem(character, equipmentId);

        CharacterEntity characterEntity = mCharacterConverter.convertToEntity(character);
        characterEntity.characterId = beforeEquip.characterId;
        characterEntity.gameId = beforeEquip.gameId;

        mCharacterRepository.save(characterEntity);
    }

    private void saveResultToBase(
            ItemsConvoyEntity enteringConvoy, Character character, ItemsConvoy itemsConvoy, CharacterEntity beforeChange
    ) {

        ItemsConvoyEntity exitingConvoy = mItemsConvoyConverter.convertToEntity(itemsConvoy);
        exitingConvoy.convoyId = enteringConvoy.convoyId;
        exitingConvoy.gameId = enteringConvoy.gameId;

        CharacterEntity exitingCharacter = mCharacterConverter.convertToEntity(character);
        exitingCharacter.characterId = beforeChange.characterId;
        exitingCharacter.gameId = beforeChange.gameId;

        mItemsConvoyRepository.save(exitingConvoy);
        mCharacterRepository.save(exitingCharacter);
    }

}
