package com.FireEmbelm.FireEmblem.app.converters;

import com.FireEmbelm.FireEmblem.app.data.entities.CharacterEntity;
import com.FireEmbelm.FireEmblem.app.data.entities.embeddable.HealingItemEmbeddable;
import com.FireEmbelm.FireEmblem.business.value.equipment.HealingItemWithUses;
import com.FireEmbelm.FireEmblem.web.models.request.CharacterModel;
import com.FireEmbelm.FireEmblem.web.models.request.HealingItemModel;

import java.util.List;

public interface HealingItemConverter {

    HealingItemWithUses convertEntityToHealingItem(HealingItemEmbeddable healingItemEmbeddable);
    HealingItemEmbeddable convertToEntity(HealingItemWithUses healingItemWithUses);

    List<HealingItemWithUses> convertEntityListToHealingItem(List<HealingItemEmbeddable> healingItemEmbeddables);
    List<HealingItemEmbeddable> convertListToEntity(List<HealingItemWithUses> healingItemWithUses);

    HealingItemWithUses convertModelToHealingItem(HealingItemModel healingItemModel);
    HealingItemModel convertToModel(HealingItemWithUses healingItemWithUses);

    List<HealingItemWithUses> convertModelListToHealingItem(List<HealingItemModel> healingItemModels);
    List<HealingItemModel> convertListToModel(List<HealingItemWithUses> healingItemWithUses);

    HealingItemEmbeddable convertModelToEntity(HealingItemModel healingItemModel);
    HealingItemModel convertEntityToModel(HealingItemEmbeddable healingItemEmbeddable);

    List<HealingItemEmbeddable> convertModelListToEntity(List<HealingItemModel> healingItemModels);
    List<HealingItemModel> convertEntityListToModel(List<HealingItemEmbeddable> healingItemEmbeddables);

}
