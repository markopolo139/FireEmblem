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
    @Min(0)
    public Integer convoyMoney;

    public SellPayLoad(Integer convoyId, Integer convoyMoney) {
        this.convoyId = convoyId;
        this.convoyMoney = convoyMoney;
    }
}
