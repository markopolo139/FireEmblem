package com.FireEmbelm.FireEmblem.app.converters;

import com.FireEmbelm.FireEmblem.app.data.entities.ItemsConvoyEntity;
import com.FireEmbelm.FireEmblem.business.entitie.ItemsConvoy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ItemsConvoyConverter {

    ItemsConvoy convertEntityToItemsConvoy(ItemsConvoyEntity itemsConvoyEntity);
    ItemsConvoyEntity convertToEntity(ItemsConvoy itemsConvoy);
    List<ItemsConvoy> convertEntityListToItemsConvoy(List<ItemsConvoyEntity> itemsConvoyEntities);
    List<ItemsConvoyEntity> convertListToEntity(List<ItemsConvoy> itemsConvoys);

}
