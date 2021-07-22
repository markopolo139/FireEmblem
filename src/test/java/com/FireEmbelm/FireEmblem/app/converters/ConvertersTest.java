package com.FireEmbelm.FireEmblem.app.converters;

import com.FireEmbelm.FireEmblem.app.data.entities.CharacterEntity;
import com.FireEmbelm.FireEmblem.app.data.entities.EnemyEntity;
import com.FireEmbelm.FireEmblem.app.data.entities.ItemsConvoyEntity;
import com.FireEmbelm.FireEmblem.app.data.entities.SpotEntity;
import com.FireEmbelm.FireEmblem.app.data.entities.embeddable.HealingItemEmbeddable;
import com.FireEmbelm.FireEmblem.app.data.entities.embeddable.StatEmbeddable;
import com.FireEmbelm.FireEmblem.app.data.entities.embeddable.WeaponEmbeddable;
import com.FireEmbelm.FireEmblem.app.data.entities.embeddable.WeaponProgressEmbeddable;
import com.FireEmbelm.FireEmblem.business.entitie.Character;
import com.FireEmbelm.FireEmblem.business.entitie.CharacterClass;
import com.FireEmbelm.FireEmblem.business.entitie.Enemy;
import com.FireEmbelm.FireEmblem.business.entitie.ItemsConvoy;
import com.FireEmbelm.FireEmblem.business.value.categories.WeaponCategory;
import com.FireEmbelm.FireEmblem.business.value.character.related.CharacterState;
import com.FireEmbelm.FireEmblem.business.value.character.related.Stat;
import com.FireEmbelm.FireEmblem.business.value.character.related.StatsType;
import com.FireEmbelm.FireEmblem.business.value.character.related.WeaponProgress;
import com.FireEmbelm.FireEmblem.business.value.equipment.*;
import com.FireEmbelm.FireEmblem.business.value.field.Spot;
import com.FireEmbelm.FireEmblem.business.value.field.SpotsType;
import com.FireEmbelm.FireEmblem.web.models.request.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

@SpringBootTest(classes = {
        CharacterConverter.class,
        CharacterConverterImpl.class,
        EnemyConverter.class,
        EnemyConverterImpl.class,
        HealingItemConverter.class,
        HealingItemsConverterImpl.class,
        ItemsConvoyConverterImpl.class,
        ItemsConvoyConverter.class,
        SpotConverter.class,
        SpotConverterImpl.class,
        StatConverter.class,
        StatConverterImpl.class,
        WeaponConverter.class,
        WeaponConverterImpl.class,
        WeaponProgressConverter.class,
        WeaponProgressConverterImpl.class
})
public class ConvertersTest {

    @Autowired
    private CharacterConverter mCharacterConverter;

    @Autowired
    private EnemyConverter mEnemyConverter;

    @Autowired
    private HealingItemConverter mHealingItemConverter;

    @Autowired
    private ItemsConvoyConverter mItemsConvoyConverter;

    @Autowired
    private SpotConverter mSpotConverter;

    @Autowired
    private StatConverter mStatConverter;

    @Autowired
    private WeaponConverter mWeaponConverter;

    @Autowired
    private WeaponProgressConverter mWeaponProgressConverter;

    @Test
    void testCharacterConverter() {

        Weapon weapon = new Weapon(
                "test",1,2,3,4,5,6,7,8,WeaponCategory.BOW
        );

        Character character = new Character(
                "test",
                1,
                1,
                1,
                new HashMap<>() {{
                    put(StatsType.STRENGTH,new Stat(StatsType.STRENGTH,5,5));
                    put(StatsType.HEALTH,new Stat(StatsType.HEALTH,2,5));
                    put(StatsType.SKILL,new Stat(StatsType.SKILL,2,5));
                    put(StatsType.SPEED,new Stat(StatsType.SPEED,2,5));
                    put(StatsType.LUCK,new Stat(StatsType.LUCK,2,5));
                }},
                weapon,
                new ArrayList<>(){{
                    add(weapon);
                    add(new HealingItemWithUses(HealingItems.MEND,20));
                    add(Seals.HEART_SEAL);
                    add(StatsUpItems.STRENGTH_UP);
                }},
                new HashMap<>() {{
                   put(WeaponCategory.SWORD, new WeaponProgress(WeaponCategory.SWORD,1,2));
                   put(WeaponCategory.BOW, new WeaponProgress(WeaponCategory.BOW,1,2));
                }},
                CharacterClass.ARCHER,
                CharacterState.DEAD,
                false
        );

        CharacterEntity characterEntity = new CharacterEntity(
                null,
                "test",
                1,1,1,0,
                CharacterClass.ARCHER,
                CharacterState.DEAD,
                false,
                Collections.singletonList(new HealingItemEmbeddable(HealingItems.MEND, 20)),
                Collections.singletonList(mWeaponConverter.convertToEntity(weapon)),
                Collections.singletonList(Seals.HEART_SEAL),
                Collections.singletonList(StatsUpItems.STRENGTH_UP),
                Arrays.asList(
                        new StatEmbeddable(StatsType.STRENGTH,5,5),
                        new StatEmbeddable(StatsType.HEALTH,2,5),
                        new StatEmbeddable(StatsType.SKILL,2,5),
                        new StatEmbeddable(StatsType.LUCK,2,5),
                        new StatEmbeddable(StatsType.SPEED,2,5)
                ),
                Arrays.asList(
                        new WeaponProgressEmbeddable(WeaponCategory.SWORD,1,2),
                        new WeaponProgressEmbeddable(WeaponCategory.BOW,1,2)
                )
        );

        CharacterModel characterModel = new CharacterModel(
                "test",
                1,1,1,
                Arrays.asList(
                        new StatModel(StatsType.STRENGTH.name(),5,5),
                        new StatModel(StatsType.HEALTH.name(),2,5),
                        new StatModel(StatsType.SKILL.name(),2,5),
                        new StatModel(StatsType.LUCK.name(),2,5),
                        new StatModel(StatsType.SPEED.name(),2,5)
                ),
                0,
                Collections.singletonList(mWeaponConverter.convertToModel(weapon)),
                Collections.singletonList(new HealingItemModel(HealingItems.MEND.name(), 20)),
                Collections.singletonList(Seals.HEART_SEAL),
                Collections.singletonList(StatsUpItems.STRENGTH_UP),
                Arrays.asList(
                        new WeaponProgressModel(WeaponCategory.SWORD.getName(),1,2),
                        new WeaponProgressModel(WeaponCategory.BOW.getName(),1,2)
                ),
                CharacterClass.ARCHER.name(),
                CharacterState.DEAD.name(),
                false
        );


        Character convertedFromEntity = mCharacterConverter.convertEntityToCharacter(characterEntity);

        CharacterEntity convertedEntity = mCharacterConverter.convertToEntity(character);

        CharacterModel convertedModel = mCharacterConverter.convertToModel(character);

        Character convertedFromModel = mCharacterConverter.convertModelToCharacter(characterModel);

        Assertions.assertEquals(character,convertedFromEntity);

        Assertions.assertEquals(characterEntity,convertedEntity);

        Assertions.assertEquals(character,convertedFromModel);

        Assertions.assertEquals(characterModel,convertedModel);

        Spot spot = new Spot(SpotsType.FORREST, 6, 4, character);

        SpotEntity spotEntity = new SpotEntity(null,SpotsType.FORREST,6,4,characterEntity,null);

        SpotModel spotModel = new SpotModel("FORREST",6,4,characterModel,null);

        Assertions.assertEquals(spot, mSpotConverter.convertEntityToSpot(spotEntity));

        Assertions.assertEquals(spotEntity, mSpotConverter.convertToEntity(spot));

        Assertions.assertEquals(spot, mSpotConverter.convertModelToSpot(spotModel));

        Assertions.assertEquals(spotModel, mSpotConverter.convertToModel(spot));

    }

    @Test
    void testEnemyConverter() {

        Weapon weapon = new Weapon(
                "test",1,2,3,4,5,6,7,8,WeaponCategory.BOW
        );

        Enemy enemy = new Enemy(
                1,2,3,
                new HashMap<>() {{
                    put(StatsType.STRENGTH,new Stat(StatsType.STRENGTH,5,5));
                    put(StatsType.HEALTH,new Stat(StatsType.HEALTH,2,5));
                    put(StatsType.SKILL,new Stat(StatsType.SKILL,2,5));
                    put(StatsType.SPEED,new Stat(StatsType.SPEED,2,5));
                    put(StatsType.LUCK,new Stat(StatsType.LUCK,2,5));
                }},
                weapon,
                new ArrayList<>(){{
                    add(weapon);
                    add(new HealingItemWithUses(HealingItems.MEND,20));
                    add(Seals.HEART_SEAL);
                    add(StatsUpItems.STRENGTH_UP);
                }},
                new HashMap<>() {{
                    put(WeaponCategory.SWORD, new WeaponProgress(WeaponCategory.SWORD,1,2));
                    put(WeaponCategory.BOW, new WeaponProgress(WeaponCategory.BOW,1,2));
                }},
                CharacterClass.ARCHER,
                CharacterState.DEAD,
                false,
                weapon, false, 555
        );

        EnemyEntity enemyEntity = new EnemyEntity(
                null,"Ruffian",1,2,3,0,
                CharacterClass.ARCHER, CharacterState.DEAD, false, 0,
                false, 555,
                Collections.singletonList(new HealingItemEmbeddable(HealingItems.MEND, 20)),
                Collections.singletonList(mWeaponConverter.convertToEntity(weapon)),
                Collections.singletonList(Seals.HEART_SEAL),
                Collections.singletonList(StatsUpItems.STRENGTH_UP),
                Arrays.asList(
                        new StatEmbeddable(StatsType.STRENGTH,5,5),
                        new StatEmbeddable(StatsType.HEALTH,2,5),
                        new StatEmbeddable(StatsType.SKILL,2,5),
                        new StatEmbeddable(StatsType.LUCK,2,5),
                        new StatEmbeddable(StatsType.SPEED,2,5)
                ),
                Arrays.asList(
                        new WeaponProgressEmbeddable(WeaponCategory.SWORD,1,2),
                        new WeaponProgressEmbeddable(WeaponCategory.BOW,1,2)
                )
        );

        EnemyModel enemyModel = new EnemyModel(
                "Ruffian",
                1,2,3,
                Arrays.asList(
                        new StatModel(StatsType.STRENGTH.name(),5,5),
                        new StatModel(StatsType.HEALTH.name(),2,5),
                        new StatModel(StatsType.SKILL.name(),2,5),
                        new StatModel(StatsType.LUCK.name(),2,5),
                        new StatModel(StatsType.SPEED.name(),2,5)
                ),
                0,
                Collections.singletonList(mWeaponConverter.convertToModel(weapon)),
                Collections.singletonList(new HealingItemModel(HealingItems.MEND.name(), 20)),
                Collections.singletonList(Seals.HEART_SEAL),
                Collections.singletonList(StatsUpItems.STRENGTH_UP),
                Arrays.asList(
                        new WeaponProgressModel(WeaponCategory.SWORD.getName(),1,2),
                        new WeaponProgressModel(WeaponCategory.BOW.getName(),1,2)
                ),
                CharacterClass.ARCHER.name(),
                CharacterState.DEAD.name(),
                false,
                0, false, 555
        );

        Enemy convertedFromEntity = mEnemyConverter.convertEntityToEnemy(enemyEntity);

        EnemyEntity convertedEntity = mEnemyConverter.convertToEntity(enemy);

        Enemy convertedFromModel = mEnemyConverter.convertModelToEnemy(enemyModel);

        EnemyModel convertedModel = mEnemyConverter.convertToModel(enemy);

        Assertions.assertEquals(enemy,convertedFromEntity);

        Assertions.assertEquals(enemyEntity,convertedEntity);

        Assertions.assertEquals(enemy, convertedFromModel);

        Assertions.assertEquals(enemyModel, convertedModel);
    }

    @Test
    void testHealingItemConverter() {

        HealingItemEmbeddable healingItemEmbeddable = new HealingItemEmbeddable(HealingItems.HEAL,31);

        HealingItemWithUses healingItemWithUses = new HealingItemWithUses(HealingItems.HEAL,31);

        HealingItemModel healingItemModel = new HealingItemModel("HEAL",31);

        Assertions.assertEquals(
                healingItemEmbeddable,
                mHealingItemConverter.convertToEntity(healingItemWithUses)
        );

        Assertions.assertEquals(
                healingItemWithUses,
                mHealingItemConverter.convertEntityToHealingItem(healingItemEmbeddable)
        );

        Assertions.assertEquals(
                healingItemModel,
                mHealingItemConverter.convertToModel(healingItemWithUses)
        );

        Assertions.assertEquals(
                healingItemWithUses,
                mHealingItemConverter.convertModelToHealingItem(healingItemModel)
        );
    }

    @Test
    void testItemsConveyConverter() {

        ItemsConvoy itemsConvoy = new ItemsConvoy(
                5412,
                new ArrayList<>(
                        Arrays.asList(
                                new HealingItemWithUses(HealingItems.MEND,20),
                                new Weapon("test",1,2,3,4,5,6,7,8,WeaponCategory.BOW),
                                new Weapon("test2",1,2,3,4,5,6,7,8,WeaponCategory.BOW),
                                new Weapon("test3",1,2,3,4,5,6,7,8,WeaponCategory.BOW),
                                Seals.HEART_SEAL,
                                StatsUpItems.STRENGTH_UP
                        )
                )
                );

        ItemsConvoyEntity itemsConvoyEntity = new ItemsConvoyEntity(
                null,
                5412,
                Collections.singletonList(new HealingItemEmbeddable(HealingItems.MEND,20)),
                Arrays.asList(
                        new WeaponEmbeddable("test",1,2,3,4,5,6,7,8,WeaponCategory.BOW),
                        new WeaponEmbeddable("test2",1,2,3,4,5,6,7,8,WeaponCategory.BOW),
                        new WeaponEmbeddable("test3",1,2,3,4,5,6,7,8,WeaponCategory.BOW)
                ),
                Collections.singletonList(Seals.HEART_SEAL),
                Collections.singletonList(StatsUpItems.STRENGTH_UP)
                );

        ItemsConvoyModel itemsConvoyModel = new ItemsConvoyModel(
                5412,
                Arrays.asList(
                        new WeaponModel("test",1,2,3,4,5,6,7,8,WeaponCategory.BOW.getName()),
                        new WeaponModel("test2",1,2,3,4,5,6,7,8,WeaponCategory.BOW.getName()),
                        new WeaponModel("test3",1,2,3,4,5,6,7,8,WeaponCategory.BOW.getName())

                ),
                Collections.singletonList(new HealingItemModel(HealingItems.MEND.name(),20)),
                Collections.singletonList(Seals.HEART_SEAL),
                Collections.singletonList(StatsUpItems.STRENGTH_UP)
        );

        ItemsConvoy convertedFromModel = mItemsConvoyConverter.convertModelToItemsConvoy(itemsConvoyModel);
        ItemsConvoyModel convertedToModel = mItemsConvoyConverter.convertToModel(itemsConvoy);

        Assertions.assertEquals(itemsConvoy,mItemsConvoyConverter.convertEntityToItemsConvoy(itemsConvoyEntity));

        Assertions.assertEquals(itemsConvoyEntity,mItemsConvoyConverter.convertToEntity(itemsConvoy));

        Assertions.assertEquals(itemsConvoy,convertedFromModel);

        Assertions.assertEquals(itemsConvoyModel,convertedToModel);

    }

    @Test
    void testSpotConverter() {
        Spot spot = new Spot(SpotsType.FORREST, 6, 4, null);

        SpotEntity spotEntity = new SpotEntity(null,SpotsType.FORREST,6,4,null,null);

        SpotModel spotModel = new SpotModel("FORREST",6,4,null,null);

        Assertions.assertEquals(spot, mSpotConverter.convertEntityToSpot(spotEntity));

        Assertions.assertEquals(spotEntity, mSpotConverter.convertToEntity(spot));

        Assertions.assertEquals(spot, mSpotConverter.convertModelToSpot(spotModel));

        Assertions.assertEquals(spotModel, mSpotConverter.convertToModel(spot));
    }

    @Test
    void testStatConverter() {

        Stat stat = new Stat(StatsType.HEALTH,14,66);

        StatEmbeddable statEmbeddable = new StatEmbeddable(StatsType.HEALTH,14,66);

        StatModel statModel = new StatModel("HEALTH",14,66);

        Assertions.assertEquals(stat,mStatConverter.convertEntityToStat(statEmbeddable));

        Assertions.assertEquals(statEmbeddable,mStatConverter.convertToEntity(stat));

        Assertions.assertEquals(stat,mStatConverter.convertModelToStat(statModel));

        Assertions.assertEquals(statModel,mStatConverter.convertToModel(stat));
    }

    @Test
    void testWeaponConverter() {

        WeaponEmbeddable weaponEmbeddable = new WeaponEmbeddable(
                "Test",
                1,2,3,4,5,6,7,8,WeaponCategory.BOW
        );

        Weapon weapon = new Weapon(
                "Test",
                1,2,3,4,5,6,7,8,WeaponCategory.BOW
        );

        WeaponModel weaponModel = new WeaponModel(
                "Test",
                1,2,3,4,5,6,7,8,"BOW"
        );

        Assertions.assertEquals(weapon,mWeaponConverter.convertEntityToWeapon(weaponEmbeddable));

        Assertions.assertEquals(weaponEmbeddable, mWeaponConverter.convertToEntity(weapon));

        Assertions.assertEquals(weapon,mWeaponConverter.convertModelToWeapon(weaponModel));

        Assertions.assertEquals(weaponModel, mWeaponConverter.convertToModel(weapon));

    }

    @Test
    void testWeaponProgressConverter() {

        WeaponProgressEmbeddable weaponProgressEmbeddable = new WeaponProgressEmbeddable(
                WeaponCategory.AXE,
                76,
                4
        );

        WeaponProgress weaponProgress = new WeaponProgress(WeaponCategory.AXE,76,4);

        WeaponProgressModel weaponProgressModel = new WeaponProgressModel("AXE",76,4);


        Assertions.assertEquals(
                weaponProgress,
                mWeaponProgressConverter.convertEntityToWeaponProgress(weaponProgressEmbeddable)
        );

        Assertions.assertEquals(
                weaponProgressEmbeddable,
                mWeaponProgressConverter.convertToEntity(weaponProgress)
        );

        Assertions.assertEquals(
                weaponProgress,
                mWeaponProgressConverter.convertModelToWeaponProgress(weaponProgressModel)
        );

        Assertions.assertEquals(
                weaponProgressModel,
                mWeaponProgressConverter.convertToModel(weaponProgress)
        );
    }
}
