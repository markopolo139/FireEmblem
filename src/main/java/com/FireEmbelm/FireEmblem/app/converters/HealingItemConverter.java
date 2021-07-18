package com.FireEmbelm.FireEmblem.app.converters;

import com.FireEmbelm.FireEmblem.app.data.entities.embeddable.HealingItemEmbeddable;
import com.FireEmbelm.FireEmblem.business.value.equipment.HealingItemWithUses;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface HealingItemConverter {

    HealingItemWithUses convertEntityToHealingItem(HealingItemEmbeddable healingItemEmbeddable);
    HealingItemEmbeddable convertToEntity(HealingItemWithUses healingItemWithUses);

    List<HealingItemWithUses> convertEntityListToHealingItem(List<HealingItemEmbeddable> healingItemEmbeddables);
    List<HealingItemEmbeddable> convertListToEntity(List<HealingItemWithUses> healingItemWithUses);

}
