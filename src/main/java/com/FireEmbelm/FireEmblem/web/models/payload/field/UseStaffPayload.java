package com.FireEmbelm.FireEmblem.web.models.payload.field;

import com.FireEmbelm.FireEmblem.web.models.request.SpotModel;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class UseStaffPayload {

    @NotNull
    @Min(0)
    public Integer healingSpotHeight;

    @NotNull
    @Min(0)
    public Integer healingSpotWidth;

    @NotNull
    @Min(0)
    public Integer healedSpotHeight;

    @NotNull
    @Min(0)
    public Integer healedSpotWidth;

    @NotNull
    @Valid
    public Integer itemId;

    public UseStaffPayload(
            Integer healingSpotHeight, Integer healingSpotWidth,
            Integer healedSpotHeight, Integer healedSpotWidth,
            Integer itemId
    ) {
        this.healingSpotHeight = healingSpotHeight;
        this.healingSpotWidth = healingSpotWidth;
        this.healedSpotHeight = healedSpotHeight;
        this.healedSpotWidth = healedSpotWidth;
        this.itemId = itemId;
    }
}
