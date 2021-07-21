package com.FireEmbelm.FireEmblem.app.data.entities;

import com.FireEmbelm.FireEmblem.app.data.entities.embeddable.WeaponEmbeddable;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "all_weapons")
public class WeaponEntity {

    @Id
    @GeneratedValue
    public Long weaponId;

    @Embedded
    public WeaponEmbeddable weaponEmbeddable;

    public int quality;

    public WeaponEntity(Long weaponId, WeaponEmbeddable weaponEmbeddable, int quality) {
        this.weaponId = weaponId;
        this.weaponEmbeddable = weaponEmbeddable;
        this.quality = quality;
    }

    protected WeaponEntity() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeaponEntity that = (WeaponEntity) o;
        return quality == that.quality
                && Objects.equals(weaponId, that.weaponId)
                && Objects.equals(weaponEmbeddable, that.weaponEmbeddable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(weaponId, weaponEmbeddable, quality);
    }
}
