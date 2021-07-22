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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

//TODO:
// in interactor : check status (if dead, set spot to null and save dead character, and spot with null) (if alive, save spot with character
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
            CharacterModel characterModel, int itemsConvoyMoney, int itemsConvoyId
    ) throws EquipmentLimitException {

        ItemsConvoyEntity enteringConvoy = mItemsConvoyRepository.findByMoney(itemsConvoyMoney);
        Character character = mCharacterConverter.convertModelToCharacter(characterModel);
        ItemsConvoy itemsConvoy = mItemsConvoyConverter.convertEntityToItemsConvoy(enteringConvoy);

        mEquipmentManagementService.getEquipmentForCharacterFromConvoy(character, itemsConvoy, itemsConvoyId);

        saveResultToBase(enteringConvoy.convoyId, character, itemsConvoy);

    }

    public void giveEquipmentFromCharacterToConvoy(
            CharacterModel characterModel, int itemsConvoyMoney, int characterEquipmentId
    ) {
        ItemsConvoyEntity enteringConvoy = mItemsConvoyRepository.findByMoney(itemsConvoyMoney);
        Character character = mCharacterConverter.convertModelToCharacter(characterModel);
        ItemsConvoy itemsConvoy = mItemsConvoyConverter.convertEntityToItemsConvoy(enteringConvoy);

        mEquipmentManagementService.giveEquipmentFromCharacterToConvoy(character, itemsConvoy, characterEquipmentId);

        saveResultToBase(enteringConvoy.convoyId, character, itemsConvoy);
    }

    public void storeAllEquipmentFromCharacters(int itemsConvoyMoney) {

        ItemsConvoyEntity enteringConvoy = mItemsConvoyRepository.findByMoney(itemsConvoyMoney);
        List<Character> characters = mCharacterConverter.convertEntityListToCharacter(
                mCharacterRepository.findByCharacterState(CharacterState.ALIVE)
        );
        ItemsConvoy itemsConvoy = mItemsConvoyConverter.convertEntityToItemsConvoy(enteringConvoy);

        mEquipmentManagementService.storeAllEquipmentFromCharacters(characters, itemsConvoy);

        ItemsConvoyEntity exitingConvoy = mItemsConvoyConverter.convertToEntity(itemsConvoy);
        exitingConvoy.convoyId = enteringConvoy.convoyId;

        List<CharacterEntity> exitingCharacters = mCharacterConverter.convertListToEntity(characters);

        for(CharacterEntity ce : exitingCharacters)
            ce.characterId = mCharacterRepository.findByName(ce.name).orElseThrow().characterId;

        mItemsConvoyRepository.save(exitingConvoy);
        mCharacterRepository.saveAll(exitingCharacters);
    }

    public void trade(CharacterModel tradeFrom, CharacterModel tradeTo, int equipmentId)
            throws EquipmentLimitException {

        Character tradeFromCharacter = mCharacterConverter.convertModelToCharacter(tradeFrom);
        Character tradeToCharacter = mCharacterConverter.convertModelToCharacter(tradeTo);

        mEquipmentManagementService.trade(tradeFromCharacter, tradeToCharacter, equipmentId);

        CharacterEntity tradeFromEntity = mCharacterConverter.convertToEntity(tradeFromCharacter);
        tradeFromEntity.characterId = mCharacterRepository.findByName(tradeFromEntity.name).orElseThrow().characterId;

        CharacterEntity tradeToEntity = mCharacterConverter.convertToEntity(tradeToCharacter);
        tradeToEntity.characterId = mCharacterRepository.findByName(tradeToEntity.name).orElseThrow().characterId;

        mCharacterRepository.save(tradeFromEntity);
        mCharacterRepository.save(tradeToEntity);
    }

    public void equipItem(CharacterModel characterModel, int equipmentId) throws InvalidEquipmentException {

        Character character = mCharacterConverter.convertModelToCharacter(characterModel);

        mEquipmentManagementService.equipItem(character, equipmentId);

        CharacterEntity characterEntity = mCharacterConverter.convertToEntity(character);
        characterEntity.characterId = mCharacterRepository.findByName(characterEntity.name).orElseThrow().characterId;

        mCharacterRepository.save(characterEntity);
    }

    private void saveResultToBase(long enteringConvoyId, Character character, ItemsConvoy itemsConvoy) {
        ItemsConvoyEntity exitingConvoy = mItemsConvoyConverter.convertToEntity(itemsConvoy);
        exitingConvoy.convoyId = enteringConvoyId;

        CharacterEntity exitingCharacter = mCharacterConverter.convertToEntity(character);
        exitingCharacter.characterId = mCharacterRepository.findByName(character.getName()).orElseThrow().characterId;

        mItemsConvoyRepository.save(exitingConvoy);
        mCharacterRepository.save(exitingCharacter);
    }

}
