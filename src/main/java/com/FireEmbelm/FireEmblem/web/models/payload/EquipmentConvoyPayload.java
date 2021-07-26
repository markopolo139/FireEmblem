package com.FireEmbelm.FireEmblem.web.models.payload;

import com.FireEmbelm.FireEmblem.web.models.request.CharacterModel;
import com.FireEmbelm.FireEmblem.web.models.request.ItemsConvoyModel;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class EquipmentConvoyPayload {

    @NotNull
    @Valid
    public CharacterModel mCharacterModel;

    @NotNull
    @Valid
    public ItemsConvoyModel mItemsConvoyModel;

    @NotNull
    @Min(0)
    public Integer elementId;

    public EquipmentConvoyPayload(CharacterModel characterModel, ItemsConvoyModel itemsConvoyModel, Integer elementId) {
        mCharacterModel = characterModel;
        mItemsConvoyModel = itemsConvoyModel;
        this.elementId = elementId;
    }
}
