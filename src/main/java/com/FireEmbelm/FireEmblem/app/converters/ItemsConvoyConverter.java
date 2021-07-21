package com.FireEmbelm.FireEmblem.app.converters;

import com.FireEmbelm.FireEmblem.app.data.entities.ItemsConvoyEntity;
import com.FireEmbelm.FireEmblem.business.entitie.ItemsConvoy;
import com.FireEmbelm.FireEmblem.web.models.request.ItemsConvoyModel;

import java.util.List;

public interface ItemsConvoyConverter {

    ItemsConvoy convertEntityToItemsConvoy(ItemsConvoyEntity itemsConvoyEntity);
    ItemsConvoyEntity convertToEntity(ItemsConvoy itemsConvoy);

    List<ItemsConvoy> convertEntityListToItemsConvoy(List<ItemsConvoyEntity> itemsConvoyEntities);
    List<ItemsConvoyEntity> convertListToEntity(List<ItemsConvoy> itemsConvoys);

    ItemsConvoy convertModelToItemsConvoy(ItemsConvoyModel itemsConvoyModel);
    ItemsConvoyModel convertToModel(ItemsConvoy itemsConvoy);

    List<ItemsConvoy> convertModelListToItemsConvoy(List<ItemsConvoyModel> itemsConvoyModels);
    List<ItemsConvoyModel> convertListToModel(List<ItemsConvoy> itemsConvoys);
}
