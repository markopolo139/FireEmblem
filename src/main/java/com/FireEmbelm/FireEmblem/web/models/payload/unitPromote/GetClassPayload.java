package com.FireEmbelm.FireEmblem.web.models.payload.unitPromote;

import com.FireEmbelm.FireEmblem.business.value.equipment.Seals;
import com.FireEmbelm.FireEmblem.web.validation.equipment.ValidSeal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class GetClassPayload {

    @NotBlank
    public String characterName;

    @NotNull
    @ValidSeal
    public String seals;

    public GetClassPayload(String characterName, String seals) {
        this.characterName = characterName;
        this.seals = seals;
    }
}
