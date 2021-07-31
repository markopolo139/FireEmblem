package com.FireEmbelm.FireEmblem.business.service;

import com.FireEmbelm.FireEmblem.business.entitie.Character;
import com.FireEmbelm.FireEmblem.business.entitie.CharacterClass;
import com.FireEmbelm.FireEmblem.business.entitie.ItemsConvoy;
import com.FireEmbelm.FireEmblem.business.exceptions.InvalidEquipmentException;
import com.FireEmbelm.FireEmblem.business.exceptions.TooSmallAmountOfMoneyException;
import com.FireEmbelm.FireEmblem.business.utils.Utils;
import com.FireEmbelm.FireEmblem.business.value.categories.WeaponCategory;
import com.FireEmbelm.FireEmblem.business.value.character.related.CharacterState;
import com.FireEmbelm.FireEmblem.business.value.equipment.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class BlacksmithServiceTest {

    private Weapon weaponToUpgrade;
    private Weapon errorWeapon;
    private Character mCharacter;
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

        mCharacter = new Character(
                "TestCharacter",
                1,
                0,
                15,
                Utils.createStats(
                        1,100,
                        1,80,
                        1,0,
                        1,0,
                        1,0,
                        1,0,
                        1,0,
                        1,0
                ),
                null,
                new ArrayList<>(
                        Arrays.asList(
                                weaponToUpgrade,
                                errorWeapon
                        )
                ),
                Utils.startUpWeaponProgress(),
                CharacterClass.LORD,
                CharacterState.ALIVE,
                false
        );
    }

    @Test
    void testUpgradeWeapon() throws InvalidEquipmentException, TooSmallAmountOfMoneyException {
        Assertions.assertThrows(
                InvalidEquipmentException.class,
                () -> mBlacksmithService.upgradeWeapon(mCharacter, 1, mItemsConvoy)
        );

        mItemsConvoy.setMoney(1000);
        Assertions.assertThrows(
                TooSmallAmountOfMoneyException.class,
                () -> mBlacksmithService.upgradeWeapon(mCharacter, 0, mItemsConvoy)
        );

        mItemsConvoy.setMoney(1500);

        mBlacksmithService.upgradeWeapon(mCharacter, 0, mItemsConvoy);

    Assertions.assertEquals(
                errorWeapon.getName(),
                mCharacter.getEquipment().get(0).getName()
        );



    }
}
