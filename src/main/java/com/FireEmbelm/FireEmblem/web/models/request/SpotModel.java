package com.FireEmbelm.FireEmblem.web.models.request;

import com.FireEmbelm.FireEmblem.web.validation.ValidSpotType;
import org.springframework.lang.Nullable;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.Objects;

public class SpotModel {

    @ValidSpotType
    public String spotType;

    @Min(0)
    public int height;

    @Min(0)
    public int width;

    @Nullable
    @Valid
    public CharacterModel characterOnSpot;

    @Nullable
    @Valid
    public EnemyModel enemyOnSpot;

    public SpotModel(
            String spotType, int height, int width, @Nullable CharacterModel characterOnSpot,
            @Nullable EnemyModel enemyOnSpot
    ) {
        this.spotType = spotType;
        this.height = height;
        this.width = width;
        this.characterOnSpot = characterOnSpot;
        this.enemyOnSpot = enemyOnSpot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpotModel spotModel = (SpotModel) o;
        return height == spotModel.height
                && width == spotModel.width
                && Objects.equals(spotType, spotModel.spotType)
                && Objects.equals(characterOnSpot, spotModel.characterOnSpot)
                && Objects.equals(enemyOnSpot, spotModel.enemyOnSpot);
    }

    @Override
    public int hashCode() {
        return Objects.hash(spotType, height, width, characterOnSpot, enemyOnSpot);
    }
}
