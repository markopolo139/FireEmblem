package com.FireEmbelm.FireEmblem.business.service;

import com.FireEmbelm.FireEmblem.business.entitie.Character;
import com.FireEmbelm.FireEmblem.business.entitie.ItemsConvoy;
import com.FireEmbelm.FireEmblem.business.exceptions.EquipmentLimitException;
import com.FireEmbelm.FireEmblem.business.exceptions.InvalidEquipmentException;
import com.FireEmbelm.FireEmblem.business.value.categories.ConsumableItemCategory;
import com.FireEmbelm.FireEmblem.business.value.categories.WeaponCategory;
import com.FireEmbelm.FireEmblem.business.value.equipment.Equipment;
import com.FireEmbelm.FireEmblem.business.value.equipment.Weapon;

import java.util.Collection;

public class EquipmentManagementService {

    public static final int EQUIPMENT_LIMIT = 6;

    public void getEquipmentForCharacterFromConvoy(Character character, ItemsConvoy itemsConvoy, int itemsConvoyId)
            throws EquipmentLimitException {

        if(itemsConvoyId < 0 || itemsConvoyId >= itemsConvoy.getPlayerItems().size())
            throw new IndexOutOfBoundsException("Selected item id must be in range of items convoy");

        if(character.getEquipment().size() >= EQUIPMENT_LIMIT)
            throw new EquipmentLimitException();

        character.getEquipment().add(itemsConvoy.getPlayerItems().get(itemsConvoyId));
        itemsConvoy.getPlayerItems().remove(itemsConvoyId);
    }

    public void giveEquipmentFromCharacterToConvoy(Character character, ItemsConvoy itemsConvoy, int characterEquipmentId) {

        if(characterEquipmentId < 0 || characterEquipmentId >= EQUIPMENT_LIMIT )
            throw new IndexOutOfBoundsException("Selected item id must be in range of character equipment");

        itemsConvoy.getPlayerItems().add(character.getEquipment().get(characterEquipmentId));

        if(character.getCurrentEquippedItem() != null)
            if(character.getCurrentEquippedItem().equals(character.getEquipment().get(characterEquipmentId)))
                character.setCurrentEquippedItem(null);

        character.getEquipment().remove(characterEquipmentId);
    }

    public void storeAllEquipmentFromCharacters(Collection<Character> characters, ItemsConvoy itemsConvoy) {

        for(Character character : characters) {
            itemsConvoy.getPlayerItems().addAll(character.getEquipment());
            character.getEquipment().clear();
            character.setCurrentEquippedItem(null);
        }

    }

    public void trade(Character tradeFrom, Character tradeTo, int equipmentId) throws EquipmentLimitException {

        if(equipmentId < 0 || equipmentId >= tradeFrom.getEquipment().size())
            throw new IndexOutOfBoundsException("Selected item id must be in range of items convoy");

        if(tradeTo.getEquipment().size() >= 6)
            throw new EquipmentLimitException();

        if(tradeFrom.getEquipment().get(equipmentId).equals(tradeFrom.getCurrentEquippedItem()))
            tradeFrom.setCurrentEquippedItem(null);

        tradeTo.getEquipment().add(tradeFrom.getEquipment().get(equipmentId));
        tradeFrom.getEquipment().remove(equipmentId);

    }

    public void equipItem(Character character, int equipmentId) throws InvalidEquipmentException {

        Equipment selectedItem = character.getEquipment().get(equipmentId);

        if(selectedItem == null)
            throw new InvalidEquipmentException();

        if(selectedItem.getItemCategory() instanceof ConsumableItemCategory)
            throw new InvalidEquipmentException();

        if( (!character.getCharacterClass().getAllowedWeapons().contains(selectedItem.getItemCategory())
                && selectedItem.getItemCategory() instanceof WeaponCategory))
            throw new InvalidEquipmentException();

        if(selectedItem instanceof Weapon) {
            if(((Weapon) selectedItem).getRank() > character.getWeaponProgresses().get(selectedItem.getItemCategory()).getRank())
                throw new InvalidEquipmentException();
        }

        character.setCurrentEquippedItem(selectedItem);
    }
}
