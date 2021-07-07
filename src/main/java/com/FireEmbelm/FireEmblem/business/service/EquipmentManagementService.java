package com.FireEmbelm.FireEmblem.business.service;

import com.FireEmbelm.FireEmblem.business.entitie.Character;
import com.FireEmbelm.FireEmblem.business.entitie.ItemsConvoy;
import com.FireEmbelm.FireEmblem.business.exceptions.EquipmentLimitException;

import java.util.Collection;

//TODO : test
public class EquipmentManagementService {

    public static final int EQUIPMENT_LIMIT = 6;

    public void getEquipmentForCharacterFromConvoy(Character character, ItemsConvoy itemsConvoy, int itemsConvoyId) throws EquipmentLimitException {
        if(character.getEquipment().size() > EQUIPMENT_LIMIT)
            throw new EquipmentLimitException();
        character.getEquipment().add(itemsConvoy.getEquipmentCollection().get(itemsConvoyId));
        itemsConvoy.getEquipmentCollection().remove(itemsConvoyId);
    }

    public void giveEquipmentFromCharacterToConvoy(Character character, ItemsConvoy itemsConvoy, int characterEquipmentId) {
        itemsConvoy.getEquipmentCollection().add(character.getEquipment().get(characterEquipmentId));
        character.getEquipment().remove(characterEquipmentId);
    }

    public void storeAllEquipmentFromCharacters(Collection<Character> characters, ItemsConvoy itemsConvoy) {
        for(Character character : characters) {
            itemsConvoy.getEquipmentCollection().addAll(character.getEquipment());
            character.getEquipment().clear();
        }
    }
}
