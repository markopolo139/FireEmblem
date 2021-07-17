package com.FireEmbelm.FireEmblem.app.converters.appBuisness;

import com.FireEmbelm.FireEmblem.app.data.entities.ItemsConvoyEntity;
import com.FireEmbelm.FireEmblem.business.entitie.ItemsConvoy;
import com.FireEmbelm.FireEmblem.business.value.categories.ConsumableItemCategory;
import com.FireEmbelm.FireEmblem.business.value.equipment.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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
                new ArrayList<>(
                        (Collection<? extends Equipment>) Arrays.asList(
                                mHealingItemConverter.convertListToHealingItem(itemsConvoyEntity.healingItems),
                                mWeaponConverter.convertListToWeapon(itemsConvoyEntity.weapons),
                                itemsConvoyEntity.sealType,
                                itemsConvoyEntity.statUpType
                        )
                )
        );
    }

    @Override
    public ItemsConvoyEntity convertToEntity(ItemsConvoy itemsConvoy) {
        return new ItemsConvoyEntity(
                null,
                itemsConvoy.getMoney(),
                mHealingItemConverter.convertListToEntity(
                        new ArrayList<>(
                                (Collection<? extends HealingItemWithUses>) itemsConvoy.getHealingItems()
                        )),
                mWeaponConverter.convertListToEntity(
                        new ArrayList<>(
                                (Collection<? extends Weapon>) itemsConvoy.getWeapons()
                        )),
                new ArrayList<>(
                        (Collection<? extends Seals>) itemsConvoy.getByItemCategory(ConsumableItemCategory.SEALS)
                ),
                new ArrayList<>(
                        (Collection<? extends StatsUpItems>) itemsConvoy.getByItemCategory(ConsumableItemCategory.STATS_UP_ITEMS)
                )
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
