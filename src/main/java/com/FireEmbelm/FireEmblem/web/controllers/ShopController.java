package com.FireEmbelm.FireEmblem.web.controllers;

import com.FireEmbelm.FireEmblem.app.interactors.ShopInteractor;
import com.FireEmbelm.FireEmblem.app.utils.AppUtils;
import com.FireEmbelm.FireEmblem.business.exceptions.TooSmallAmountOfMoneyException;
import com.FireEmbelm.FireEmblem.business.value.equipment.Equipment;
import com.FireEmbelm.FireEmblem.web.models.payload.shop.BuyPayload;
import com.FireEmbelm.FireEmblem.web.validation.shop.ValidEquipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class ShopController {

    @Autowired
    private ShopInteractor mShopInteractor;

    @Autowired
    private AppUtils mAppUtils;

    @GetMapping("/api/v1/shopList")
    public List<Equipment> checkShopItemList(Principal principal) {
        return mShopInteractor.checkShopItemList(mAppUtils.getGameIdFromLogin(principal.getName()));
    }

    @PutMapping("/api/v1/item/buy")
    public void buyItem(@Valid @RequestBody BuyPayload buyPayload, Principal principal) throws TooSmallAmountOfMoneyException {
        mShopInteractor.buyItem(
                buyPayload.randomListId,
                (ArrayList<Equipment>) buyPayload.randomList,
                mAppUtils.getGameIdFromLogin(principal.getName())
        );
    }

    @PutMapping("/api/v1/item/sell")
    public void sellItem(
            @Valid @NotNull @Min(0) @RequestParam(name = "convoyItemId") Integer convoyId, Principal principal
    ) throws TooSmallAmountOfMoneyException {
        mShopInteractor.sellItem(convoyId, mAppUtils.getGameIdFromLogin(principal.getName()));
    }

    @GetMapping("/api/v1/item/check")
    public String checkItem(@Valid @ValidEquipment Equipment equipment) {
        return mShopInteractor.checkItem(equipment);
    }

}
