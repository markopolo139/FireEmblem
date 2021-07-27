package com.FireEmbelm.FireEmblem.web.models.payload.equipment;

import com.FireEmbelm.FireEmblem.web.models.request.CharacterModel;
import com.FireEmbelm.FireEmblem.web.models.request.ItemsConvoyModel;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class EquipmentConvoyPayload {

    @NotBlank
    public String characterName;

    @NotNull
    @Min(0)
    public Integer convoyMoney;

    @NotNull
    @Min(0)
    public Integer elementId;

    public EquipmentConvoyPayload(String characterName, Integer convoyMoney, Integer elementId) {
        this.characterName = characterName;
        this.convoyMoney = convoyMoney;
        this.elementId = elementId;
    }
}
