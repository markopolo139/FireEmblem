package com.FireEmbelm.FireEmblem.app.converters.appBuisness;

import com.FireEmbelm.FireEmblem.app.data.entities.EnemyEntity;
import com.FireEmbelm.FireEmblem.business.entitie.Character;
import com.FireEmbelm.FireEmblem.business.entitie.Enemy;
import com.FireEmbelm.FireEmblem.business.value.character.related.Stat;
import com.FireEmbelm.FireEmblem.business.value.character.related.WeaponProgress;
import com.FireEmbelm.FireEmblem.business.value.equipment.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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
    public Enemy convertToEnemy(EnemyEntity enemyEntity) {

        Enemy enemy;

        if(enemyEntity == null) {
            enemy = null;
        }

        else{
            enemy = new Enemy(
                    enemyEntity.level,
                    enemyEntity.exp,
                    enemyEntity.remainingHealth,
                    mStatConverter.convertListToHashMap(enemyEntity.stats),
                    null,
                    Stream.of(
                                    mWeaponConverter.convertListToWeapon(enemyEntity.weapons),
                                    mHealingItemConverter.convertListToHealingItem(enemyEntity.healingItems),
                                    enemyEntity.sealType,
                                    enemyEntity.statUpType
                    ).flatMap(List::stream).collect(Collectors.toCollection(ArrayList::new)),
                    mWeaponProgressConverter.convertListToHashMap(enemyEntity.weaponProgress),
                    enemyEntity.characterClass,
                    enemyEntity.characterState,
                    enemyEntity.moved,
                    null,
                    enemyEntity.boss,
                    enemyEntity.goldDrop
            );

            if(enemyEntity.currentEquipedItemId !=null) {
                enemy.setCurrentEquipedItem(enemy.getEquipment().get(enemyEntity.currentEquipedItemId));
                enemy.setDropItem(enemy.getCurrentEquipedItem());
                enemy.getCharacterBattleStats().calculateBattleStats(enemy);
            }
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
                enemy.getEquipment().indexOf(enemy.getCurrentEquipedItem()),
                enemy.getCharacterClass(),
                enemy.getCharacterState(),
                enemy.isMoved(),
                enemy.getEquipment().indexOf(enemy.getCurrentEquipedItem()),
                enemy.isBoss(),
                enemy.getGoldDrop(),
                mHealingItemConverter.convertListToEntity(enemy.getEquipment().stream()
                        .filter(i -> i instanceof HealingItemWithUses)
                        .map(i -> (HealingItemWithUses) i)
                        .collect(Collectors.toList())
                ),
                mWeaponConverter.convertListToEntity(enemy.getEquipment().stream()
                        .filter(i -> i instanceof Weapon)
                        .map(i -> (Weapon) i)
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
    public List<Enemy> convertListToEnemy(List<EnemyEntity> enemyEntities) {
        return enemyEntities.stream().map(this::convertToEnemy).collect(Collectors.toList());
    }

    @Override
    public List<EnemyEntity> convertListToEntity(List<Enemy> enemies) {
        return enemies.stream().map(this::convertToEntity).collect(Collectors.toList());
    }
}
