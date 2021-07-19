package com.FireEmbelm.FireEmblem.web.models.request;

import com.FireEmbelm.FireEmblem.web.validation.ValidStatType;

import javax.validation.constraints.Min;
import java.util.Objects;

public class StatModel {

    @ValidStatType
    public String statType;

    @Min(0)
    public int value;

    @Min(0)
    public int chanceToIncrease;

    public StatModel(String statType, int value, int chanceToIncrease) {
        this.statType = statType;
        this.value = value;
        this.chanceToIncrease = chanceToIncrease;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatModel statModel = (StatModel) o;
        return value == statModel.value
                && chanceToIncrease == statModel.chanceToIncrease
                && Objects.equals(statType, statModel.statType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(statType, value, chanceToIncrease);
    }
}
