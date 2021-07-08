package com.FireEmbelm.FireEmblem.business.service;

import com.FireEmbelm.FireEmblem.business.entitie.Character;
import com.FireEmbelm.FireEmblem.business.entitie.ItemsConvoy;
import com.FireEmbelm.FireEmblem.business.exceptions.EquipmentLimitException;
import com.FireEmbelm.FireEmblem.business.exceptions.InvalidEquipmentException;
import com.FireEmbelm.FireEmblem.business.value.categories.ConsumableItemCategory;
import com.FireEmbelm.FireEmblem.business.value.categories.WeaponCategory;
import com.FireEmbelm.FireEmblem.business.value.equipment.Weapon;

import java.util.Collection;

public class EquipmentManagementService {

    public static final int EQUIPMENT_LIMIT = 6;

    public void getEquipmentForCharacterFromConvoy(Character character, ItemsConvoy itemsConvoy, int itemsConvoyId)
            throws EquipmentLimitException {

        if(itemsConvoyId < 0 || itemsConvoyId >= itemsConvoy.getEquipmentCollection().size())
            throw new IndexOutOfBoundsException("Selected item id must be in range of items convoy");

        if(character.getEquipment().size() >= EQUIPMENT_LIMIT)
            throw new EquipmentLimitException();

        character.getEquipment().add(itemsConvoy.getEquipmentCollection().get(itemsConvoyId));
        itemsConvoy.getEquipmentCollection().remove(itemsConvoyId);
    }

    public void giveEquipmentFromCharacterToConvoy(Character character, ItemsConvoy itemsConvoy, int characterEquipmentId) {

        if(characterEquipmentId < 0 || characterEquipmentId >= EQUIPMENT_LIMIT )
            throw new IndexOutOfBoundsException("Selected item id must be in range of character equipment");

        itemsConvoy.getEquipmentCollection().add(character.getEquipment().get(characterEquipmentId));

        if(character.getCurrentEquipedItem().equals(character.getEquipment().get(characterEquipmentId)))
            character.setCurrentEquipedItem(null);

        character.getEquipment().remove(characterEquipmentId);
    }

    public void storeAllEquipmentFromCharacters(Collection<Character> characters, ItemsConvoy itemsConvoy) {

        for(Character character : characters) {
            itemsConvoy.getEquipmentCollection().addAll(character.getEquipment());
            character.getEquipment().clear();
            character.setCurrentEquipedItem(null);
        }

    }

    public void trade(Character tradeFrom, Character tradeTo, int equipmentId) throws EquipmentLimitException {

        if(tradeTo.getEquipment().size() >= 6)
            throw new EquipmentLimitException();

        if(tradeFrom.getEquipment().get(equipmentId).equals(tradeFrom.getCurrentEquipedItem()))
            tradeFrom.setCurrentEquipedItem(null);

        tradeTo.getEquipment().add(tradeFrom.getEquipment().get(equipmentId));
        tradeFrom.getEquipment().remove(equipmentId);

    }

    public void equipItem(Character character, int equipmentId) throws InvalidEquipmentException {
        if(character.getEquipment().get(equipmentId) == null)
            throw new InvalidEquipmentException();

        if( (!character.getCharacterClass().getAllowedWeapons().contains(character.getEquipment().get(equipmentId).getItemCategory())
                && character.getEquipment().get(equipmentId).getItemCategory() instanceof WeaponCategory)
                || character.getEquipment().get(equipmentId).getItemCategory() instanceof ConsumableItemCategory)
            throw new InvalidEquipmentException();

        character.setCurrentEquipedItem(character.getEquipment().get(equipmentId));
    }
}
