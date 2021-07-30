package com.FireEmbelm.FireEmblem.app.converters;

import com.FireEmbelm.FireEmblem.app.data.entities.CharacterEntity;
import com.FireEmbelm.FireEmblem.app.data.entities.EnemyEntity;
import com.FireEmbelm.FireEmblem.business.entitie.Enemy;
import com.FireEmbelm.FireEmblem.web.models.request.CharacterModel;
import com.FireEmbelm.FireEmblem.web.models.request.EnemyModel;

import java.util.List;

public interface EnemyConverter {

    Enemy convertEntityToEnemy(EnemyEntity enemyEntity);
    EnemyEntity convertToEntity(Enemy enemy);

    List<Enemy> convertEntityListToEnemy(List<EnemyEntity> enemyEntities);
    List<EnemyEntity> convertListToEntity(List<Enemy> enemies);

    Enemy convertModelToEnemy(EnemyModel enemyModel);
    EnemyModel convertToModel(Enemy enemy);

    List<Enemy> convertModelListToEnemy(List<EnemyModel> enemyModels);
    List<EnemyModel> convertListToModel(List<Enemy> enemies);

    EnemyEntity convertModelToEntity(EnemyModel enemyModel);
    EnemyModel convertEntityToModel(EnemyEntity enemyEntity);

    List<EnemyEntity> convertModelListToEntity(List<EnemyModel> enemyModels);
    List<EnemyModel> convertEntityListToModel(List<EnemyEntity> enemyEntities);

}
