package com.FireEmbelm.FireEmblem.app.interactors;

import com.FireEmbelm.FireEmblem.app.converters.HealingItemConverter;
import com.FireEmbelm.FireEmblem.app.converters.ItemsConvoyConverter;
import com.FireEmbelm.FireEmblem.app.converters.WeaponConverter;
import com.FireEmbelm.FireEmblem.app.data.entities.ItemsConvoyEntity;
import com.FireEmbelm.FireEmblem.app.data.repository.HealingItemRepository;
import com.FireEmbelm.FireEmblem.app.data.repository.ItemsConvoyRepository;
import com.FireEmbelm.FireEmblem.app.data.repository.WeaponRepository;
import com.FireEmbelm.FireEmblem.business.entitie.ItemsConvoy;
import com.FireEmbelm.FireEmblem.business.exceptions.TooSmallAmountOfMoneyException;
import com.FireEmbelm.FireEmblem.business.service.ShopService;
import com.FireEmbelm.FireEmblem.business.value.equipment.Equipment;
import com.FireEmbelm.FireEmblem.business.value.equipment.Seals;
import com.FireEmbelm.FireEmblem.business.value.equipment.StatsUpItems;
import com.FireEmbelm.FireEmblem.web.models.request.HealingItemModel;
import com.FireEmbelm.FireEmblem.web.models.request.WeaponModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//TODO:
// in interactor : check status (if dead, set spot to null and save dead character, and spot with null) (if alive, save spot with character
@Service
public class ShopInteractor {

    @Autowired
    private ShopService mShopService;

    @Autowired
    private WeaponConverter mWeaponConverter;

    @Autowired
    private HealingItemConverter mHealingItemConverter;

    @Autowired
    private ItemsConvoyConverter mItemsConvoyConverter;

    @Autowired
    private ItemsConvoyRepository mItemsConvoyRepository;

    @Autowired
    private WeaponRepository mWeaponRepository;

    @Autowired
    private HealingItemRepository mHealingItemRepository;

    public ArrayList<Equipment> checkShopItemList(int maxLevelCharacter) {

        int weaponQuality = maxLevelCharacter / 4;

        if(weaponQuality == 0)
            weaponQuality = 1;

        List<WeaponModel> potentialWeapons = mWeaponRepository.findByQuality(weaponQuality)
                .stream().map(i -> mWeaponConverter.convertEntityToWeapon(i.weaponEmbeddable))
                .map(i -> mWeaponConverter.convertToModel(i)).collect(Collectors.toList());


        List<HealingItemModel> healingItems = mHealingItemRepository.findAll()
                .stream().map(i -> mHealingItemConverter.convertEntityToHealingItem(i.healItemEmbeddable))
                .map(i -> mHealingItemConverter.convertToModel(i)).collect(Collectors.toList());

        List<Seals> seals = Arrays.asList(Seals.values());
        List<StatsUpItems> statsUpItems = Arrays.asList(StatsUpItems.values());

        ArrayList<Equipment> returnList = new ArrayList<>(potentialWeapons);
        returnList.addAll(seals);
        returnList.add(statsUpItems.stream().findAny().orElseThrow());
        returnList.addAll(healingItems);

        return returnList;
    }

    public void buyItem(
            int randomItemId, ArrayList<Equipment> randomList, int itemsConvoyMoney
    ) throws TooSmallAmountOfMoneyException {

        ItemsConvoyEntity enteringEntity = mItemsConvoyRepository.findByMoney(itemsConvoyMoney);
        ItemsConvoy itemsConvoy = mItemsConvoyConverter.convertEntityToItemsConvoy(enteringEntity);

        randomList = randomList.stream().map(i -> {
            if (i instanceof WeaponModel)
                i = mWeaponConverter.convertModelToWeapon((WeaponModel) i);

            if(i instanceof HealingItemModel)
                i = mHealingItemConverter.convertModelToHealingItem((HealingItemModel) i);

            return i;
        }).collect(Collectors.toCollection(ArrayList::new));

        mShopService.buyItem(randomItemId,randomList,itemsConvoy);

        ItemsConvoyEntity exitingEntity = mItemsConvoyConverter.convertToEntity(itemsConvoy);
        exitingEntity.convoyId = enteringEntity.convoyId;

        mItemsConvoyRepository.save(exitingEntity);

    }

    public void sellItem(int itemsConvoyId, int itemsConvoyMoney) {

        ItemsConvoyEntity enteringEntity = mItemsConvoyRepository.findByMoney(itemsConvoyMoney);
        ItemsConvoy itemsConvoy = mItemsConvoyConverter.convertEntityToItemsConvoy(enteringEntity);

        mShopService.sellItem(itemsConvoyId,itemsConvoy);

        ItemsConvoyEntity exitingEntity = mItemsConvoyConverter.convertToEntity(itemsConvoy);
        exitingEntity.convoyId = enteringEntity.convoyId;

        mItemsConvoyRepository.save(exitingEntity);

    }

    public String checkItem(Equipment equipment) {
        return mShopService.checkItem(equipment);
    }

}
