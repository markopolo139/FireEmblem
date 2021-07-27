package com.FireEmbelm.FireEmblem.web.models.payload.unitPromote;

import com.FireEmbelm.FireEmblem.business.entitie.CharacterClass;
import com.FireEmbelm.FireEmblem.web.models.request.CharacterModel;
import com.FireEmbelm.FireEmblem.web.validation.character.ValidCharacterClass;
import com.FireEmbelm.FireEmblem.web.validation.character.ValidCharacterClassList;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

public class PromoteCharacterPayload {

    @NotBlank
    public String characterName;

    @NotEmpty
    @ValidCharacterClassList
    public List<CharacterClass> characterClassList;

    @NotNull
    @Min(0)
    public Integer listId;

    public PromoteCharacterPayload(String characterName, List<CharacterClass> characterClassList, Integer listId) {
        this.characterName = characterName;
        this.characterClassList = characterClassList;
        this.listId = listId;
    }
}
