package com.FireEmbelm.FireEmblem.business.service;

import com.FireEmbelm.FireEmblem.business.entitie.Character;
import com.FireEmbelm.FireEmblem.business.entitie.CharacterClass;
import com.FireEmbelm.FireEmblem.business.entitie.Enemy;
import com.FireEmbelm.FireEmblem.business.entitie.ItemsConvoy;
import com.FireEmbelm.FireEmblem.business.utils.Utils;
import com.FireEmbelm.FireEmblem.business.value.categories.WeaponCategory;
import com.FireEmbelm.FireEmblem.business.value.character.related.CharacterState;
import com.FireEmbelm.FireEmblem.business.value.equipment.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class CharacterDevelopmentTest {

    private CharacterDevelopmentService mCharacterDevelopmentService = new CharacterDevelopmentService();
    private Character mCharacter;
    private Enemy mEnemy;
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
                false,
                Seals.HEART_SEAL,
                false,
                1000
        );

        mCharacter.getWeaponProgresses().get(WeaponCategory.SWORD).setProgress(95);
    }

    @Test
    void testGetExpALive() {
        mEnemy.setLevel(3);

        mCharacterDevelopmentService.increaseExpNotDead(mCharacter,mEnemy);

        Assertions.assertEquals(11, mCharacter.getExp());

        mEnemy.setLevel(1);
        mCharacter.setLevel(2);
        mCharacter.setExp(0);

        mCharacterDevelopmentService.increaseExpNotDead(mCharacter, mEnemy);

        Assertions.assertEquals(10, mCharacter.getExp());

        mCharacter.setLevel(5);
        mCharacter.setExp(0);

        mCharacterDevelopmentService.increaseExpNotDead(mCharacter, mEnemy);

        Assertions.assertEquals( 9, mCharacter.getExp());
    }

    @Test
    void testGetExpDead() {
        mEnemy.setLevel(3);

        mCharacterDevelopmentService.increaseExpDead(mCharacter,mEnemy);

        Assertions.assertEquals(26, mCharacter.getExp());

        mEnemy.setLevel(1);
        mCharacter.setLevel(2);
        mCharacter.setExp(0);

        mCharacterDevelopmentService.increaseExpDead(mCharacter, mEnemy);

        Assertions.assertEquals(20, mCharacter.getExp());

        mCharacter.setLevel(5);
        mCharacter.setExp(0);

        mCharacterDevelopmentService.increaseExpDead(mCharacter, mEnemy);

        Assertions.assertEquals( 14, mCharacter.getExp());

    }

    @Test
    void testWeaponProgress() {
        mCharacterDevelopmentService.increaseWeaponProgress(mCharacter);

        Assertions.assertEquals(0,mCharacter.getWeaponProgresses().get(WeaponCategory.SWORD).getProgress());
        Assertions.assertEquals(2,mCharacter.getWeaponProgresses().get(WeaponCategory.SWORD).getRank());

        mCharacter.setCurrentEquipedItem(mItemsConvoy.getWeapons().get(2));
        mCharacterDevelopmentService.increaseWeaponProgress(mCharacter);

        Assertions.assertEquals(5,mCharacter.getWeaponProgresses().get(WeaponCategory.AXE).getProgress());
        Assertions.assertEquals(1,mCharacter.getWeaponProgresses().get(WeaponCategory.AXE).getRank());
    }

    @Test
    void testLevelUp() {
        mCharacter.setExp(99);
        mCharacterDevelopmentService.increaseExpDead(mCharacter,mEnemy);

        Assertions.assertEquals(2,mCharacter.getLevel());
        Assertions.assertEquals(0,mCharacter.getExp());

    }
}
