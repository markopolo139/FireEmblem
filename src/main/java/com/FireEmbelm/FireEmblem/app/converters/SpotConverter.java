package com.FireEmbelm.FireEmblem.app.converters;

import com.FireEmbelm.FireEmblem.app.data.entities.SpotEntity;
import com.FireEmbelm.FireEmblem.business.value.field.Spot;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SpotConverter {

    Spot convertEntityToSpot(SpotEntity spotEntity);
    SpotEntity convertToEntity(Spot spot);
    List<Spot> convertEntityListToSpot(List<SpotEntity> spotEntities);
    List<SpotEntity> convertListToEntity(List<Spot> spots);

}
