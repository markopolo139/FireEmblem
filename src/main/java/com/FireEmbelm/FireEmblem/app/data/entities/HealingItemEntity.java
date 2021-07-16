package com.FireEmbelm.FireEmblem.app.data.entities;

import com.FireEmbelm.FireEmblem.app.data.entities.embeddable.HealingItemEmbeddable;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "all_healing_items")
public class HealingItemEntity {

    @Id
    @GeneratedValue
    public Long healItemId;

    @Embedded
    public HealingItemEmbeddable healItemEmbeddable;

    public HealingItemEntity(Long healItemId, HealingItemEmbeddable healItemEmbeddable) {
        this.healItemId = healItemId;
        this.healItemEmbeddable = healItemEmbeddable;
    }

    private HealingItemEntity() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HealingItemEntity that = (HealingItemEntity) o;
        return Objects.equals(healItemId, that.healItemId) && Objects.equals(healItemEmbeddable, that.healItemEmbeddable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(healItemId, healItemEmbeddable);
    }
}
