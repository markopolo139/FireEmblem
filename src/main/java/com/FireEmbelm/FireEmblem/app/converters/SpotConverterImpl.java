package com.FireEmbelm.FireEmblem.app.converters;

import com.FireEmbelm.FireEmblem.app.data.entities.SpotEntity;
import com.FireEmbelm.FireEmblem.business.entitie.Character;
import com.FireEmbelm.FireEmblem.business.entitie.Enemy;
import com.FireEmbelm.FireEmblem.business.value.field.Spot;
import com.FireEmbelm.FireEmblem.business.value.field.SpotsType;
import com.FireEmbelm.FireEmblem.web.models.request.SpotModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SpotConverterImpl implements SpotConverter {

    @Autowired
    private CharacterConverter mCharacterConverter;

    @Autowired
    private EnemyConverter mEnemyConverter;

    @Override
    public Spot convertEntityToSpot(SpotEntity spotEntity) {
        return new Spot(
                spotEntity.spotType,
                spotEntity.height,
                spotEntity.width,
                spotEntity.characterId != null ?
                        mCharacterConverter.convertEntityToCharacter(spotEntity.characterId)
                        : mEnemyConverter.convertEntityToEnemy(spotEntity.enemyId)
        );
    }

    @Override
    public SpotEntity convertToEntity(Spot spot) {
        return new SpotEntity(
                null,
                spot.getSpotsType(),
                spot.getHeight(),
                spot.getWidth(),
                spot.getCharacterOnSpot() instanceof Character ?
                        mCharacterConverter.convertToEntity((Character) spot.getCharacterOnSpot()) : null,
                spot.getCharacterOnSpot() instanceof Enemy ?
                        mEnemyConverter.convertToEntity((Enemy) spot.getCharacterOnSpot()) : null
                );
    }

    @Override
    public List<Spot> convertEntityListToSpot(List<SpotEntity> spotEntities) {
        return spotEntities.stream().map(this::convertEntityToSpot).collect(Collectors.toList());
    }

    @Override
    public List<SpotEntity> convertListToEntity(List<Spot> spots) {
        return spots.stream().map(this::convertToEntity).collect(Collectors.toList());
    }

    @Override
    public Spot convertModelToSpot(SpotModel spotModel) {
        return new Spot(
                SpotsType.valueOf(spotModel.spotType),
                spotModel.height,
                spotModel.width,
                spotModel.characterOnSpot != null ?
                        mCharacterConverter.convertModelToCharacter(spotModel.characterOnSpot)
                        : mEnemyConverter.convertModelToEnemy(spotModel.enemyOnSpot)
        );
    }

    @Override
    public SpotModel convertToModel(Spot spot) {
        return new SpotModel(
                spot.getSpotsType().name(),
                spot.getHeight(),
                spot.getWidth(),
                spot.getCharacterOnSpot() instanceof Character ?
                        mCharacterConverter.convertToModel((Character) spot.getCharacterOnSpot()) : null,
                spot.getCharacterOnSpot() instanceof Enemy ?
                        mEnemyConverter.convertToModel((Enemy) spot.getCharacterOnSpot()) : null
        );
    }

    @Override
    public List<Spot> convertModelListToSpot(List<SpotModel> spotModels) {
        return spotModels.stream().map(this::convertModelToSpot).collect(Collectors.toList());
    }

    @Override
    public List<SpotModel> convertListToModel(List<Spot> spots) {
        return spots.stream().map(this::convertToModel).collect(Collectors.toList());
    }
}
