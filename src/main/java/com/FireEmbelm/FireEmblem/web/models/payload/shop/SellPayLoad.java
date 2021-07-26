package com.FireEmbelm.FireEmblem.web.models.payload.shop;

import com.FireEmbelm.FireEmblem.web.models.request.ItemsConvoyModel;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class SellPayLoad {

    @NotNull
    @Min(0)
    public Integer convoyId;

    @NotNull
    @Valid
    public ItemsConvoyModel itemsConvoyModel;

    public SellPayLoad(Integer convoyId, ItemsConvoyModel itemsConvoyModel) {
        this.convoyId = convoyId;
        this.itemsConvoyModel = itemsConvoyModel;
    }
}
