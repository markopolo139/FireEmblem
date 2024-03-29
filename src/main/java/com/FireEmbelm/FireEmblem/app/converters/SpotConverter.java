package com.FireEmbelm.FireEmblem.app.converters;

import com.FireEmbelm.FireEmblem.app.data.entities.CharacterEntity;
import com.FireEmbelm.FireEmblem.app.data.entities.SpotEntity;
import com.FireEmbelm.FireEmblem.business.value.field.Spot;
import com.FireEmbelm.FireEmblem.web.models.request.CharacterModel;
import com.FireEmbelm.FireEmblem.web.models.request.SpotModel;

import java.util.List;

public interface SpotConverter {

    Spot convertEntityToSpot(SpotEntity spotEntity);
    SpotEntity convertToEntity(Spot spot);

    List<Spot> convertEntityListToSpot(List<SpotEntity> spotEntities);
    List<SpotEntity> convertListToEntity(List<Spot> spots);

    Spot convertModelToSpot(SpotModel spotModel);
    SpotModel convertToModel(Spot spot);

    List<Spot> convertModelListToSpot(List<SpotModel> spotModels);
    List<SpotModel> convertListToModel(List<Spot> spots);

    SpotEntity convertModelToEntity(SpotModel spotModel);
    SpotModel convertEntityToModel(SpotEntity spotEntity);

    List<SpotEntity> convertModelListToEntity(List<SpotModel> spotModels);
    List<SpotModel> convertEntityListToModel(List<SpotEntity> spotEntities);

}
