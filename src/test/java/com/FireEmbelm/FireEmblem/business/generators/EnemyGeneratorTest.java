package com.FireEmbelm.FireEmblem.business.generators;

import com.FireEmbelm.FireEmblem.business.entitie.Enemy;
import com.FireEmbelm.FireEmblem.business.service.generators.EnemyGenerator;
import com.FireEmbelm.FireEmblem.business.service.generators.FieldGenerator;
import com.FireEmbelm.FireEmblem.business.value.Field.Spot;
import com.FireEmbelm.FireEmblem.business.value.categories.WeaponCategory;
import com.FireEmbelm.FireEmblem.business.value.equipment.Equipment;
import com.FireEmbelm.FireEmblem.business.value.equipment.Weapon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class EnemyGeneratorTest {
    private EnemyGenerator mEnemyGenerator = new EnemyGenerator();
    private FieldGenerator mFieldGenerator = new FieldGenerator();
    private List<Weapon> mWeapons;

    @BeforeEach
    void setUp() {
        mWeapons = new ArrayList<>(
                Arrays.asList(
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
                                "Bronze Bow", 1, 2,100, 0,
                                0, 50, 2, 510, WeaponCategory.BOW
                        ),
                        new Weapon(
                                "Fire",1,2,90, 0,
                                0,50,2, 540, WeaponCategory.TOME
                        ),
                        new Weapon(
                                "Iron Sword",2,3,100, 0,
                                0,50,1, 350, WeaponCategory.SWORD
                        ),
                        new Weapon(
                                "Iron Lance",2,3,90, 0,
                                0,50,1, 350, WeaponCategory.LANCE
                        ),
                        new Weapon(
                                "Iron Axe",2,4,80, 0,
                                0,50,1, 400, WeaponCategory.AXE
                        ),
                        new Weapon(
                                "Archfire",2,2,90, 0,
                                0,50,2, 540, WeaponCategory.TOME
                        )
                )
        );
    }

    //DO manual debug to test
    @Test
    void testEnemyGenerator() {

        List<Spot> field = (List<Spot>) mFieldGenerator.generateNewField();
        Collection<Enemy> enemies = mEnemyGenerator.generateEnemy(field, mWeapons, 21);

        Assertions.assertFalse(enemies.isEmpty());
        Assertions.assertEquals(0, field.stream().filter(i -> i.getCharacterOnSpot() != null && i.getHeight() <= 3).count());
        Assertions.assertEquals(field.stream().filter(i -> i.getCharacterOnSpot()!=null).count(), enemies.size());
    }
}
