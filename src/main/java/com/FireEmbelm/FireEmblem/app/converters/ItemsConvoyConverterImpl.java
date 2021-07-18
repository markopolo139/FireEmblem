package com.FireEmbelm.FireEmblem.app.converters;

import com.FireEmbelm.FireEmblem.app.data.entities.ItemsConvoyEntity;
import com.FireEmbelm.FireEmblem.business.entitie.ItemsConvoy;
import com.FireEmbelm.FireEmblem.business.value.categories.ConsumableItemCategory;
import com.FireEmbelm.FireEmblem.business.value.equipment.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class ItemsConvoyConverterImpl implements ItemsConvoyConverter {

    @Autowired
    private WeaponConverter mWeaponConverter;

    @Autowired
    private HealingItemConverter mHealingItemConverter;

    @Override
    public ItemsConvoy convertToItemsConvoy(ItemsConvoyEntity itemsConvoyEntity) {


        return new ItemsConvoy(
                itemsConvoyEntity.money,
                Stream.of(
                        mHealingItemConverter.convertListToHealingItem(itemsConvoyEntity.healingItems),
                        mWeaponConverter.convertListToWeapon(itemsConvoyEntity.weapons),
                        itemsConvoyEntity.sealType,
                        itemsConvoyEntity.statUpType
                ).flatMap(List::stream).collect(Collectors.toCollection(ArrayList::new))
        );
    }

    @Override
    public ItemsConvoyEntity convertToEntity(ItemsConvoy itemsConvoy) {
        return new ItemsConvoyEntity(
                null,
                itemsConvoy.getMoney(),
                mHealingItemConverter.convertListToEntity(
                        itemsConvoy.getHealingItems().stream()
                                .map(i -> (HealingItemWithUses) i).collect(Collectors.toList())
                ),
                mWeaponConverter.convertListToEntity(
                        itemsConvoy.getWeapons().stream()
                                .map(i -> (Weapon) i).collect(Collectors.toList())),
                itemsConvoy.getByItemCategory(ConsumableItemCategory.SEALS).stream()
                        .map(i -> (Seals) i).collect(Collectors.toList()),
                itemsConvoy.getByItemCategory(ConsumableItemCategory.STATS_UP_ITEMS).stream()
                        .map(i -> (StatsUpItems) i).collect(Collectors.toList())
        );
    }

    @Override
    public List<ItemsConvoy> convertListToItemsConvoy(List<ItemsConvoyEntity> itemsConvoyEntities) {
        return itemsConvoyEntities.stream().map(this::convertToItemsConvoy).collect(Collectors.toList());
    }

    @Override
    public List<ItemsConvoyEntity> convertListToEntity(List<ItemsConvoy> itemsConvoys) {
        return itemsConvoys.stream().map(this::convertToEntity).collect(Collectors.toList());
    }
}
