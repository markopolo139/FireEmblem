package com.FireEmbelm.FireEmblem.app.data.entities.embeddable;

import com.FireEmbelm.FireEmblem.business.value.equipment.HealingItems;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Objects;

@Embeddable
public class HealingItemEmbeddable {

    @Enumerated(EnumType.STRING)
    public HealingItems healType;

    public int uses;

    public HealingItemEmbeddable(HealingItems healType, int uses) {
        this.healType = healType;
        this.uses = uses;
    }

    private HealingItemEmbeddable() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HealingItemEmbeddable that = (HealingItemEmbeddable) o;
        return uses == that.uses && healType == that.healType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(healType, uses);
    }
}
