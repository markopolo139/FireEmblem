package com.FireEmbelm.FireEmblem.web.models.payload.equipment;

import com.FireEmbelm.FireEmblem.web.models.request.CharacterModel;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class TradePayload {

    @NotNull
    @Valid
    public CharacterModel tradeFromCharacter;

    @NotNull
    @Valid
    public CharacterModel tradeToCharacter;

    @NotNull
    @Min(0)
    public Integer equipmentId;

    public TradePayload(CharacterModel tradeFromCharacter, CharacterModel tradeToCharacter, Integer equipmentId) {
        this.tradeFromCharacter = tradeFromCharacter;
        this.tradeToCharacter = tradeToCharacter;
        this.equipmentId = equipmentId;
    }
}
