package com.FireEmbelm.FireEmblem.app.converters;

import com.FireEmbelm.FireEmblem.app.data.entities.EnemyEntity;
import com.FireEmbelm.FireEmblem.business.entitie.CharacterClass;
import com.FireEmbelm.FireEmblem.business.entitie.Enemy;
import com.FireEmbelm.FireEmblem.business.value.character.related.CharacterState;
import com.FireEmbelm.FireEmblem.business.value.equipment.*;
import com.FireEmbelm.FireEmblem.web.models.request.EnemyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class EnemyConverterImpl implements EnemyConverter {

    @Autowired
    private StatConverter mStatConverter;

    @Autowired
    private WeaponProgressConverter mWeaponProgressConverter;

    @Autowired
    private WeaponConverter mWeaponConverter;

    @Autowired
    private HealingItemConverter mHealingItemConverter;

    @Override
    public Enemy convertEntityToEnemy(EnemyEntity enemyEntity) {

        Enemy enemy;

        if(enemyEntity == null) {
            enemy = null;
        }

        else{
            enemy = new Enemy(
                    enemyEntity.level,
                    enemyEntity.exp,
                    enemyEntity.remainingHealth,
                    mStatConverter.convertEntityListToHashMap(enemyEntity.stats),
                    null,
                    Stream.of(
                            mWeaponConverter.convertEntityListToWeapon(enemyEntity.weapons),
                            mHealingItemConverter.convertEntityListToHealingItem(enemyEntity.healingItems),
                            enemyEntity.sealType == null ? new ArrayList<Equipment>() : enemyEntity.sealType,
                            enemyEntity.statUpType == null ? new ArrayList<Equipment>() : enemyEntity.statUpType
                    ).flatMap(List::stream).collect(Collectors.toCollection(ArrayList::new)),
                    mWeaponProgressConverter.convertEntityListToHashMap(enemyEntity.weaponProgress),
                    enemyEntity.characterClass,
                    enemyEntity.characterState,
                    enemyEntity.moved,
                    null,
                    enemyEntity.boss,
                    enemyEntity.goldDrop
            );

            if(enemyEntity.currentEquippedItemId !=null) {
                enemy.setCurrentEquippedItem(enemy.getEquipment().get(enemyEntity.currentEquippedItemId));
                enemy.getCharacterBattleStats().calculateBattleStats(enemy);
            }

            if(enemyEntity.dropItemId != null)
                enemy.setDropItem(enemy.getEquipment().get(enemyEntity.dropItemId));

        }

        return enemy;
    }

    @Override
    public EnemyEntity convertToEntity(Enemy enemy) {

        if(enemy == null)
            return null;

        return new EnemyEntity(
                null,
                enemy.getName(),
                enemy.getLevel(),
                enemy.getExp(),
                enemy.getRemainingHealth(),
                !enemy.getEquipment().contains(enemy.getCurrentEquippedItem()) ? null
                        : enemy.getEquipment().indexOf(enemy.getCurrentEquippedItem()),
                enemy.getCharacterClass(),
                enemy.getCharacterState(),
                enemy.isMoved(),
                !enemy.getEquipment().contains(enemy.getDropItem()) ? null
                        : enemy.getEquipment().indexOf(enemy.getDropItem()),
                enemy.isBoss(),
                enemy.getGoldDrop(),
                mWeaponConverter.convertListToEntity(enemy.getEquipment().stream()
                        .filter(i -> i instanceof Weapon)
                        .map(i -> (Weapon) i)
                        .collect(Collectors.toList())
                ),
                mHealingItemConverter.convertListToEntity(enemy.getEquipment().stream()
                        .filter(i -> i instanceof HealingItemWithUses)
                        .map(i -> (HealingItemWithUses) i)
                        .collect(Collectors.toList())
                ),
                enemy.getEquipment().stream()
                        .filter(i -> i instanceof Seals)
                        .map(i -> (Seals) i).collect(Collectors.toList()),
                enemy.getEquipment().stream()
                        .filter(i -> i instanceof StatsUpItems)
                        .map(i -> (StatsUpItems) i).collect(Collectors.toList()),
                mStatConverter.convertListToEntity(new ArrayList<>(enemy.getStats().values())),
                mWeaponProgressConverter.convertListToEntity(new ArrayList<>(enemy.getWeaponProgresses().values()))
        );
    }

    @Override
    public List<Enemy> convertEntityListToEnemy(List<EnemyEntity> enemyEntities) {
        return enemyEntities.stream().map(this::convertEntityToEnemy).collect(Collectors.toList());
    }

    @Override
    public List<EnemyEntity> convertListToEntity(List<Enemy> enemies) {
        return enemies.stream().map(this::convertToEntity).collect(Collectors.toList());
    }

    @Override
    public Enemy convertModelToEnemy(EnemyModel enemyModel) {

        if (enemyModel == null)
            return null;

        Enemy enemy = new Enemy(
                enemyModel.level,
                enemyModel.exp,
                enemyModel.remainingHealth,
                mStatConverter.convertModelListToHashMap(enemyModel.stats),
                null,
                Stream.of(
                        mWeaponConverter.convertModelListToWeapon(enemyModel.weapons),
                        mHealingItemConverter.convertModelListToHealingItem(enemyModel.healingItems),
                        enemyModel.seals == null ? new ArrayList<Equipment>() : enemyModel.seals,
                        enemyModel.statsUpItems == null ? new ArrayList<Equipment>() : enemyModel.statsUpItems
                ).flatMap(List::stream).collect(Collectors.toCollection(ArrayList::new)),
                mWeaponProgressConverter.convertModelListToHashMap(enemyModel.weaponProgress),
                CharacterClass.valueOf(enemyModel.characterClass),
                CharacterState.valueOf(enemyModel.characterState),
                enemyModel.moved,
                null,
                enemyModel.boss,
                enemyModel.goldDrop
        );

        if(enemyModel.currentEquippedItemId != null) {
            enemy.setCurrentEquippedItem(enemy.getEquipment().get(enemyModel.currentEquippedItemId));
            enemy.getCharacterBattleStats().calculateBattleStats(enemy);
        }

        if(enemyModel.dropItemId != null)
            enemy.setDropItem(enemy.getEquipment().get(enemyModel.dropItemId));

        return enemy;
    }

    @Override
    public EnemyModel convertToModel(Enemy enemy) {

        if (enemy == null)
            return null;

        return new EnemyModel(
                enemy.getName(),
                enemy.getLevel(),
                enemy.getExp(),
                enemy.getRemainingHealth(),
                mStatConverter.convertListToModel(new ArrayList<>(enemy.getStats().values())),
                !enemy.getEquipment().contains(enemy.getCurrentEquippedItem()) ? null
                        : enemy.getEquipment().indexOf(enemy.getCurrentEquippedItem()),
                mWeaponConverter.convertListToModel(
                        enemy.getEquipment().stream().filter(i -> i instanceof Weapon)
                                .map( i -> (Weapon) i).collect(Collectors.toList())
                ),
                mHealingItemConverter.convertListToModel(
                        enemy.getEquipment().stream().filter(i -> i instanceof HealingItemWithUses)
                                .map( i -> (HealingItemWithUses) i).collect(Collectors.toList())
                ),
                enemy.getEquipment().stream().filter(i -> i instanceof Seals)
                        .map( i -> (Seals) i).collect(Collectors.toList()),
                enemy.getEquipment().stream().filter(i -> i instanceof StatsUpItems)
                        .map( i -> (StatsUpItems) i).collect(Collectors.toList()),
                mWeaponProgressConverter.convertListToModel(new ArrayList<>(enemy.getWeaponProgresses().values())),
                enemy.getCharacterClass().name(),
                enemy.getCharacterState().name(),
                enemy.isMoved(),
                !enemy.getEquipment().contains(enemy.getDropItem()) ? null
                        : enemy.getEquipment().indexOf(enemy.getDropItem()),
                enemy.isBoss(),
                enemy.getGoldDrop()
        );
    }

    @Override
    public List<Enemy> convertModelListToEnemy(List<EnemyModel> enemyModels) {
        return enemyModels.stream().map(this::convertModelToEnemy).collect(Collectors.toList());
    }

    @Override
    public List<EnemyModel> convertListToModel(List<Enemy> enemies) {
        return enemies.stream().map(this::convertToModel).collect(Collectors.toList());
    }

    @Override
    public EnemyEntity convertModelToEntity(EnemyModel enemyModel) {

        if (enemyModel == null)
            return null;

        return new EnemyEntity(
                null,
                enemyModel.name,
                enemyModel.level,
                enemyModel.exp,
                enemyModel.remainingHealth,
                enemyModel.currentEquippedItemId,
                CharacterClass.valueOf(enemyModel.characterClass),
                CharacterState.valueOf(enemyModel.characterState),
                enemyModel.moved,
                enemyModel.dropItemId,
                enemyModel.boss,
                enemyModel.goldDrop,
                mWeaponConverter.convertModelListToEntity(enemyModel.weapons),
                mHealingItemConverter.convertModelListToEntity(enemyModel.healingItems),
                enemyModel.seals,
                enemyModel.statsUpItems,
                mStatConverter.convertModelListToEntity(enemyModel.stats),
                mWeaponProgressConverter.convertModelListToEntity(enemyModel.weaponProgress)
        );
    }

    @Override
    public EnemyModel convertEntityToModel(EnemyEntity enemyEntity) {

        if (enemyEntity == null)
            return null;

        return new EnemyModel(
                enemyEntity.name,
                enemyEntity.level,
                enemyEntity.exp,
                enemyEntity.remainingHealth,
                mStatConverter.convertEntityListToModel(enemyEntity.stats),
                enemyEntity.currentEquippedItemId,
                mWeaponConverter.convertEntityListToModel(enemyEntity.weapons),
                mHealingItemConverter.convertEntityListToModel(enemyEntity.healingItems),
                enemyEntity.sealType,
                enemyEntity.statUpType,
                mWeaponProgressConverter.convertEntityListToModel(enemyEntity.weaponProgress),
                enemyEntity.characterClass.name(),
                enemyEntity.characterState.name(),
                enemyEntity.moved,
                enemyEntity.dropItemId,
                enemyEntity.boss,
                enemyEntity.goldDrop
        );
    }

    @Override
    public List<EnemyEntity> convertModelListToEntity(List<EnemyModel> enemyModels) {
        return enemyModels.stream().map(this::convertModelToEntity).collect(Collectors.toList());
    }

    @Override
    public List<EnemyModel> convertEntityListToModel(List<EnemyEntity> enemyEntities) {
        return enemyEntities.stream().map(this::convertEntityToModel).collect(Collectors.toList());
    }
}
