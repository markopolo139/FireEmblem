package com.FireEmbelm.FireEmblem.web.models.payload.field;

import com.FireEmbelm.FireEmblem.web.models.request.CharacterModel;
import com.FireEmbelm.FireEmblem.web.models.request.SpotModel;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class MovePayload {

    @NotNull
    @Valid
    public SpotModel moveFrom;

    @NotNull
    @Valid
    public SpotModel moveTo;

    public MovePayload(SpotModel moveFrom, SpotModel moveTo) {
        this.moveFrom = moveFrom;
        this.moveTo = moveTo;
    }
}
