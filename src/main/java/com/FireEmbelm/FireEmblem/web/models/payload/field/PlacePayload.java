package com.FireEmbelm.FireEmblem.web.models.payload.field;

import com.FireEmbelm.FireEmblem.web.models.request.CharacterModel;
import com.FireEmbelm.FireEmblem.web.models.request.SpotModel;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class PlacePayload {

    @NotNull
    @Valid
    public CharacterModel characterModel;

    @NotNull
    @Valid
    public SpotModel spotModel;

    public PlacePayload(CharacterModel characterModel, SpotModel spotModel) {
        this.characterModel = characterModel;
        this.spotModel = spotModel;
    }
}
