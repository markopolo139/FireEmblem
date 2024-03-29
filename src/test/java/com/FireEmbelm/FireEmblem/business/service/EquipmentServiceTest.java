package com.FireEmbelm.FireEmblem.business.service;

import com.FireEmbelm.FireEmblem.business.entitie.Character;
import com.FireEmbelm.FireEmblem.business.entitie.CharacterClass;
import com.FireEmbelm.FireEmblem.business.entitie.ItemsConvoy;
import com.FireEmbelm.FireEmblem.business.exceptions.EquipmentLimitException;
import com.FireEmbelm.FireEmblem.business.exceptions.InvalidEquipmentException;
import com.FireEmbelm.FireEmblem.business.utils.Utils;
import com.FireEmbelm.FireEmblem.business.value.categories.WeaponCategory;
import com.FireEmbelm.FireEmblem.business.value.character.related.CharacterState;
import com.FireEmbelm.FireEmblem.business.value.equipment.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class EquipmentServiceTest {

    private EquipmentManagementService mEquipmentManagementService = new EquipmentManagementService();
    private Character mCharacter;
    private Character mCharacter2;
    private ItemsConvoy mItemsConvoy;

    @BeforeEach
    void setUp() {
        mItemsConvoy = new ItemsConvoy(
                5000,
                new ArrayList<>(
                        Arrays.asList(
                                new HealingItemWithUses(HealingItems.HEAL, 20),
                                new HealingItemWithUses(HealingItems.VULNERARY,3),
                                new HealingItemWithUses(HealingItems.VULNERARY,3),
                                new HealingItemWithUses(HealingItems.CONCOCTIONS,3),
                                Seals.MASTER_SEAL,
                                StatsUpItems.DEFENSE_UP,
                                new Weapon(
                                        "Bronze Sword",1,3,100, 0,
                                        0,50,1, 350, WeaponCategory.SWORD
                                ),
                                new Weapon(
                                        "Iron Sword",2,3,100, 0,
                                        0,50,1, 350, WeaponCategory.SWORD
                                ),
                                new Weapon(
                                        "Bronze Lance",1,3,90, 0,
                                        0,50,1, 350, WeaponCategory.LANCE
                                ),
                                new Weapon(
                                        "Bronze Axe",1,4,80, 0,
                                        0,50,1, 400, WeaponCategory.AXE
                                ),
                                new Weapon(
                                        "Fire",1,2,90, 0,
                                        0,50,2, 540, WeaponCategory.TOME
                                )
                        )
                )
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
                mItemsConvoy.getWeapons().get(0),
                new ArrayList<>(
                        Arrays.asList(
                                mItemsConvoy.getPlayerItems().get(0),
                                mItemsConvoy.getPlayerItems().get(0),
                                mItemsConvoy.getWeapons().get(0),
                                mItemsConvoy.getWeapons().get(2),
                                mItemsConvoy.getWeapons().get(1)
                        )
                ),
                Utils.startUpWeaponProgress(),
                CharacterClass.LORD,
                CharacterState.ALIVE,
                false
        );

        mCharacter2 = new Character(
                "TestCharacter2",
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
                mItemsConvoy.getWeapons().get(0),
                new ArrayList<>(
                        Arrays.asList(
                                mItemsConvoy.getPlayerItems().get(0),
                                mItemsConvoy.getPlayerItems().get(0),
                                mItemsConvoy.getWeapons().get(0),
                                mItemsConvoy.getWeapons().get(2),
                                mItemsConvoy.getWeapons().get(1)
                        )
                ),
                Utils.startUpWeaponProgress(),
                CharacterClass.LORD,
                CharacterState.ALIVE,
                false
        );
    }

    @Test
    void testGettingFromConvoy() throws EquipmentLimitException {
        mEquipmentManagementService.getEquipmentForCharacterFromConvoy(mCharacter,mItemsConvoy,4);

        Assertions.assertTrue(mCharacter.getEquipment().contains(Seals.MASTER_SEAL));
        Assertions.assertFalse(mItemsConvoy.getPlayerItems().contains(Seals.MASTER_SEAL));

        Assertions.assertThrows(EquipmentLimitException.class,
                () -> mEquipmentManagementService.getEquipmentForCharacterFromConvoy(mCharacter,mItemsConvoy,4));

        Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> mEquipmentManagementService.getEquipmentForCharacterFromConvoy(mCharacter,mItemsConvoy,-1));
    }

    @Test
    void testGivingToConvoy() {
        mEquipmentManagementService.giveEquipmentFromCharacterToConvoy(mCharacter,mItemsConvoy,0);

        Assertions.assertEquals(4,mCharacter.getEquipment().size());
        Assertions.assertEquals(12,mItemsConvoy.getPlayerItems().size());

        mEquipmentManagementService.giveEquipmentFromCharacterToConvoy(mCharacter,mItemsConvoy,1);

        Assertions.assertEquals(3,mCharacter.getEquipment().size());
        Assertions.assertNull(mCharacter.getCurrentEquippedItem());
        Assertions.assertEquals(13,mItemsConvoy.getPlayerItems().size());

        Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> mEquipmentManagementService.giveEquipmentFromCharacterToConvoy(mCharacter,mItemsConvoy,6));

    }

    @Test
    void testStoringAll() {
        mEquipmentManagementService.storeAllEquipmentFromCharacters(Collections.singletonList(mCharacter),mItemsConvoy);

        Assertions.assertEquals(0,mCharacter.getEquipment().size());
        Assertions.assertNull(mCharacter.getCurrentEquippedItem());

        Assertions.assertEquals(16,mItemsConvoy.getPlayerItems().size());
    }

    @Test
    void testTrade() throws EquipmentLimitException {
        mEquipmentManagementService.trade(mCharacter,mCharacter2,2);

        Assertions.assertNull(mCharacter.getCurrentEquippedItem());
        Assertions.assertEquals(4,mCharacter.getEquipment().size());
        Assertions.assertEquals(6,mCharacter2.getEquipment().size());

        Assertions.assertThrows(EquipmentLimitException.class,
                () -> mEquipmentManagementService.trade(mCharacter,mCharacter2,0));
    }

    @Test
    void testEquip() throws InvalidEquipmentException {
        Assertions.assertThrows(InvalidEquipmentException.class,
                () -> mEquipmentManagementService.equipItem(mCharacter,0));

        Assertions.assertThrows(InvalidEquipmentException.class,
                () -> mEquipmentManagementService.equipItem(mCharacter,4));

        Assertions.assertThrows(InvalidEquipmentException.class,
                () -> mEquipmentManagementService.equipItem(mCharacter,3));

        mCharacter.getWeaponProgresses().get(WeaponCategory.SWORD).setRank(2);
        mEquipmentManagementService.equipItem(mCharacter,4);

        Assertions.assertEquals(mItemsConvoy.getWeapons().get(1), mCharacter.getCurrentEquippedItem());
    }
}
