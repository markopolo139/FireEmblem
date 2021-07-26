package com.FireEmbelm.FireEmblem.web.models.payload.field;

import com.FireEmbelm.FireEmblem.web.models.request.SpotModel;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class UseStaffPayload {

    @NotNull
    @Valid
    public SpotModel healingSpot;

    @NotNull
    @Valid
    public SpotModel healedSpot;

    @NotNull
    @Valid
    public Integer itemId;

    public UseStaffPayload(SpotModel healingSpot, SpotModel healedSpot, Integer itemId) {
        this.healingSpot = healingSpot;
        this.healedSpot = healedSpot;
        this.itemId = itemId;
    }
}
