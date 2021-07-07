package com.FireEmbelm.FireEmblem.business.service;

import com.FireEmbelm.FireEmblem.business.entitie.Character;
import com.FireEmbelm.FireEmblem.business.entitie.CharacterClass;
import com.FireEmbelm.FireEmblem.business.entitie.ItemsConvoy;
import com.FireEmbelm.FireEmblem.business.exceptions.EquipmentLimitException;
import com.FireEmbelm.FireEmblem.business.utils.Utils;
import com.FireEmbelm.FireEmblem.business.value.categories.WeaponCategory;
import com.FireEmbelm.FireEmblem.business.value.character.related.CharacterState;
import com.FireEmbelm.FireEmblem.business.value.equipment.*;
import org.hibernate.annotations.Index;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class EquipmentServiceTest {

    private EquipmentManagementService mEquipmentManagementService = new EquipmentManagementService();
    private Character mCharacter;
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
                                mItemsConvoy.getEquipmentCollection().get(0),
                                mItemsConvoy.getEquipmentCollection().get(0),
                                mItemsConvoy.getWeapons().get(0)
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
        Assertions.assertFalse(mItemsConvoy.getEquipmentCollection().contains(Seals.MASTER_SEAL));

        mEquipmentManagementService.getEquipmentForCharacterFromConvoy(mCharacter,mItemsConvoy,4);
        mEquipmentManagementService.getEquipmentForCharacterFromConvoy(mCharacter,mItemsConvoy,4);

        Assertions.assertThrows(EquipmentLimitException.class,
                () -> mEquipmentManagementService.getEquipmentForCharacterFromConvoy(mCharacter,mItemsConvoy,4));

        Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> mEquipmentManagementService.getEquipmentForCharacterFromConvoy(mCharacter,mItemsConvoy,-1));
    }

    @Test
    void testGivingToConvoy() {
        mEquipmentManagementService.giveEquipmentFromCharacterToConvoy(mCharacter,mItemsConvoy,0);

        Assertions.assertEquals(2,mCharacter.getEquipment().size());
        Assertions.assertEquals(11,mItemsConvoy.getEquipmentCollection().size());

        mEquipmentManagementService.giveEquipmentFromCharacterToConvoy(mCharacter,mItemsConvoy,1);

        Assertions.assertEquals(1,mCharacter.getEquipment().size());
        Assertions.assertNull(mCharacter.getCurrentEquipedItem());
        Assertions.assertEquals(12,mItemsConvoy.getEquipmentCollection().size());

        Assertions.assertThrows(IndexOutOfBoundsException.class,
                () -> mEquipmentManagementService.giveEquipmentFromCharacterToConvoy(mCharacter,mItemsConvoy,6));

    }

    @Test
    void testStoringAll() {
        mEquipmentManagementService.storeAllEquipmentFromCharacters(Collections.singletonList(mCharacter),mItemsConvoy);

        Assertions.assertEquals(0,mCharacter.getEquipment().size());
        Assertions.assertNull(mCharacter.getCurrentEquipedItem());

        Assertions.assertEquals(13,mItemsConvoy.getEquipmentCollection().size());
    }
}
