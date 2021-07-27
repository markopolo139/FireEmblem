package com.FireEmbelm.FireEmblem.web.models.payload.field;

import com.FireEmbelm.FireEmblem.web.models.request.CharacterModel;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UseItemPayload {

    @NotBlank
    public String characterName;

    @NotNull
    @Min(0)
    public Integer itemId;

    public UseItemPayload(String characterName, Integer itemId) {
        this.characterName = characterName;
        this.itemId = itemId;
    }
}
