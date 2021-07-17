package com.FireEmbelm.FireEmblem.app.converters.appBuisness;

import com.FireEmbelm.FireEmblem.app.data.entities.embeddable.HealingItemEmbeddable;
import com.FireEmbelm.FireEmblem.business.value.equipment.HealingItemWithUses;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class HealingItemsConverterImpl implements HealingItemConverter {
    @Override
    public HealingItemWithUses convertToHealingItem(HealingItemEmbeddable healingItemEmbeddable) {
        return new HealingItemWithUses(healingItemEmbeddable.healType,healingItemEmbeddable.uses);
    }

    @Override
    public HealingItemEmbeddable convertToEntity(HealingItemWithUses healingItemWithUses) {
        return new HealingItemEmbeddable(healingItemWithUses.getHealingItems(), healingItemWithUses.getUses());
    }

    @Override
    public List<HealingItemWithUses> convertListToHealingItem(List<HealingItemEmbeddable> healingItemEmbeddables) {
        return healingItemEmbeddables.stream().map(this::convertToHealingItem).collect(Collectors.toList());
    }

    @Override
    public List<HealingItemEmbeddable> convertListToEntity(List<HealingItemWithUses> healingItemWithUses) {
        return healingItemWithUses.stream().map(this::convertToEntity).collect(Collectors.toList());
    }
}
