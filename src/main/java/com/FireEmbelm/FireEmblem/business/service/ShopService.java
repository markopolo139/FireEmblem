package com.FireEmbelm.FireEmblem.business.service;

import com.FireEmbelm.FireEmblem.business.entitie.ItemsConvoy;
import com.FireEmbelm.FireEmblem.business.exceptions.TooSmallAmountOfMoneyException;
import com.FireEmbelm.FireEmblem.business.value.equipment.Equipment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

//TODO :
// in front-end write id
// interactor gives random items (items in base will have levels [1 - 5lv of highest level player character, 2 -10lv]
public class ShopService {

    private static final double SELL_PERCENTAGE = 0.8;

    public void buyItem(int randomItemId, ArrayList<Equipment> randomEquipment, ItemsConvoy itemsConvoy)
            throws TooSmallAmountOfMoneyException {

        if(randomItemId < 0 || randomItemId > randomEquipment.size())
            throw new IndexOutOfBoundsException("Selected ID is out of bounds");

        Equipment selectedItem = randomEquipment.get(randomItemId);

        if(itemsConvoy.getMoney() < selectedItem.getWorth())
            throw new TooSmallAmountOfMoneyException();

        randomEquipment.remove(randomItemId);
        itemsConvoy.getEquipmentCollection().add(selectedItem);
        itemsConvoy.setMoney(itemsConvoy.getMoney() - selectedItem.getWorth());
    }

    public void sellItem(int itemsConvoyId, ItemsConvoy itemsConvoy) {
        if(itemsConvoyId < 0 || itemsConvoyId > itemsConvoy.getEquipmentCollection().size())
            throw new IndexOutOfBoundsException("Selected ID is out of bounds");

        Equipment selectedItem =  itemsConvoy.getEquipmentCollection().get(itemsConvoyId);
        itemsConvoy.getEquipmentCollection().remove(selectedItem);
        itemsConvoy.setMoney(itemsConvoy.getMoney() + (int) (selectedItem.getWorth() * SELL_PERCENTAGE));

    }

    public String checkItem(Equipment equipment) {
        return equipment.getDescription();
    }
}
