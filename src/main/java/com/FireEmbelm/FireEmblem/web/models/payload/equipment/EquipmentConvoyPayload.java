package com.FireEmbelm.FireEmblem.web.models.payload.equipment;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class EquipmentConvoyPayload {

    @NotBlank
    public String characterName;

    @NotNull
    @Min(0)
    public Integer elementId;

    public EquipmentConvoyPayload(String characterName, Integer elementId) {
        this.characterName = characterName;
        this.elementId = elementId;
    }
}
