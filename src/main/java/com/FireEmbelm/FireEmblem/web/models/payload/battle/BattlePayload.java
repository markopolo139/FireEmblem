package com.FireEmbelm.FireEmblem.web.models.payload.battle;

import com.FireEmbelm.FireEmblem.web.models.request.ItemsConvoyModel;
import com.FireEmbelm.FireEmblem.web.models.request.SpotModel;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class BattlePayload {

    @NotNull
    @Min(0)
    public Integer attackerSpotHeight;

    @NotNull
    @Min(0)
    public Integer attackerSpotWidth;

    @NotNull
    @Min(0)
    public Integer defenderSpotHeight;

    @NotNull
    @Min(0)
    public Integer defenderSpotWidth;

    public BattlePayload(
            Integer attackerSpotHeight, Integer attackerSpotWidth,
            Integer defenderSpotHeight, Integer defenderSpotWidth
    ) {
        this.attackerSpotHeight = attackerSpotHeight;
        this.attackerSpotWidth = attackerSpotWidth;
        this.defenderSpotHeight = defenderSpotHeight;
        this.defenderSpotWidth = defenderSpotWidth;
    }

}
