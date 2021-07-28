package com.FireEmbelm.FireEmblem.web.models.payload.field;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PlacePayload {

    @NotBlank
    public String characterName;

    @NotNull
    @Min(0)
    public Integer spotHeight;

    @NotNull
    @Min(0)
    public Integer spotWidth;

    public PlacePayload(String characterName, Integer spotHeight, Integer spotWidth) {
        this.characterName = characterName;
        this.spotHeight = spotHeight;
        this.spotWidth = spotWidth;
    }
}
