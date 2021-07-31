package com.FireEmbelm.FireEmblem.business.service;

import com.FireEmbelm.FireEmblem.business.entitie.ItemsConvoy;
import com.FireEmbelm.FireEmblem.business.exceptions.InvalidEquipmentException;
import com.FireEmbelm.FireEmblem.business.exceptions.TooSmallAmountOfMoneyException;
import com.FireEmbelm.FireEmblem.business.value.categories.WeaponCategory;
import com.FireEmbelm.FireEmblem.business.value.equipment.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class BlacksmithServiceTest {

    private Weapon weaponToUpgrade;
    private Weapon errorWeapon;
    private ItemsConvoy mItemsConvoy;

    private BlacksmithService mBlacksmithService = new BlacksmithService();

    @BeforeEach
    void setUp() {
        weaponToUpgrade = new Weapon(
                "Bronze Sword",1,3,100, 0,
                0,50,1, 350, WeaponCategory.SWORD
        );

        errorWeapon = new Weapon("Bronze Sword+",1,3,100, 0,
                0,50,1, 350, WeaponCategory.SWORD
        );

        mItemsConvoy = new ItemsConvoy(
                5000,
                new ArrayList<>()
        );
    }

    @Test
    void testUpgradeWeapon() throws InvalidEquipmentException, TooSmallAmountOfMoneyException {
        Assertions.assertThrows(
                InvalidEquipmentException.class,
                () -> mBlacksmithService.upgradeWeapon(errorWeapon, mItemsConvoy)
        );

        Assertions.assertEquals(
                errorWeapon.getName(),
                mBlacksmithService.upgradeWeapon(weaponToUpgrade, mItemsConvoy).getName()
        );

        mItemsConvoy.setMoney(1000);

        Assertions.assertThrows(
                TooSmallAmountOfMoneyException.class,
                () -> mBlacksmithService.upgradeWeapon(weaponToUpgrade, mItemsConvoy)
        );

    }
}
