package com.FireEmbelm.FireEmblem.web.models.payload.equipment;

import com.FireEmbelm.FireEmblem.web.models.request.CharacterModel;
import com.FireEmbelm.FireEmblem.web.models.request.ItemsConvoyModel;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class EquipmentConvoyPayload {

    @NotNull
    @Valid
    public CharacterModel characterModel;

    @NotNull
    @Valid
    public ItemsConvoyModel itemsConvoyModel;

    @NotNull
    @Min(0)
    public Integer elementId;

    public EquipmentConvoyPayload(CharacterModel characterModel, ItemsConvoyModel itemsConvoyModel, Integer elementId) {
        this.characterModel = characterModel;
        this.itemsConvoyModel = itemsConvoyModel;
        this.elementId = elementId;
    }
}
