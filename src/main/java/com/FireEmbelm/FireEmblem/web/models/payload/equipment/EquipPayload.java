package com.FireEmbelm.FireEmblem.web.models.payload.equipment;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class EquipPayload {

    @NotBlank
    public String characterName;

    @NotNull
    @Min(0)
    public Integer equipmentId;

    public EquipPayload(String characterName, Integer equipmentId) {
        this.characterName = characterName;
        this.equipmentId = equipmentId;
    }
}
