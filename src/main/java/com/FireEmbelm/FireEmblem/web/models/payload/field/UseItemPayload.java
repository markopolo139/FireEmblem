package com.FireEmbelm.FireEmblem.web.models.payload.field;

import com.FireEmbelm.FireEmblem.web.models.request.CharacterModel;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class UseItemPayload {

    @NotNull
    @Valid
    public CharacterModel characterModel;

    @NotNull
    @Min(0)
    public Integer itemId;

    public UseItemPayload(CharacterModel characterModel, Integer itemId) {
        this.characterModel = characterModel;
        this.itemId = itemId;
    }
}
