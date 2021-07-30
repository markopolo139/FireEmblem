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
import com.FireEmbelm.FireEmblem.business.value.equipment.Equipment;
import com.FireEmbelm.FireEmblem.web.models.request.CharacterModel;
import com.FireEmbelm.FireEmblem.web.models.request.ItemsConvoyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
            String characterName, int itemsConvoyId, Long gameId
    ) throws EquipmentLimitException {


        ItemsConvoyEntity enteringConvoy = mItemsConvoyRepository.findByGameId_GameId(gameId);

        CharacterEntity beforeChange =
                mCharacterRepository.findByNameAndGameId_GameId(characterName, gameId).orElseThrow();

        Character character = mCharacterConverter.convertEntityToCharacter(beforeChange);
        ItemsConvoy itemsConvoy = mItemsConvoyConverter.convertEntityToItemsConvoy(enteringConvoy);

        mEquipmentManagementService.getEquipmentForCharacterFromConvoy(character, itemsConvoy, itemsConvoyId);

        saveResultToBase(enteringConvoy, character, itemsConvoy, beforeChange);

    }

    public void giveEquipmentFromCharacterToConvoy(
            String characterName, int characterEquipmentId, Long gameId
    ) {
        ItemsConvoyEntity enteringConvoy = mItemsConvoyRepository.findByGameId_GameId(gameId);

        CharacterEntity beforeChange =
                mCharacterRepository.findByNameAndGameId_GameId(characterName, gameId).orElseThrow();

        Character character = mCharacterConverter.convertEntityToCharacter(beforeChange);
        ItemsConvoy itemsConvoy = mItemsConvoyConverter.convertEntityToItemsConvoy(enteringConvoy);

        mEquipmentManagementService.giveEquipmentFromCharacterToConvoy(character, itemsConvoy, characterEquipmentId);

        saveResultToBase(enteringConvoy, character, itemsConvoy, beforeChange);
    }

    public void storeAllEquipmentFromCharacters(Long gameId) {

        ItemsConvoyEntity beforeSave = mItemsConvoyRepository.findByGameId_GameId(gameId);

        List<Character> characters = mCharacterConverter.convertEntityListToCharacter(
                mCharacterRepository.findByCharacterStateAndGameId_GameId(CharacterState.ALIVE, gameId)
        );
        ItemsConvoy itemsConvoy = mItemsConvoyConverter.convertEntityToItemsConvoy(beforeSave);

        mEquipmentManagementService.storeAllEquipmentFromCharacters(characters, itemsConvoy);

        saveCharactersAndConvoy(
                mCharacterConverter.convertListToEntity(characters),
                mItemsConvoyConverter.convertToEntity(itemsConvoy),
                beforeSave, gameId
                );
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

        saveCharacter(mCharacterConverter.convertToEntity(tradeFromCharacter), beforeTradeFromCharacter);
        saveCharacter(mCharacterConverter.convertToEntity(tradeToCharacter), beforeTradeToCharacter);
    }

    public void equipItem(String characterName, int equipmentId, Long gameId) throws InvalidEquipmentException {

        CharacterEntity beforeEquip =
                mCharacterRepository.findByNameAndGameId_GameId(characterName, gameId).orElseThrow();

        Character character = mCharacterConverter.convertEntityToCharacter(beforeEquip);

        mEquipmentManagementService.equipItem(character, equipmentId);

        saveCharacter(mCharacterConverter.convertToEntity(character), beforeEquip);

    }

    public List<Equipment> getItemsConvoyEquipment(Long gameId) {

        ItemsConvoyModel itemsConvoyModel =
                mItemsConvoyConverter.convertEntityToModel(mItemsConvoyRepository.findByGameId_GameId(gameId));

        return Stream.of(
                itemsConvoyModel.weapons,
                itemsConvoyModel.healingItems,
                itemsConvoyModel.seals,
                itemsConvoyModel.statsUpItems
        ).flatMap(List::stream).collect(Collectors.toList());

    }

    public int getConvoyMoney(Long gameId) {
        return mItemsConvoyRepository.findByGameId_GameId(gameId).money;
    }

    public List<Equipment> getCharacterEquipment(String characterName, Long gameId) {

        CharacterModel characterModel = mCharacterConverter.convertEntityToModel(
                mCharacterRepository.findByNameAndGameId_GameId(characterName, gameId).orElseThrow()
        );

        return Stream.of(
                characterModel.weapons,
                characterModel.healingItems,
                characterModel.seals,
                characterModel.statsUpItems
        ).flatMap(List::stream).collect(Collectors.toList());
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

    private void saveCharacter(CharacterEntity toSave, CharacterEntity beforeChange) {
        toSave.characterId = beforeChange.characterId;
        toSave.gameId = beforeChange.gameId;

        mCharacterRepository.save(toSave);
    }

    private void saveCharactersAndConvoy(
            List<CharacterEntity> toSaveCharacters, ItemsConvoyEntity toSaveConvoy,
            ItemsConvoyEntity beforeSaveConvoy, Long gameId
    ) {

        toSaveConvoy.convoyId = beforeSaveConvoy.convoyId;
        toSaveConvoy.gameId = beforeSaveConvoy.gameId;

        for(CharacterEntity ce : toSaveCharacters) {

            CharacterEntity entityInBase =
                    mCharacterRepository.findByNameAndGameId_GameId(ce.name, gameId).orElseThrow();
            ce.characterId = entityInBase.characterId;
            ce.gameId = entityInBase.gameId;

        }

        mItemsConvoyRepository.save(toSaveConvoy);
        mCharacterRepository.saveAll(toSaveCharacters);

    }
}
