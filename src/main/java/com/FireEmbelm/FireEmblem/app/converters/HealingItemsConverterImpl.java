package com.FireEmbelm.FireEmblem.app.converters;

import com.FireEmbelm.FireEmblem.app.data.entities.embeddable.HealingItemEmbeddable;
import com.FireEmbelm.FireEmblem.business.value.equipment.HealingItemWithUses;
import com.FireEmbelm.FireEmblem.business.value.equipment.HealingItems;
import com.FireEmbelm.FireEmblem.web.models.request.HealingItemModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class HealingItemsConverterImpl implements HealingItemConverter {
    @Override
    public HealingItemWithUses convertEntityToHealingItem(HealingItemEmbeddable healingItemEmbeddable) {

        if (healingItemEmbeddable == null)
            return null;

        return new HealingItemWithUses(healingItemEmbeddable.healType,healingItemEmbeddable.uses);
    }

    @Override
    public HealingItemEmbeddable convertToEntity(HealingItemWithUses healingItemWithUses) {

        if(healingItemWithUses == null)
            return null;

        return new HealingItemEmbeddable(healingItemWithUses.getHealingItems(), healingItemWithUses.getUses());
    }

    @Override
    public List<HealingItemWithUses> convertEntityListToHealingItem(List<HealingItemEmbeddable> healingItemEmbeddables) {
        return healingItemEmbeddables.stream().map(this::convertEntityToHealingItem).collect(Collectors.toList());
    }

    @Override
    public List<HealingItemEmbeddable> convertListToEntity(List<HealingItemWithUses> healingItemWithUses) {
        return healingItemWithUses.stream().map(this::convertToEntity).collect(Collectors.toList());
    }

    @Override
    public HealingItemWithUses convertModelToHealingItem(HealingItemModel healingItemModel) {

        if (healingItemModel == null)
            return null;

        return new HealingItemWithUses(
                HealingItems.valueOf(healingItemModel.healingType),
                healingItemModel.uses
        );
    }

    @Override
    public HealingItemModel convertToModel(HealingItemWithUses healingItemWithUses) {

        if (healingItemWithUses == null)
            return null;

        return new HealingItemModel(
                healingItemWithUses.getHealingItems().name(),
                healingItemWithUses.getUses()
        );
    }

    @Override
    public List<HealingItemWithUses> convertModelListToHealingItem(List<HealingItemModel> healingItemModels) {
        return healingItemModels.stream().map(this::convertModelToHealingItem).collect(Collectors.toList());
    }

    @Override
    public List<HealingItemModel> convertListToModel(List<HealingItemWithUses> healingItemWithUses) {
        return healingItemWithUses.stream().map(this::convertToModel).collect(Collectors.toList());
    }
}
