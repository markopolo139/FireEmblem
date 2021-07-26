package com.FireEmbelm.FireEmblem.web.models.payload.shop;

import com.FireEmbelm.FireEmblem.business.value.equipment.Equipment;
import com.FireEmbelm.FireEmblem.web.models.request.ItemsConvoyModel;
import com.FireEmbelm.FireEmblem.web.validation.shop.ValidEquipmentList;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class BuyPayload {

    @NotNull
    @Min(0)
    public Integer randomListId;

    @NotEmpty
    @ValidEquipmentList
    public List<Equipment> randomList;

    @NotNull
    @Valid
    public ItemsConvoyModel itemsConvoyModel;

    public BuyPayload(Integer randomListId, List<Equipment> randomList, ItemsConvoyModel itemsConvoyModel) {
        this.randomListId = randomListId;
        this.randomList = randomList;
        this.itemsConvoyModel = itemsConvoyModel;
    }
}
