package com.FireEmbelm.FireEmblem.app.interactors;

import com.FireEmbelm.FireEmblem.app.converters.HealingItemConverter;
import com.FireEmbelm.FireEmblem.app.converters.ItemsConvoyConverter;
import com.FireEmbelm.FireEmblem.app.converters.WeaponConverter;
import com.FireEmbelm.FireEmblem.app.data.entities.ItemsConvoyEntity;
import com.FireEmbelm.FireEmblem.app.data.repository.CharacterRepository;
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

    @Autowired
    private CharacterRepository mCharacterRepository;

    public ArrayList<Equipment> checkShopItemList(Long gameId) {

        int maxLevelCharacter = mCharacterRepository.findFirstByGameId_GameIdOrderByLevelDesc(gameId).level;

        int weaponQuality = maxLevelCharacter / 4;

        if(weaponQuality == 0)
            weaponQuality = 1;

        List<WeaponModel> potentialWeapons = mWeaponRepository.findByQuality(weaponQuality)
                .stream().map(i -> mWeaponConverter.convertEntityToModel(i.weaponEmbeddable))
                .collect(Collectors.toList());


        List<HealingItemModel> healingItems = mHealingItemRepository.findAll()
                .stream().map(i -> mHealingItemConverter.convertEntityToModel(i.healItemEmbeddable))
                .collect(Collectors.toList());

        List<Seals> seals = Arrays.asList(Seals.values());
        List<StatsUpItems> statsUpItems = Arrays.asList(StatsUpItems.values());

        ArrayList<Equipment> returnList = new ArrayList<>(potentialWeapons);
        returnList.addAll(seals);
        returnList.add(statsUpItems.stream().findAny().orElseThrow());
        returnList.addAll(healingItems);

        return returnList;
    }

    public void buyItem(
            int randomItemId, ArrayList<Equipment> randomList, Long gameId
    ) throws TooSmallAmountOfMoneyException {

        ItemsConvoyEntity enteringEntity = mItemsConvoyRepository.findByGameId_GameId(gameId);
        ItemsConvoy itemsConvoy = mItemsConvoyConverter.convertEntityToItemsConvoy(enteringEntity);

        randomList = convertModelsInListToBusiness(randomList);

        mShopService.buyItem(randomItemId,randomList,itemsConvoy);

        saveItemsConvoy(mItemsConvoyConverter.convertToEntity(itemsConvoy), enteringEntity);

    }

    public void sellItem(int itemsConvoyId, Long gameId) {

        ItemsConvoyEntity enteringEntity = mItemsConvoyRepository.findByGameId_GameId(gameId);
        ItemsConvoy itemsConvoy = mItemsConvoyConverter.convertEntityToItemsConvoy(enteringEntity);

        mShopService.sellItem(itemsConvoyId,itemsConvoy);

        saveItemsConvoy(mItemsConvoyConverter.convertToEntity(itemsConvoy), enteringEntity);

    }

    public String checkItem(Equipment equipment) {
        return mShopService.checkItem(equipment);
    }

    private void saveItemsConvoy(ItemsConvoyEntity saveConvoy, ItemsConvoyEntity beforeSave) {

        saveConvoy.convoyId = beforeSave.convoyId;
        saveConvoy.gameId = beforeSave.gameId;

        mItemsConvoyRepository.save(saveConvoy);

    }

    private ArrayList<Equipment> convertModelsInListToBusiness(List<Equipment> list) {

        return list.stream().map(i -> {
            if (i instanceof WeaponModel)
                i = mWeaponConverter.convertModelToWeapon((WeaponModel) i);

            if(i instanceof HealingItemModel)
                i = mHealingItemConverter.convertModelToHealingItem((HealingItemModel) i);

            return i;
        }).collect(Collectors.toCollection(ArrayList::new));

    }
}
