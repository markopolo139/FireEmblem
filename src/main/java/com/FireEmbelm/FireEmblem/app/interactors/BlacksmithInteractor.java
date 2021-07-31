package com.FireEmbelm.FireEmblem.app.interactors;

import com.FireEmbelm.FireEmblem.app.converters.CharacterConverter;
import com.FireEmbelm.FireEmblem.app.converters.ItemsConvoyConverter;
import com.FireEmbelm.FireEmblem.app.data.entities.CharacterEntity;
import com.FireEmbelm.FireEmblem.app.data.entities.ItemsConvoyEntity;
import com.FireEmbelm.FireEmblem.app.data.repository.CharacterRepository;
import com.FireEmbelm.FireEmblem.app.data.repository.ItemsConvoyRepository;
import com.FireEmbelm.FireEmblem.business.entitie.Character;
import com.FireEmbelm.FireEmblem.business.entitie.ItemsConvoy;
import com.FireEmbelm.FireEmblem.business.exceptions.InvalidEquipmentException;
import com.FireEmbelm.FireEmblem.business.exceptions.TooSmallAmountOfMoneyException;
import com.FireEmbelm.FireEmblem.business.service.BlacksmithService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlacksmithInteractor {

    @Autowired
    private BlacksmithService mBlacksmithService;

    @Autowired
    private CharacterRepository mCharacterRepository;

    @Autowired
    private CharacterConverter mCharacterConverter;

    @Autowired
    private ItemsConvoyRepository mItemsConvoyRepository;

    @Autowired
    private ItemsConvoyConverter mItemsConvoyConverter;

    public void upgradeWeapon(String characterName, int itemId, Long gameId)
            throws InvalidEquipmentException, TooSmallAmountOfMoneyException {

        CharacterEntity beforeSaveCharacter =
                mCharacterRepository.findByNameAndGameId_GameId(characterName,gameId).orElseThrow();

        ItemsConvoyEntity beforeSaveConvoy = mItemsConvoyRepository.findByGameId_GameId(gameId);

        Character character = mCharacterConverter.convertEntityToCharacter(beforeSaveCharacter);
        ItemsConvoy itemsConvoy = mItemsConvoyConverter.convertEntityToItemsConvoy(beforeSaveConvoy);

        mBlacksmithService.upgradeWeapon(character,itemId,itemsConvoy);

        saveCharacter(mCharacterConverter.convertToEntity(character), beforeSaveCharacter);
        saveConvoy(mItemsConvoyConverter.convertToEntity(itemsConvoy),beforeSaveConvoy);

    }

    private void saveCharacter(CharacterEntity toSave, CharacterEntity beforeSave) {

        toSave.characterId = beforeSave.characterId;
        toSave.gameId = beforeSave.gameId;

        mCharacterRepository.save(toSave);

    }

    private void saveConvoy(ItemsConvoyEntity toSave, ItemsConvoyEntity beforeSave) {

        toSave.convoyId = beforeSave.convoyId;
        toSave.gameId = beforeSave.gameId;

        mItemsConvoyRepository.save(toSave);

    }

}
