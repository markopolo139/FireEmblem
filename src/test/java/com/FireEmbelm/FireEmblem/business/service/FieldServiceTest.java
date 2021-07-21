package com.FireEmbelm.FireEmblem.business.service;

import com.FireEmbelm.FireEmblem.business.entitie.Character;
import com.FireEmbelm.FireEmblem.business.entitie.CharacterClass;
import com.FireEmbelm.FireEmblem.business.entitie.Enemy;
import com.FireEmbelm.FireEmblem.business.entitie.ItemsConvoy;
import com.FireEmbelm.FireEmblem.business.exceptions.InvalidEquipmentException;
import com.FireEmbelm.FireEmblem.business.exceptions.InvalidSpotException;
import com.FireEmbelm.FireEmblem.business.utils.Utils;
import com.FireEmbelm.FireEmblem.business.value.categories.WeaponCategory;
import com.FireEmbelm.FireEmblem.business.value.character.related.CharacterState;
import com.FireEmbelm.FireEmblem.business.value.character.related.StatsType;
import com.FireEmbelm.FireEmblem.business.value.equipment.*;
import com.FireEmbelm.FireEmblem.business.value.field.Spot;
import com.FireEmbelm.FireEmblem.business.value.field.SpotsType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class FieldServiceTest {

    private FieldService mFieldService = new FieldService();
    private Character mCharacter;
    private Character mCharacter2;
    private Enemy mEnemy;
    private ItemsConvoy mItemsConvoy;
    private Spot mAttackerSpot = new Spot(SpotsType.GRASS,1,1,null);
    private Spot mHealedSpot = new Spot(SpotsType.FORT,1,2,null);
    private Spot mDefenderSpot = new Spot(SpotsType.FORREST,2,1,null);
    private Spot mEmptySpot = new Spot(SpotsType.FORREST,6,1,null);

    @BeforeEach
    void setUp() {
        mItemsConvoy = new ItemsConvoy(
                5000,
                new ArrayList<>(
                        Arrays.asList(
                                new HealingItemWithUses(HealingItems.HEAL, 20),
                                new HealingItemWithUses(HealingItems.VULNERARY,3),
                                new HealingItemWithUses(HealingItems.VULNERARY,3),
                                new HealingItemWithUses(HealingItems.CONCOCTIONS,2),
                                Seals.MASTER_SEAL,
                                StatsUpItems.DEFENSE_UP,
                                new Weapon(
                                        "Bronze Sword",1,3,150, 0,
                                        105,50,1, 350, WeaponCategory.SWORD
                                ),
                                new Weapon(
                                        "Bronze Lance",1,3,-100, 0,
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
                null,
                new ArrayList<>(
                        Arrays.asList(
                                mItemsConvoy.getEquipmentCollection().get(1),
                                mItemsConvoy.getEquipmentCollection().get(3),
                                mItemsConvoy.getEquipmentCollection().get(0),
                                mItemsConvoy.getEquipmentCollection().get(5)
                        )
                ),
                Utils.startUpWeaponProgress(),
                CharacterClass.PRIEST,
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
                null,
                new ArrayList<>(
                        Arrays.asList(
                                mItemsConvoy.getEquipmentCollection().get(1),
                                mItemsConvoy.getEquipmentCollection().get(3),
                                mItemsConvoy.getEquipmentCollection().get(0)
                        )
                ),
                Utils.startUpWeaponProgress(),
                CharacterClass.PRIEST,
                CharacterState.ALIVE,
                false
        );

        mEnemy = new Enemy(
                1,
                0,
                15,
                Utils.createStats(
                        1,100,
                        1,0,
                        1,0,
                        1,0,
                        1,0,
                        1,0,
                        1,0,
                        1,0
                ),
                mItemsConvoy.getWeapons().get(1),
                new ArrayList<>(
                        Arrays.asList(
                                mItemsConvoy.getEquipmentCollection().get(0),
                                mItemsConvoy.getEquipmentCollection().get(0),
                                mItemsConvoy.getWeapons().get(0),
                                mItemsConvoy.getWeapons().get(1)
                        )
                ),
                Utils.startUpWeaponProgress(),
                CharacterClass.LORD,
                CharacterState.ALIVE,
                false,
                Seals.HEART_SEAL,
                false,
                1000
        );

        mAttackerSpot.setCharacterOnSpot(mCharacter);
        mHealedSpot.setCharacterOnSpot(mCharacter2);
        mDefenderSpot.setCharacterOnSpot(mEnemy);
    }

    @Test
    void moveCharacter() throws InvalidSpotException {

        Assertions.assertThrows(
                InvalidSpotException.class,
                () -> mFieldService.moveCharacter(mAttackerSpot, mDefenderSpot)
        );

        mFieldService.moveCharacter(mAttackerSpot,mEmptySpot);
        Assertions.assertEquals(mEmptySpot.getCharacterOnSpot(), mCharacter);
        Assertions.assertNull(mAttackerSpot.getCharacterOnSpot());

        Spot TooFarSpot = new Spot(SpotsType.FORREST, 15,15,null);

        Assertions.assertThrows(
                InvalidSpotException.class,
                () -> mFieldService.moveCharacter(mEmptySpot,TooFarSpot)
        );

        mFieldService.moveCharacter(mDefenderSpot,mAttackerSpot);
        Assertions.assertEquals(mAttackerSpot.getCharacterOnSpot(), mEnemy);
        Assertions.assertNull(mDefenderSpot.getCharacterOnSpot());

    }

    @Test
    void testTurns() {

        int character2MaxHp = mHealedSpot.getCharacterOnSpot().getStats().get(StatsType.HEALTH).getValue()
                + mHealedSpot.getCharacterOnSpot().getCharacterClass().getBonusStats().get(StatsType.HEALTH).getValue();

        mHealedSpot.getCharacterOnSpot().setRemainingHealth(mHealedSpot.getCharacterOnSpot().getRemainingHealth() - 1);

        mFieldService.endTurn(mCharacter2);
        mFieldService.endTurn(mCharacter);

        mFieldService.startTurn(Arrays.asList(mAttackerSpot,mHealedSpot));

        Assertions.assertFalse(mCharacter.isMoved());
        Assertions.assertFalse(mCharacter2.isMoved());

        Assertions.assertEquals(character2MaxHp, mCharacter2.getRemainingHealth());

        mCharacter2.setRemainingHealth(1);

        mFieldService.endTurn(mCharacter2);
        mFieldService.startTurn(Collections.singletonList(mHealedSpot));

        Assertions.assertEquals(
                (character2MaxHp * mHealedSpot.getSpotsType().getPercentHeal() / 100) + 1,
                mCharacter2.getRemainingHealth()
        );
    }

    @Test
    void testUseConsumableItem() throws InvalidEquipmentException {

        Assertions.assertThrows(InvalidEquipmentException.class, () -> mFieldService.useConsumableItem(mCharacter,1));

        mFieldService.useConsumableItem(mCharacter,3);

        Assertions.assertFalse(mCharacter.getEquipment().contains(StatsUpItems.DEFENSE_UP));
        Assertions.assertEquals(3, mCharacter.getStats().get(StatsType.DEFENSE).getValue());
    }

    @Test
    void testUseHealingItem() throws InvalidEquipmentException {

        int characterMaxHp = mCharacter.getStats().get(StatsType.HEALTH).getValue()
                + mCharacter.getCharacterClass().getBonusStats().get(StatsType.HEALTH).getValue();

        Assertions.assertThrows(InvalidEquipmentException.class, () -> mFieldService.useHealingItem(mCharacter,2));

        mCharacter.setRemainingHealth(1);

        mFieldService.useHealingItem(mCharacter,0);

        Assertions.assertEquals(11, mCharacter.getRemainingHealth());
        Assertions.assertEquals(2,mCharacter.getEquipment().get(0).getUses());

        mCharacter.getEquipment().get(0).setUses(1);

        mFieldService.useHealingItem(mCharacter,0);

        Assertions.assertEquals(characterMaxHp, mCharacter.getRemainingHealth());
        Assertions.assertFalse(mCharacter.getEquipment().contains(mItemsConvoy.getEquipmentCollection().get(1)));

        mCharacter.setRemainingHealth(1);

        mFieldService.useHealingItem(mCharacter,0);

        Assertions.assertEquals(characterMaxHp, mCharacter.getRemainingHealth());
        Assertions.assertEquals(1,mCharacter.getEquipment().get(0).getUses());

    }

    @Test
    void testUseStaff() throws InvalidEquipmentException, InvalidSpotException {
        int character2MaxHp = mCharacter2.getStats().get(StatsType.HEALTH).getValue()
                + mCharacter2.getCharacterClass().getBonusStats().get(StatsType.HEALTH).getValue();

        mCharacter.setCharacterClass(CharacterClass.LORD);

        Assertions.assertThrows(InvalidEquipmentException.class, () -> mFieldService.useStaff(mAttackerSpot,mHealedSpot,3));
        Assertions.assertThrows(InvalidEquipmentException.class, () -> mFieldService.useStaff(mAttackerSpot,mHealedSpot,2));
        Assertions.assertThrows(InvalidEquipmentException.class, () -> mFieldService.useStaff(mAttackerSpot,mHealedSpot,0));

        mCharacter.setCharacterClass(CharacterClass.PRIEST);
        Assertions.assertThrows(InvalidSpotException.class, () -> mFieldService.useStaff(mAttackerSpot,mEmptySpot,2));

        mCharacter2.setRemainingHealth(1);

        mFieldService.useStaff(mAttackerSpot,mHealedSpot,2);

        Assertions.assertEquals(16, mCharacter2.getRemainingHealth());
        Assertions.assertEquals(19,mCharacter.getEquipment().get(2).getUses());

        mCharacter.getEquipment().get(2).setUses(1);

        mFieldService.useStaff(mAttackerSpot,mHealedSpot,2);

        Assertions.assertEquals(character2MaxHp, mCharacter2.getRemainingHealth());
        Assertions.assertFalse(mCharacter.getEquipment().contains(mItemsConvoy.getEquipmentCollection().get(0)));

    }
}
