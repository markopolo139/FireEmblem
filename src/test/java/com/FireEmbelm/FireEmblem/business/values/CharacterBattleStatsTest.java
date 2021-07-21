package com.FireEmbelm.FireEmblem.business.values;

import com.FireEmbelm.FireEmblem.business.entitie.Character;
import com.FireEmbelm.FireEmblem.business.entitie.CharacterClass;
import com.FireEmbelm.FireEmblem.business.utils.Utils;
import com.FireEmbelm.FireEmblem.business.value.categories.WeaponCategory;
import com.FireEmbelm.FireEmblem.business.value.character.related.CharacterState;
import com.FireEmbelm.FireEmblem.business.value.equipment.Weapon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

public class CharacterBattleStatsTest {

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
                        "Iron Sword",2,5,95, 0,
                        0,50,1, 520, WeaponCategory.SWORD
                ),
                new ArrayList<>(
                        Collections.singleton(
                                new Weapon(
                                        "Iron Sword",2,5,95, 0,
                                        0,50,1, 520, WeaponCategory.SWORD
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
    void testCalculatingBattleStats() {
        Assertions.assertEquals(104, mCharacter.getCharacterBattleStats().getHitRate());
        Assertions.assertEquals(12, mCharacter.getCharacterBattleStats().getAvoid());
        Assertions.assertEquals(3, mCharacter.getCharacterBattleStats().getCritical());
    }
}
