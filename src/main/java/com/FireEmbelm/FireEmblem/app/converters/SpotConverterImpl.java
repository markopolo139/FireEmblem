package com.FireEmbelm.FireEmblem.app.converters;

import com.FireEmbelm.FireEmblem.app.data.entities.SpotEntity;
import com.FireEmbelm.FireEmblem.business.entitie.Character;
import com.FireEmbelm.FireEmblem.business.entitie.Enemy;
import com.FireEmbelm.FireEmblem.business.value.field.Spot;
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
}
