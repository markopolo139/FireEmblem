package com.FireEmbelm.FireEmblem.business.service;

import com.FireEmbelm.FireEmblem.business.entitie.ItemsConvoy;
import com.FireEmbelm.FireEmblem.business.exceptions.InvalidEquipmentException;
import com.FireEmbelm.FireEmblem.business.exceptions.TooSmallAmountOfMoneyException;
import com.FireEmbelm.FireEmblem.business.value.categories.WeaponCategory;
import com.FireEmbelm.FireEmblem.business.value.equipment.Weapon;

public class BlacksmithService {

    public static final int UPGRADE_COST = 1500;

    public Weapon upgradeWeapon(Weapon weapon, ItemsConvoy itemsConvoy) throws InvalidEquipmentException, TooSmallAmountOfMoneyException {

        if (weapon.getName().endsWith("+"))
            throw new InvalidEquipmentException("This weapon is already upgraded");

        if(itemsConvoy.getMoney() < UPGRADE_COST)
            throw new TooSmallAmountOfMoneyException();

        itemsConvoy.setMoney(itemsConvoy.getMoney() - UPGRADE_COST);

        return new Weapon(
                weapon.getName() + "+",
                weapon.getRank(),
                weapon.getMight() + 3,
                weapon.getHit() + 10,
                weapon.getAvo() + 5,
                weapon.getCrit() + 3,
                40,
                weapon.getRange(),
                weapon.getWorth() + 500,
                (WeaponCategory) weapon.getItemCategory()
        );

    }

}
