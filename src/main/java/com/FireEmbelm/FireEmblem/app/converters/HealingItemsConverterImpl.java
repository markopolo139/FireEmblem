package com.FireEmbelm.FireEmblem.app.converters;

import com.FireEmbelm.FireEmblem.app.data.entities.embeddable.HealingItemEmbeddable;
import com.FireEmbelm.FireEmblem.business.value.equipment.HealingItemWithUses;
import com.FireEmbelm.FireEmblem.business.value.equipment.HealingItems;
import com.FireEmbelm.FireEmblem.web.models.request.HealingItemModel;
import org.springframework.stereotype.Component;

import java.util.Collections;
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

        if (healingItemEmbeddables == null)
            return Collections.emptyList();

        return healingItemEmbeddables.stream().map(this::convertEntityToHealingItem).collect(Collectors.toList());
    }

    @Override
    public List<HealingItemEmbeddable> convertListToEntity(List<HealingItemWithUses> healingItemWithUses) {

        if (healingItemWithUses == null)
            return Collections.emptyList();

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

        if (healingItemModels == null)
            return Collections.emptyList();

        return healingItemModels.stream().map(this::convertModelToHealingItem).collect(Collectors.toList());
    }

    @Override
    public List<HealingItemModel> convertListToModel(List<HealingItemWithUses> healingItemWithUses) {

        if (healingItemWithUses == null)
            return Collections.emptyList();

        return healingItemWithUses.stream().map(this::convertToModel).collect(Collectors.toList());
    }

    @Override
    public HealingItemEmbeddable convertModelToEntity(HealingItemModel healingItemModel) {

        if (healingItemModel == null)
            return null;

        return new HealingItemEmbeddable(HealingItems.valueOf(healingItemModel.healingType), healingItemModel.uses);
    }

    @Override
    public HealingItemModel convertEntityToModel(HealingItemEmbeddable healingItemEmbeddable) {

        if (healingItemEmbeddable == null)
            return null;

        return  new HealingItemModel(healingItemEmbeddable.healType.name(),healingItemEmbeddable.uses);
    }

    @Override
    public List<HealingItemEmbeddable> convertModelListToEntity(List<HealingItemModel> healingItemModels) {

        if (healingItemModels == null)
            return Collections.emptyList();

        return healingItemModels.stream().map(this::convertModelToEntity).collect(Collectors.toList());
    }

    @Override
    public List<HealingItemModel> convertEntityListToModel(List<HealingItemEmbeddable> healingItemEmbeddables) {

        if (healingItemEmbeddables == null)
            return Collections.emptyList();

        return healingItemEmbeddables.stream().map(this::convertEntityToModel).collect(Collectors.toList());
    }
}
