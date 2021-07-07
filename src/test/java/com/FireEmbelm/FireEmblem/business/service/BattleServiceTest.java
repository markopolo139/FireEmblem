package com.FireEmbelm.FireEmblem.business.service;

import com.FireEmbelm.FireEmblem.business.entitie.Character;
import com.FireEmbelm.FireEmblem.business.entitie.CharacterClass;
import com.FireEmbelm.FireEmblem.business.entitie.Enemy;
import com.FireEmbelm.FireEmblem.business.entitie.ItemsConvoy;
import com.FireEmbelm.FireEmblem.business.exceptions.OutOfRangeException;
import com.FireEmbelm.FireEmblem.business.utils.Utils;
import com.FireEmbelm.FireEmblem.business.value.Field.Spot;
import com.FireEmbelm.FireEmblem.business.value.Field.SpotsType;
import com.FireEmbelm.FireEmblem.business.value.categories.WeaponCategory;
import com.FireEmbelm.FireEmblem.business.value.character.related.CharacterState;
import com.FireEmbelm.FireEmblem.business.value.character.related.StatsType;
import com.FireEmbelm.FireEmblem.business.value.equipment.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class BattleServiceTest {

    private BattleService mBattleService = new BattleService( new CharacterDevelopmentService());
    private Character mCharacter;
    private Enemy mEnemy;
    private ItemsConvoy mItemsConvoy;
    private Spot mAttackerSpot = new Spot(SpotsType.GRASS,1,1,null);
    private Spot mDefenderSpot = new Spot(SpotsType.FORREST,2,1,null);

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
                mItemsConvoy.getWeapons().get(0),
                new Equipment[]{
                        mItemsConvoy.getEquipmentCollection().get(0),
                        mItemsConvoy.getEquipmentCollection().get(0),
                        mItemsConvoy.getWeapons().get(0)
                },
                Utils.startUpWeaponProgress(),
                CharacterClass.LORD,
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
                new Equipment[]{
                        mItemsConvoy.getEquipmentCollection().get(0),
                        mItemsConvoy.getEquipmentCollection().get(0),
                        mItemsConvoy.getWeapons().get(0)
                },
                Utils.startUpWeaponProgress(),
                CharacterClass.LORD,
                CharacterState.ALIVE,
                false,
                Seals.HEART_SEAL,
                false,
                1000
        );

        mCharacter.getWeaponProgresses().get(WeaponCategory.SWORD.name()).setProgress(95);

        mAttackerSpot.setCharacterOnSpot(mCharacter);
        mDefenderSpot.setCharacterOnSpot(mEnemy);
    }

    @Test
    void testCurrentEquipedItemIsWeapon() {
        Assertions.assertTrue(mCharacter.getCurrentEquipedItem().getItemCategory() instanceof WeaponCategory);
    }

    @Test
    void testCalculateDamage() {
        Assertions.assertEquals(1, mBattleService.calculateDamageDealt(mAttackerSpot,mDefenderSpot));

        mCharacter.setCurrentEquipedItem(
                mItemsConvoy.getWeapons().stream()
                        .filter(i -> i.getItemCategory().equals(WeaponCategory.TOME))
                        .findFirst()
                        .get()
        );
        mCharacter.getCharacterBattleStats().calculateBattleStats(mCharacter);
        Assertions.assertEquals(2, mBattleService.calculateDamageDealt(mAttackerSpot,mDefenderSpot));
    }

    @Test
    void testSettingRemainingHp() {
        mBattleService.calculateDamageAndSetRemainingHp(mDefenderSpot,mAttackerSpot);

        Assertions.assertEquals(15,mAttackerSpot.getCharacterOnSpot().getRemainingHealth());
        Assertions.assertFalse(mBattleService.didAttackHit(mDefenderSpot,mAttackerSpot));

        mEnemy.setRemainingHealth(1);
        mBattleService.calculateDamageAndSetRemainingHp(mAttackerSpot,mDefenderSpot);
        Assertions.assertEquals(-2, mDefenderSpot.getCharacterOnSpot().getRemainingHealth());
        Assertions.assertEquals(CharacterState.DEAD, mEnemy.getCharacterState());
    }

    @Test
    void testGettingMoneyItemAndExp() {
        mEnemy.setCharacterState(CharacterState.DEAD);

        mBattleService.gettingExpAndMoney(mCharacter,mEnemy,mItemsConvoy);

        Assertions.assertNotEquals(0,mCharacter.getExp());
        Assertions.assertEquals(6000, mItemsConvoy.getMoney());
        Assertions.assertTrue(mItemsConvoy.getEquipmentCollection().contains(Seals.HEART_SEAL));
    }

    @Test
    void testIsDoubleAttack() {
        Assertions.assertFalse(mBattleService.isDoubleAttack(mCharacter, mEnemy));

        mCharacter.getStats().get(StatsType.SPEED).setValue(6);

        Assertions.assertTrue(mBattleService.isDoubleAttack(mCharacter, mEnemy));
    }

    @Test
    void testIsDefenderInRange() throws OutOfRangeException {
        Assertions.assertDoesNotThrow(() -> mBattleService.isDefenderInWeaponRange(mAttackerSpot,mDefenderSpot));

        mAttackerSpot.setHeight(10);

        Assertions.assertThrows(OutOfRangeException.class, () -> mBattleService.isDefenderInWeaponRange(mAttackerSpot,mDefenderSpot));
    }

    @Test
    void testUpdateWeaponUse() {

        mEnemy.setCharacterState(CharacterState.DEAD);
        mBattleService.updateWeaponUse(mEnemy);
        Assertions.assertEquals(50, mEnemy.getCurrentEquipedItem().getUses());

        mBattleService.updateWeaponUse(mCharacter);
        Assertions.assertEquals(49,mCharacter.getCurrentEquipedItem().getUses());

        mCharacter.getCurrentEquipedItem().setUses(1);
        mBattleService.updateWeaponUse(mCharacter);
        Assertions.assertNull(mCharacter.getCurrentEquipedItem());

    }
}
