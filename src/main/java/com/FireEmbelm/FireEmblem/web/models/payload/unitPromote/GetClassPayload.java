package com.FireEmbelm.FireEmblem.web.models.payload.unitPromote;

import com.FireEmbelm.FireEmblem.business.value.equipment.Seals;
import com.FireEmbelm.FireEmblem.web.models.request.CharacterModel;
import com.FireEmbelm.FireEmblem.web.validation.equipment.ValidSeal;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class GetClassPayload {

    @NotNull
    @Valid
    public CharacterModel characterModel;

    @NotNull
    @ValidSeal
    public Seals seals;

    public GetClassPayload(CharacterModel characterModel, Seals seals) {
        this.characterModel = characterModel;
        this.seals = seals;
    }
}
