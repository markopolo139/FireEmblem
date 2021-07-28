package com.FireEmbelm.FireEmblem.web.models.payload.field;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class MovePayload {

    @NotNull
    @Min(0)
    public Integer moveFromSpotHeight;

    @NotNull
    @Min(0)
    public Integer moveFromSpotWidth;

    @NotNull
    @Min(0)
    public Integer moveToSpotHeight;

    @NotNull
    @Min(0)
    public Integer moveToSpotWidth;

    public MovePayload(
            Integer moveFromSpotHeight, Integer moveFromSpotWidth, Integer moveToSpotHeight, Integer moveToSpotWidth
    ) {
        this.moveFromSpotHeight = moveFromSpotHeight;
        this.moveFromSpotWidth = moveFromSpotWidth;
        this.moveToSpotHeight = moveToSpotHeight;
        this.moveToSpotWidth = moveToSpotWidth;
    }
}
