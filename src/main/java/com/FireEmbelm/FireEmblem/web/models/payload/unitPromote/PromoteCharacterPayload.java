package com.FireEmbelm.FireEmblem.web.models.payload.unitPromote;

import com.FireEmbelm.FireEmblem.business.entitie.CharacterClass;
import com.FireEmbelm.FireEmblem.web.models.request.CharacterModel;
import com.FireEmbelm.FireEmblem.web.validation.character.ValidCharacterClass;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class PromoteCharacterPayload {

    @NotNull
    @Valid
    public CharacterModel characterModel;

    @NotEmpty
    @ValidCharacterClass
    public List<CharacterClass> characterClassList;

    @NotNull
    @Min(0)
    public Integer listId;

    public PromoteCharacterPayload(
            CharacterModel characterModel, List<CharacterClass> characterClassList, Integer listId
    ) {
        this.characterModel = characterModel;
        this.characterClassList = characterClassList;
        this.listId = listId;
    }
}
