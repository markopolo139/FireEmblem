package com.FireEmbelm.FireEmblem.web.models.payload.battle;

import com.FireEmbelm.FireEmblem.web.models.request.ItemsConvoyModel;
import com.FireEmbelm.FireEmblem.web.models.request.SpotModel;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class BattlePayload {

    @NotNull
    @Valid
    public SpotModel attackerSpot;

    @NotNull
    @Valid
    public SpotModel defenderSpot;

    @NotNull
    @Valid
    public ItemsConvoyModel itemsConvoyModel;

    public BattlePayload(SpotModel attackerSpot, SpotModel defenderSpot, ItemsConvoyModel itemsConvoyModel) {
        this.attackerSpot = attackerSpot;
        this.defenderSpot = defenderSpot;
        this.itemsConvoyModel = itemsConvoyModel;
    }
}
