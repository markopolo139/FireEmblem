package com.FireEmbelm.FireEmblem.app.data.entities.embeddable;

import com.FireEmbelm.FireEmblem.business.value.character.related.StatsType;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Objects;

@Embeddable
public class StatEmbeddable {

    @Enumerated(EnumType.STRING)
    public StatsType statType;

    public int value;

    public int increaseChance;

    public StatEmbeddable(StatsType statsType, int value, int increaseChance) {
        this.statType = statsType;
        this.value = value;
        this.increaseChance = increaseChance;
    }

    private StatEmbeddable() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatEmbeddable that = (StatEmbeddable) o;
        return value == that.value && increaseChance == that.increaseChance && statType == that.statType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(statType, value, increaseChance);
    }
}
