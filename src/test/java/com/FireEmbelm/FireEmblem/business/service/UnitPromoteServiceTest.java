package com.FireEmbelm.FireEmblem.business.service;

import com.FireEmbelm.FireEmblem.business.entitie.Character;
import com.FireEmbelm.FireEmblem.business.entitie.CharacterClass;
import com.FireEmbelm.FireEmblem.business.exceptions.CharacterLevelException;
import com.FireEmbelm.FireEmblem.business.exceptions.PromoteException;
import com.FireEmbelm.FireEmblem.business.utils.Utils;
import com.FireEmbelm.FireEmblem.business.value.categories.WeaponCategory;
import com.FireEmbelm.FireEmblem.business.value.character.related.CharacterState;
import com.FireEmbelm.FireEmblem.business.value.equipment.Seals;
import com.FireEmbelm.FireEmblem.business.value.equipment.Weapon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UnitPromoteServiceTest {

    private UnitPromoteService mUnitPromoteService = new UnitPromoteService();
    private Character mCharacter;

    @BeforeEach
    void setUp() {
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
                new Weapon(
                        "Bronze Sword",1,3,100, 0,
                        0,50,1, 350, WeaponCategory.SWORD
                ),
                new ArrayList<>(
                        Collections.singletonList(
                                new Weapon(
                                        "Bronze Sword", 1, 3, 100, 0,
                                        0, 50, 1, 350, WeaponCategory.SWORD
                                )
                        )
                ),
                Utils.startUpWeaponProgress(),
                CharacterClass.LORD,
                CharacterState.ALIVE,
                false
        );
    }

    @Test
    void testGettingClassListAndPromoting() throws CharacterLevelException, PromoteException {

        Assertions.assertThrows(CharacterLevelException.class,
                () -> mUnitPromoteService.getPossibleClassesToPromote(mCharacter,Seals.MASTER_SEAL));

        mCharacter.setLevel(15);
        List<CharacterClass> mCharacterClasses = mUnitPromoteService.getPossibleClassesToPromote(mCharacter, Seals.MASTER_SEAL);

        Assertions.assertEquals(CharacterClass.GREAT_LORD, mCharacterClasses.stream().findAny().get());

        mUnitPromoteService.promoteCharacter(mCharacter,mCharacterClasses,0);

        mCharacter.setLevel(15);
        Assertions.assertThrows(PromoteException.class,
                () -> mUnitPromoteService.getPossibleClassesToPromote(mCharacter,Seals.MASTER_SEAL));

        Assertions.assertEquals(CharacterClass.GREAT_LORD,mCharacter.getCharacterClass());
        Assertions.assertEquals(0,mCharacter.getExp());
        Assertions.assertEquals(24,mCharacter.getRemainingHealth());

        mCharacter.setLevel(15);
        mCharacterClasses = mUnitPromoteService.getPossibleClassesToPromote(mCharacter, Seals.HEART_SEAL);
        Assertions.assertEquals(9, mCharacterClasses.size());

        mUnitPromoteService.promoteCharacter(mCharacter,mCharacterClasses,0);
        Assertions.assertNotEquals(CharacterClass.GREAT_LORD,mCharacter.getCharacterClass());

        mCharacter.setLevel(15);
        mCharacterClasses = mUnitPromoteService.getPossibleClassesToPromote(mCharacter, Seals.HEART_SEAL);
        Assertions.assertEquals(8, mCharacterClasses.size());

    }

}
