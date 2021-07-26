package com.FireEmbelm.FireEmblem.web.models.payload.equipment;

import com.FireEmbelm.FireEmblem.web.models.request.CharacterModel;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class EquipPayload {

    @NotNull
    @Valid
    public CharacterModel characterModel;

    @NotNull
    @Min(0)
    public Integer equipmentId;

    public EquipPayload(CharacterModel characterModel, Integer equipmentId) {
        this.characterModel = characterModel;
        this.equipmentId = equipmentId;
    }
}
