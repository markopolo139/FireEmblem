package com.FireEmbelm.FireEmblem.app.converters.appBuisness;

import com.FireEmbelm.FireEmblem.app.data.entities.EnemyEntity;
import com.FireEmbelm.FireEmblem.business.entitie.Enemy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface EnemyConverter {

    Enemy convertToEnemy(EnemyEntity enemyEntity);
    EnemyEntity convertToEntity(Enemy enemy);
    List<Enemy> convertListToEnemy(List<EnemyEntity> enemyEntities);
    List<EnemyEntity> convertListToEntity(List<Enemy> enemies);

}
