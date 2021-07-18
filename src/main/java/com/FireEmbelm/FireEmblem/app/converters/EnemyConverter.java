package com.FireEmbelm.FireEmblem.app.converters;

import com.FireEmbelm.FireEmblem.app.data.entities.EnemyEntity;
import com.FireEmbelm.FireEmblem.business.entitie.Character;
import com.FireEmbelm.FireEmblem.business.entitie.Enemy;
import com.FireEmbelm.FireEmblem.web.models.request.CharacterModel;
import com.FireEmbelm.FireEmblem.web.models.request.EnemyModel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface EnemyConverter {

    Enemy convertEntityToEnemy(EnemyEntity enemyEntity);
    EnemyEntity convertToEntity(Enemy enemy);

    List<Enemy> convertEntityListToEnemy(List<EnemyEntity> enemyEntities);
    List<EnemyEntity> convertListToEntity(List<Enemy> enemies);

    Enemy convertModelToEnemy(EnemyModel characterModel);
    EnemyModel convertToModel(Enemy character);

    List<Enemy> convertModelListToEnemy(List<EnemyModel> characterModel);
    List<EnemyModel> convertListToModel(List<Enemy> character);

}
