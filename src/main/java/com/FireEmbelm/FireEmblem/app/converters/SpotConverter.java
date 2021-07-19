package com.FireEmbelm.FireEmblem.app.converters;

import com.FireEmbelm.FireEmblem.app.data.entities.SpotEntity;
import com.FireEmbelm.FireEmblem.business.entitie.ItemsConvoy;
import com.FireEmbelm.FireEmblem.business.value.field.Spot;
import com.FireEmbelm.FireEmblem.web.models.request.ItemsConvoyModel;
import com.FireEmbelm.FireEmblem.web.models.request.SpotModel;
import org.springframework.stereotype.Component;

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

}
