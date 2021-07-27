package com.FireEmbelm.FireEmblem.web.models.payload.equipment;

import com.FireEmbelm.FireEmblem.web.models.request.CharacterModel;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class TradePayload {

    @NotBlank
    public String tradeFromCharacterName;

    @NotBlank
    public String tradeToCharacterName;

    @NotNull
    @Min(0)
    public Integer equipmentId;

    public TradePayload(String tradeFromCharacterName, String tradeToCharacterName, Integer equipmentId) {
        this.tradeFromCharacterName = tradeFromCharacterName;
        this.tradeToCharacterName = tradeToCharacterName;
        this.equipmentId = equipmentId;
    }
}
